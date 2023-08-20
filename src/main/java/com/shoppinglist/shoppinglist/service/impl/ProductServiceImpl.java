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

    @Override
    public byte[] getProductsListPdf() throws DocumentException {
        List<Product> productList = getProducts();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable table = new PdfPTable(1);

        Phrase headerPhrase = new Phrase("Produtos");

        PdfPCell header = new PdfPCell(headerPhrase);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setVerticalAlignment(Element.ALIGN_CENTER);
        header.getPhrase().getFont().setSize(16);
        header.getPhrase().getFont().setStyle(Font.BOLD);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setPadding(10f);

        table.addCell(header);


        for (Product product : productList) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(5f);
            cell.setPhrase(new Phrase(product.getName()));
            table.addCell(cell);
        }

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }
}
