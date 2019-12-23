package com.java.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.model.Company;
import com.java.model.Employee;

public class PDFGenerator {

	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream customerPDFReport(List<Employee> user, Company comp)
			throws MalformedURLException, IOException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			if (comp.getImage() != null) {
				Image image = Image.getInstance(comp.getImage());
				// image.scaleAbsolute(200, 200);
				image.scaleAbsolute(150f, 150f);
				image.setAbsolutePosition(30f, 685f);
				document.add(image);
			} else {
				Image image = Image.getInstance(comp.getPicture_url());
				// image.scaleAbsolute(200, 200);
				image.scaleAbsolute(150f, 150f);
				image.setAbsolutePosition(30f, 685f);
				document.add(image);
			}
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35, BaseColor.BLACK);
			Paragraph para = new Paragraph(comp.getCom_name(), font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK);
			Paragraph para1 = new Paragraph(comp.getCom_email(), font1);
			para1.setAlignment(Element.ALIGN_CENTER);
//				para1.setBorder(new SolidBorder(1));
			document.add(para1);
			document.add(Chunk.NEWLINE);
//				document.add(new Phrase(comp.getCom_name()));
			/*
			 * PdfPTable table = new PdfPTable(2); table.setWidthPercentage(100);
			 * table.setWidths(new int[] { 1, 2 }); table.addCell(createImageCell(image));
			 * table.addCell(createTextCell(new Phrase(comp.getCom_name())));
			 * document.add(table);
			 */

			// Add Text to PDF file ->
			Font font2 = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
			Paragraph para2 = new Paragraph("Employee List", font2);
			para2.setAlignment(Element.ALIGN_CENTER);

			document.add(para);
			document.add(Chunk.NEWLINE);

			float[] pointColumnWidths = { 25F, 75F, 75F, 75F, 75F, 75F, 75F, 75F };
//			float[] pointColumnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable table = new PdfPTable(pointColumnWidths);
			table.setWidthPercentage(100);
			// Add PDF Table Header ->
			Stream.of("ID", "Name", "Address", "City", "State", "Pincode", "Email", "Contact").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(1);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (Employee employee : user) {
				PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(employee.getId())));
				// idCell.setPaddingLeft(5);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell NameCell = new PdfPCell(new Phrase(employee.getName()));
				NameCell.setPaddingLeft(11);
				NameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				NameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(NameCell);

				PdfPCell AddressCell = new PdfPCell(new Phrase(employee.getAddress()));
				AddressCell.setPaddingLeft(11);
				AddressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				AddressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(AddressCell);

				PdfPCell CityCell = new PdfPCell(new Phrase(employee.getCity()));
				CityCell.setPaddingLeft(11);
				CityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				CityCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(CityCell);

				PdfPCell StateCell = new PdfPCell(new Phrase(employee.getState()));
				StateCell.setPaddingLeft(11);
				StateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				StateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(StateCell);

				PdfPCell PincodeCell = new PdfPCell(new Phrase(employee.getPincode()));
				PincodeCell.setPaddingLeft(11);
				PincodeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PincodeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(PincodeCell);

				PdfPCell EmailCell = new PdfPCell(new Phrase(employee.getEmail()));
				// EmailCell.setPaddingLeft(11);
				EmailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				EmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(EmailCell);

				PdfPCell ContactCell = new PdfPCell(new Phrase(employee.getContact_no()));
//				ContactCell.setPaddingLeft(4);
				ContactCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ContactCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//				ContactCell.setPaddingRight(11);
				table.addCell(ContactCell);
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}