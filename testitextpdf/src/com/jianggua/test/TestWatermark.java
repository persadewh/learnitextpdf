package com.jianggua.test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class TestWatermark {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		String dest = "/home/rayweihao/myfile/test.pdf";
		OutputStream ops = new FileOutputStream(dest);
		PdfWriter pdfWriter = new PdfWriter(ops);
		
		PdfDocument pdf = new PdfDocument(pdfWriter);
		Document doc = new Document(pdf);
		
		PageSize pageSize = PageSize.A4.rotate();
		PdfPage page = pdf.addNewPage(pageSize);
		PdfCanvas canvas = new PdfCanvas(page);
		
		PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
		doc.add(new Paragraph("iText is : ").setFont(font));
		
		List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
		list.add(new ListItem("1"))
			.add(new ListItem("2"))
			.add(new ListItem("3"))
			.add(new ListItem("4"))
			.add(new ListItem("5"))
			.add(new ListItem("6"));
		
		doc.add(list);
		
		//watermark
		Paragraph p = new Paragraph("CONFIDENTIAL").setFontSize(60);
		canvas.saveState();
		PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
		canvas.setExtGState(gs1);
		doc.showTextAligned(p,
		        pageSize.getWidth() / 2, pageSize.getHeight() / 2,
		        pdf.getPageNumber(page),
		        TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
		canvas.restoreState();
        
		canvas.release();
		
		pdf.close();
	}

}
