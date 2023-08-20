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
            PdfPCell productNameCell = generateCell(product.getName(), 3, 5f);
            PdfPCell urgencyCell = generateCell(product.getUrgencyLevel().getMessage(), 1, 5f);

            table.addCell(productNameCell);
            table.addCell(urgencyCell);
        }
    }

    private PdfPCell generateCell(
            String phrase,
            int cols,
            float padding) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setColspan(cols);
        pdfPCell.setPadding(padding);
        pdfPCell.setPhrase(new Phrase(phrase));
        pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfPCell;
    }

    @Override
    public byte[] getProductsListPdf() throws DocumentException {
        final String PRODUCTS_HEADER_NAME = "Produtos";
        final String URGENCY_HEADER_NAME = "Urgência";

        List<Product> productList = getProducts();

        ByteArrayOutputStream outputStream = createNewByteArrayOutputStream();
        Document document = createNewDocument();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable table = createNewPdfTable(4);


        PdfPCell productsHeader = buildPdfCell(createPhrase(PRODUCTS_HEADER_NAME), 3, 16f, Font.BOLD, Element.ALIGN_CENTER, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 10f);
        PdfPCell urgencyHeader = buildPdfCell(createPhrase(URGENCY_HEADER_NAME), 1, 16f, Font.BOLD, Element.ALIGN_CENTER, Element.ALIGN_CENTER, BaseColor.LIGHT_GRAY, 10f);

        table.addCell(productsHeader);
        table.addCell(urgencyHeader);

        generateTableCells(productList, table);

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

}
