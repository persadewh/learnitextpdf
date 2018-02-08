package com.jianggua.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;

public class TestLowLevel {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		String dest = "/home/rayweihao/myfile/test.pdf";
		OutputStream ops = new FileOutputStream(dest);
		PdfWriter pdfWriter = new PdfWriter(ops);
		
		PdfDocument pdf = new PdfDocument(pdfWriter);
		
		PageSize pageSize = PageSize.A4.rotate();
		
		PdfPage page = pdf.addNewPage(pageSize);
		PdfCanvas canvas = new PdfCanvas(page);
		
		
		canvas.concatMatrix(1, 0, 0, 1, pageSize.getWidth()/2, pageSize.getHeight()/2);//变换坐标
		
		//画X轴，左右两边各缩进15
		canvas.moveTo(-(pageSize.getWidth() / 2 - 15) , 0)
			.lineTo(pageSize.getWidth() / 2 - 15, 0)
			.stroke();
		
		//画X轴箭头
		canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
			.moveTo(pageSize.getWidth() / 2 - 25, -10)
			.lineTo(pageSize.getWidth() / 2 - 15, 0)
			.lineTo(pageSize.getWidth() / 2 - 25, 10).stroke()
			.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.MITER);
		
		//画Y轴，上下各缩进15
		canvas.moveTo(0 , -(pageSize.getHeight() / 2 - 15))
			.lineTo(0 , pageSize.getHeight() / 2 - 15)
			.stroke();
		
		//画Y轴箭头
		canvas.saveState()
			.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
			.moveTo(-10 , pageSize.getHeight() / 2 - 25)
			.lineTo(0 , pageSize.getHeight() / 2 - 15)
			.lineTo(10 , pageSize.getHeight() / 2 - 25).stroke()
			.restoreState();
		
		//画X轴
		for(int i = -((int)pageSize.getWidth() / 2 - 61) ; i < ((int)pageSize.getWidth() / 2 - 60); i += 40) {
			canvas.moveTo(i, 5).lineTo(i, -5);
		}
		
		for(int j = -((int)pageSize.getHeight() / 2 - 57); j < ((int)pageSize.getHeight() / 2 - 56); j += 40) {
			canvas.moveTo(5, j).lineTo(-5, j);
		}
		
		canvas.stroke();
		
		pdf.close();
	}

}
