package com.desafiolatam.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.models.ProductosVentas;
import com.desafiolatam.models.Venta;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ProductoVentaPDFExporter {
	
	private List<ProductosVentas> listaProductoVenta;
	private Venta venta;
    
    public ProductoVentaPDFExporter(List<ProductosVentas> listaProductoVenta, Venta venta) {
        this.listaProductoVenta = listaProductoVenta;
        this.venta = venta;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Valor Unitario", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("SubTotal", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (ProductosVentas productosVentas : listaProductoVenta) {
            table.addCell(String.valueOf(productosVentas.getCantidad()));
            table.addCell(productosVentas.getProducto().getNombre());
            table.addCell(String.valueOf(productosVentas.getValorUnitario()));
            table.addCell(String.valueOf(productosVentas.getTotal()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph titulo = new Paragraph("Boleta de Venta n°"+ venta.getId(), font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER); 
        document.add(titulo);
        
        Font fontCliente = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.GRAY);
        Paragraph cliente = new Paragraph("CLIENTE : "+ venta.getCliente().getNombre() +" "+ venta.getCliente().getApellido(), fontCliente);
        document.add(cliente);
        
        Paragraph total = new Paragraph("TOTAL : $"+venta.getMonto(),fontCliente);
        document.add(total);
        
        Paragraph p = new Paragraph("Lista de Productos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER); 
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
