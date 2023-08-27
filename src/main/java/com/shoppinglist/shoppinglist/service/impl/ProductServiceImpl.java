package com.shoppinglist.shoppinglist.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.exception.ResourceNotFoundException;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.product.ProductCreateDto;
import com.shoppinglist.shoppinglist.payload.product.ProductMinDto;
import com.shoppinglist.shoppinglist.payload.product.ProductUpdateDto;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.utils.product.ProductComparator;
import com.shoppinglist.shoppinglist.repository.ProductRepository;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final MapperUtils mapperUtils;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ShoppingCartRepository shoppingCartRepository,
            MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Product> getProducts(Long shoppingCartId) {
        verifyIfShoppingCartExists(shoppingCartId);
        return getProductsByShoppingCart(shoppingCartId);
    }

    @Override
    public ApiMessageResponse createProduct(ProductCreateDto createProduct) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findById(createProduct.getShoppingCartId())
                .orElseThrow(() -> new ResourceNotFoundException("O carrinho informado não existe"));

        Product product = new Product(createProduct.getName(), createProduct.getUrgencyLevel(), shoppingCart);
        Product savedProduct = productRepository.save(product);

        return new ApiMessageResponse("Produto adicionado com sucesso: " + savedProduct.getName() + ", ID: " + savedProduct.getId());
    }

    @Override
    public ApiMessageResponse updateProduct(
            Long shoppingCartId,
            Long id,
            ProductUpdateDto updateProduct) {
        Product product = productRepository
                .findByIdAndShoppingCart_id(id, shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        product.setName(updateProduct.getName());
        product.setUrgencyLevel(updateProduct.getUrgencyLevel());

        productRepository.save(product);
        return new ApiMessageResponse("Produto atualizado com sucesso");
    }

    @Override
    public ProductMinDto getProductById(
            Long shoppingCartId,
            Long id) {
        Product product = productRepository
                .findByIdAndShoppingCart_id(id, shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return new ProductMinDto(product.getId(), product.getName(), product.getUrgencyLevel());
    }

    @Override
    public byte[] getProductsListPdf(Long shoppingCartId) throws DocumentException {
        final String PRODUCTS_HEADER_NAME = "Produtos";
        final String URGENCY_HEADER_NAME = "Urgência";

        List<Product> sortedProductList = getSortedProducts(getProducts(shoppingCartId));

        ByteArrayOutputStream outputStream = createNewByteArrayOutputStream();
        Document document = createNewDocument();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable table = createNewPdfTable(4);


        PdfPCell productsHeader = buildPdfCell(createPhrase(PRODUCTS_HEADER_NAME), 3, 16f, Font.BOLD, Element.ALIGN_CENTER, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 10f);
        PdfPCell urgencyHeader = buildPdfCell(createPhrase(URGENCY_HEADER_NAME), 1, 16f, Font.BOLD, Element.ALIGN_CENTER, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 10f);

        table.addCell(productsHeader);
        table.addCell(urgencyHeader);

        generateTableCells(sortedProductList, table);

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

    @Override
    public ApiMessageResponse removeProduct(
            Long shoppingCartId,
            Long id) {
        verifyIfProductExists(shoppingCartId, id);

        productRepository.deleteById(id);

        return new ApiMessageResponse("Produto removido com sucesso!");
    }

    private List<Product> getProductsByShoppingCart(Long shoppingCartId) {
        return productRepository.findAllByShoppingCart_Id(shoppingCartId);
    }

    private void verifyIfShoppingCartExists(Long shoppingCartId) {
        if (!shoppingCartRepository.existsById(shoppingCartId)) {
            throw new ResourceNotFoundException("O carrinho informado não existe");
        }
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
            PdfPCell productNameCell = generateCell(product, product.getName(), 3, 5f);
            PdfPCell urgencyCell = generateCell(product, product.getUrgencyLevel().getMessage(), 1, 5f);

            table.addCell(productNameCell);
            table.addCell(urgencyCell);
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

    ;

}
