package com.shoppinglist.shoppinglist.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.repository.ProductRepository;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    private Document createNewDocument() {
        return new Document(PageSize.A4);
    }

    private ByteArrayOutputStream createNewByteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }

    @Override
    public byte[] getProductsListPdf() throws DocumentException {
        List<Product> productList = getProducts();

        ByteArrayOutputStream outputStream = createNewByteArrayOutputStream();
        Document document = createNewDocument();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable table = new PdfPTable(4);

        Phrase headerPhrase = new Phrase("Produtos");
        PdfPCell header = new PdfPCell(headerPhrase);
        header.setColspan(3);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setVerticalAlignment(Element.ALIGN_CENTER);
        header.getPhrase().getFont().setSize(16);
        header.getPhrase().getFont().setStyle(Font.BOLD);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setPadding(10f);

        PdfPCell header2 = new PdfPCell();
        header2.setColspan(1);

        table.addCell(header);
        table.addCell(header2);


        for (Product product : productList) {
            PdfPCell cell = new PdfPCell();
            PdfPCell cellId = new PdfPCell();
            cell.setColspan(3);
            cell.setPadding(5f);
            cell.setPhrase(new Phrase(product.getName()));
            table.addCell(cell);
            cellId.setColspan(1);
            cellId.setPhrase(new Phrase(String.valueOf(product.getId())));
            table.addCell(cellId);
        }

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

}
