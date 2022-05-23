package com.desafiolatam.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

public class VentaPDFExporter {

	private List<Venta> listaVenta;

	public VentaPDFExporter(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Venta ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Monto Total", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Cliente Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Cliente Apellido", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (Venta venta : listaVenta) {
			table.addCell(String.valueOf(venta.getId()));
			table.addCell(String.valueOf(venta.getMonto()));
			table.addCell(venta.getCliente().getNombre());
			table.addCell(venta.getCliente().getApellido());
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Lista de Ventas", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
