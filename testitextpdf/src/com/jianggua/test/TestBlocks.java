package com.jianggua.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

public class TestBlocks {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String dest = "/home/rayweihao/myfile/test.pdf";
		OutputStream ops = new FileOutputStream(dest);
		PdfWriter pdfWriter = new PdfWriter(ops);
		
		PdfDocument pdf = new PdfDocument(pdfWriter);
		Document doc = new Document(pdf);
		
		
		//doc.add(new Paragraph("Hello World"));
		
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		doc.add(new Paragraph("iText is : ").setFont(font));
		
		List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
		list.add(new ListItem("1"))
			.add(new ListItem("2"))
			.add(new ListItem("3"))
			.add(new ListItem("4"))
			.add(new ListItem("5"))
			.add(new ListItem("6"));
		
		doc.add(list);
		
		doc.close();
		
	}

}
