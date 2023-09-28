package com.shoppinglist.shoppinglist.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.exception.ResourceNotFoundException;
import com.shoppinglist.shoppinglist.factory.product.ProductFactory;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.product.*;
import com.shoppinglist.shoppinglist.repository.ProductRepository;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import com.shoppinglist.shoppinglist.utils.mapper.MapperUtils;
import com.shoppinglist.shoppinglist.utils.product.ProductComparator;
import com.shoppinglist.shoppinglist.utils.product.ProductTableHeaders;
import com.shoppinglist.shoppinglist.utils.product.ProductUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductTableHeaders productTableHeaders;
    private final ProductFactory productFactory;
    private final MapperUtils mapperUtils;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ShoppingCartRepository shoppingCartRepository,
            ProductTableHeaders productTableHeaders,
            ProductFactory productFactory,
            MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productTableHeaders = productTableHeaders;
        this.productFactory = productFactory;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public ProductListDto productsList(Long shoppingCartId) {
        ShoppingCart shoppingCart = verifyIfShoppingCartExists(shoppingCartId);
        List<Product> products = getSortedProducts(getProductsByShoppingCart(shoppingCartId));
        return new ProductListDto(shoppingCart.getName(), mapperUtils.parseListObjects(products, ProductMinDto.class));
    }

    @Override
    public ProductListDtoV2 productsListV2(Long shoppingCartId) {
        ShoppingCart shoppingCart = verifyIfShoppingCartExists(shoppingCartId);
        List<Product> products = getSortedProducts(getProductsByShoppingCart(shoppingCartId));
        List<ProductDto> productDtos = mapperUtils.parseListObjects(products, ProductDto.class);
        return new ProductListDtoV2(shoppingCart.getName(), productDtos, ProductUtils.calculateShoppingCartTotalPrice(productDtos));
    }


    @Override
    public ProductListDto productsList(
            Long shoppingCartId,
            String name) {
        ShoppingCart shoppingCart = verifyIfShoppingCartExists(shoppingCartId);


        if (name == null) {
            return this.productsList(shoppingCartId);
        }

        List<Product> productsList = filterByName(name, shoppingCartId);
        return new ProductListDto(shoppingCart.getName(), mapperUtils.parseListObjects(productsList, ProductMinDto.class));
    }

    @Override
    public ApiMessageResponse createProduct(ProductCreateDto createProduct) {
        ShoppingCart shoppingCart = fetchShoppingCartById(createProduct.getShoppingCartId());

        Product product = buildNewProductWithPrice(createProduct, shoppingCart);
        Product savedProduct = saveProduct(product);

        return new ApiMessageResponse("Produto adicionado com sucesso: " + savedProduct.getName() + ", ID: " + savedProduct.getId());
    }


    @Override
    public ApiMessageResponse updateProduct(
            Long shoppingCartId,
            Long id,
            ProductUpdateDto updateProduct) {
        Product product = fetchProductById(id, shoppingCartId);

        updateProductAttributes(product, updateProduct);

        saveProduct(product);
        return new ApiMessageResponse("Produto atualizado com sucesso");
    }

    @Override
    public List<Product> filterByName(
            String name,
            Long shoppingCartId) {
        return findByName(name, shoppingCartId);
    }


    private List<Product> findByName(
            String name,
            Long shoppingCartId) {
        return productRepository.findByNameContainingIgnoreCaseAndShoppingCart_Id(name, shoppingCartId);
    }

    private Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    private Product fetchProductById(
            Long productId,
            Long shoppingCartId) {
        return productRepository
                .findByIdAndShoppingCart_id(productId, shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private ShoppingCart fetchShoppingCartById(
            Long shoppingCartId) {
        return shoppingCartRepository
                .findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
    }

    @Override
    public ProductMinDto getProductById(
            Long shoppingCartId,
            Long id) {
        Product product = getShoppingCartProductById(id, shoppingCartId);

        return mapperUtils.parseObject(product, ProductMinDto.class);
    }

    @Override
    public byte[] getProductsListPdf(Long shoppingCartId) throws DocumentException {
        List<ProductMinDto> productsList = productsList(shoppingCartId).getProducts();

        List<Product> sortedProductList = getSortedProducts(mapperUtils.parseListObjects(productsList, Product.class));

        ByteArrayOutputStream outputStream = createNewByteArrayOutputStream();
        Document document = createNewDocument();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable table = createNewPdfTable(10);

        List<PdfPCell> tableHeaders = generateTableHeaders();

        addTableHeaders(table, tableHeaders);

        generateTableCells(sortedProductList, table);

        List<ProductDto> productDtos = mapperUtils.parseListObjects(productsList, ProductDto.class);

        PdfPCell totalPriceCell = buildPdfCell(createPhrase("Total: " + ProductUtils.getTotalPriceNormalized(productDtos)), 10, 12f, Font.BOLD, Element.ALIGN_RIGHT, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 5f);
        table.addCell(totalPriceCell);

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

    private List<PdfPCell> generateTableHeaders() {
        List<String> headersNames = productTableHeaders.getProductTableHeaders();
        List<PdfPCell> headersCells = new ArrayList<>();

        for (String header : headersNames) {
            headersCells.add(buildPdfCell(createPhrase(header), 2, 12f, Font.BOLD, Element.ALIGN_CENTER, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 4f));
        }

        return headersCells;
    }

    private String getTotalPrice(List<ProductDto> productDtos) {
        return ProductUtils.calculateShoppingCartTotalPrice(productDtos).toString().replace('.', ',');
    }

    @Override
    public ApiMessageResponse removeProduct(
            Long shoppingCartId,
            Long id) {
        verifyIfProductExists(shoppingCartId, id);

        productRepository.deleteById(id);

        return new ApiMessageResponse("Produto removido com sucesso!");
    }

    private Product buildNewProduct(
            ProductCreateDto createProduct,
            ShoppingCart shoppingCart) {
        return productFactory.createProduct(createProduct.getName(), createProduct.getUrgencyLevel(), shoppingCart);
    }

    private Product buildNewProductWithPrice(
            ProductCreateDto createProduct,
            ShoppingCart shoppingCart) {
        return productFactory.createProductWithPrice(createProduct.getName(), createProduct.getUrgencyLevel(), shoppingCart, createProduct.getProductPrice(), createProduct.getProductQuantity());
    }

    private void updateProductAttributes(
            Product product,
            ProductUpdateDto productUpdateDto) {
        product.setName(productUpdateDto.getName());
        product.setUrgencyLevel(productUpdateDto.getUrgencyLevel());
        product.setProductPrice(productUpdateDto.getProductPrice());
        product.setProductQuantity(productUpdateDto.getProductQuantity());
    }

    private List<Product> getProductsByShoppingCart(Long shoppingCartId) {
        return productRepository.findAllByShoppingCart_Id(shoppingCartId);
    }

    private ShoppingCart verifyIfShoppingCartExists(Long shoppingCartId) {
        return shoppingCartRepository
                .findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista de compras não encontrada"));
    }

    private Product getShoppingCartProductById(
            Long productId,
            Long shoppingCartId) {
        return productRepository
                .findByIdAndShoppingCart_id(productId, shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private void verifyIfProductExists(
            Long shoppingCartId,
            Long productId) {
        productRepository
                .findByIdAndShoppingCart_id(productId, shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private List<Product> getSortedProducts(List<Product> productList) {
        productList.sort(new ProductComparator());
        return productList;
    }

    private Document createNewDocument() {
        return new Document(PageSize.A4);
    }

    private ByteArrayOutputStream createNewByteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }

    private PdfPTable createNewPdfTable(int columns) {
        return new PdfPTable(columns);
    }

    private Phrase createPhrase(String phrase) {
        return new Phrase(phrase);
    }

    private PdfPCell buildPdfCell(
            Phrase phrase,
            int colSize,
            float fontSize,
            int fontWeight,
            int horizontalAlignment,
            int verticalAlignment,
            BaseColor bgColor,
            float padding) {
        PdfPCell header = new PdfPCell(phrase);
        header.setColspan(colSize);
        header.setHorizontalAlignment(horizontalAlignment);
        header.setVerticalAlignment(verticalAlignment);
        header.getPhrase().getFont().setSize(fontSize);
        header.getPhrase().getFont().setStyle(fontWeight);
        header.setBackgroundColor(bgColor);
        header.setPadding(padding);
        return header;
    }

    private void generateTableCells(
            List<Product> productList,
            PdfPTable table) {
        for (Product product : productList) {
            PdfPCell productNameCell = generateCell(product, product.getName(), 2, 3f);
            PdfPCell urgencyCell = generateCell(product, product.getUrgencyLevel().getMessage(), 2, 3f);
            PdfPCell quantityCell = generateCell(product, product.getProductQuantity().toString(), 2, 3f);
            PdfPCell priceCell = generateCell(product, ProductUtils.normalizeCurrency(product.getProductPrice()), 2, 3f);
            PdfPCell totalPriceCell = generateCell(product, ProductUtils.normalizeCurrency(product.getProductPrice()), 2, 3f);

            table.addCell(productNameCell);
            table.addCell(urgencyCell);
            table.addCell(quantityCell);
            table.addCell(priceCell);
            table.addCell(totalPriceCell);
        }
    }

    private void addTableHeaders(
            PdfPTable table,
            List<PdfPCell> headers) {
        for (PdfPCell header : headers) {
            table.addCell(header);
        }
    }

    private PdfPCell generateCell(
            Product product,
            String phrase,
            int cols,
            float padding) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setColspan(cols);
        pdfPCell.setPadding(padding);
        pdfPCell.setPhrase(new Phrase(phrase));
        pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setBackgroundColor(defineCellColor(product));
        return pdfPCell;
    }

    private PdfPCell generateCell(
            String phrase,
            int cols,
            float padding
    ) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setColspan(cols);
        pdfPCell.setPadding(padding);
        pdfPCell.setPhrase(new Phrase(phrase));
        pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setBackgroundColor(BaseColor.WHITE);
        return pdfPCell;
    }

    private BaseColor defineCellColor(Product product) {
        final int alphaLevel = 150;
        switch (product.getUrgencyLevel()) {
            case CRITICAL -> {
                return new BaseColor(217, 83, 79, alphaLevel);
            }
            case HIGH -> {
                return new BaseColor(240, 173, 78, alphaLevel);
            }
            case MEDIUM -> {
                return new BaseColor(92, 184, 92, alphaLevel);
            }
            case LOW -> {
                return new BaseColor(2, 117, 216, alphaLevel);
            }
            case LOWEST -> {
                return new BaseColor(91, 192, 222, alphaLevel);
            }
            default -> {
                return BaseColor.WHITE;
            }
        }
    }

}
