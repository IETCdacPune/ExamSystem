package com.ietpune.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ietpune.exception.ExcelFileException;
import com.ietpune.model.Options;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.StudentPaper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FileService {
	private static String UPLOADED_FOLDER = "./src/main/resources/static/Common/avatar/";
	@Autowired private ServletContext servletContext;
	public List<Question> fileToList(MultipartFile file, Paper p) throws IOException, ExcelFileException {
		String extesion = FilenameUtils.getExtension(file.getOriginalFilename());
		List<Question> questions = null;
		if (extesion.equalsIgnoreCase("xls") || extesion.equalsIgnoreCase("xlsx")) {
			questions = readDataFromExcel(file, p);
		}
		return questions;
	}

	private List<Question> readDataFromExcel(MultipartFile file, Paper paper) throws IOException, ExcelFileException {
		Workbook workbook = getWorkBook(file);
		if (workbook != null) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			List<Question> questions = new ArrayList<>();
			while (rows.hasNext()) {
				Row row = rows.next();
				try {
					Question question = createQuetion(paper, row);
					questions.add(question);
				} catch (ExcelFileException ex) {
					break;
				}
			}
			return questions;
		}
		return Collections.emptyList();
	}

	private Question createQuetion(Paper paper, Row row) throws ExcelFileException {
		Question question = new Question();
		if (row.getCell(0) != null) {
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
				question.setFullQuestion(row.getCell(0).getStringCellValue());
			}
		} else {
			throw new ExcelFileException("Question not be empty.");
		}
		if (row.getCell(1) != null) {
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
				question.setCorrectOption(row.getCell(1).getStringCellValue().toUpperCase().charAt(0));
			}
		} else {
			throw new ExcelFileException("Question Correct Option not be empty.");
		}
		if (row.getCell(2) != null) {
			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
				question.setDescription(row.getCell(2).getStringCellValue());
			}
		} else {
			question.setDescription("None.");
		}
		List<Options> options = new ArrayList<>();
		for (int i = 3; row.getCell(i) != null; i++) {
			Options option = createOption(row, question, i);
			options.add(option);
		}
		question.setOptionList(options);
		question.setPaper(paper);
		return question;
	}

	private Options createOption(Row row, Question question, int i) {
		Options option = new Options();
		if (row.getCell(i).getCellType() != Cell.CELL_TYPE_STRING)
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
		option.setAnswer(row.getCell(i).getStringCellValue());
		option.setOpt((char) (62 + i));
		option.setQuestion(question);
		return option;
	}

	private Workbook getWorkBook(MultipartFile file) throws IOException {
		Workbook workbook = null;
		String extesion = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extesion.equalsIgnoreCase("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extesion.equalsIgnoreCase("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}
		return workbook;
	}

	public boolean isValidImg(MultipartFile multipart) {
		if (multipart.getContentType().split("/")[0].equals("image") && (multipart.getSize() / 1024) < 500)
			return true;
		return false;
	}

	public String saveImg(MultipartFile file, String prn) throws IOException {
		byte[] bytes = file.getBytes();
		String newName = prn + "." + FilenameUtils.getExtension(file.getOriginalFilename());
		;
		Path path = Paths.get(UPLOADED_FOLDER + newName);
		Files.write(path, bytes);
		return newName;
	}

	public boolean createExcel(List<StudentPaper> studentPaperList, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response) {

		String filePath = servletContext.getRealPath("/resources/paper/");
		File file = new File(filePath);
		String fileName = String.valueOf(studentPaperList.get(0).getPaper().getPaperCode());
		boolean exits = new File(filePath).exists();
		if (!exits) {
			new File(filePath).mkdirs();

		}
		try {

			FileOutputStream outputStream = new FileOutputStream(file + "/" + fileName + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("student");
			sheet.setDefaultColumnWidth(30);
			
			
			/*
			 * HSSFFont font= workbook.createFont(); font.setFontHeightInPoints((short)10);
			 * font.setFontName("Arial"); font.setColor(IndexedColors.BLACK.getIndex());
			 * font.setBoldweight((short)10); font.setItalic(true);
			 * font.setFontHeight((short)40);
			 */
			
			
			
			HSSFCellStyle headerCellStyle1 = workbook.createCellStyle();
			headerCellStyle1.setFillForegroundColor(HSSFColor.WHITE.index);
			headerCellStyle1.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		//headerCellStyle1.setFont(font);
		
			
		
			
			headerCellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFRow headerRow1 = sheet.createRow(1);
			HSSFCell paperCode=headerRow1.createCell(0+1);
			paperCode.setCellValue("Paper Code:"+studentPaperList.get(0).getPaper().getPaperCode());
			paperCode.setCellStyle(headerCellStyle1);
			
			headerCellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFRow headerRow2 = sheet.createRow(2);
			HSSFCell subject=headerRow2.createCell(0+1);
			subject.setCellValue("Subject Name:"+studentPaperList.get(0).getPaper().getSubject().getName());
			subject.setCellStyle(headerCellStyle1);
			
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFRow headerRow = sheet.createRow(3);
			HSSFCell prn = headerRow.createCell(0);
			prn.setCellValue("prn");
			prn.setCellStyle(headerCellStyle);
			HSSFCell firstName = headerRow.createCell(1);
			firstName.setCellValue("First Name");
			firstName.setCellStyle(headerCellStyle);
			HSSFCell lastName = headerRow.createCell(2);
			lastName.setCellValue("Last Name");
			lastName.setCellStyle(headerCellStyle);
			HSSFCell mark = headerRow.createCell(3);
			mark.setCellValue("Marks");
			mark.setCellStyle(headerCellStyle);
			HSSFCell result = headerRow.createCell(4);
			result.setCellValue("Result");
			result.setCellStyle(headerCellStyle);
			HSSFCell attendence = headerRow.createCell(5);
			attendence.setCellValue("Attedecnce");
			attendence.setCellStyle(headerCellStyle);
			int i = 4;

			for (StudentPaper studentPaper : studentPaperList) {

				HSSFRow bodyRow = sheet.createRow(i);
				HSSFCellStyle bodysStyle = workbook.createCellStyle();
				bodysStyle.setFillForegroundColor(HSSFColor.WHITE.index);
				HSSFCell prnValue = bodyRow.createCell(0);
				prnValue.setCellValue(studentPaper.getStudent().getPrn());
				prnValue.setCellStyle(bodysStyle);

				HSSFCell firstNameValue = bodyRow.createCell(1);
				firstNameValue.setCellValue(studentPaper.getStudent().getFirstName());
				firstNameValue.setCellStyle(bodysStyle);

				HSSFCell lastNameValue = bodyRow.createCell(2);
				lastNameValue.setCellValue(studentPaper.getStudent().getLastName());
				lastNameValue.setCellStyle(bodysStyle);

				HSSFCell markValue = bodyRow.createCell(3);
				markValue.setCellValue(studentPaper.getMarks());
				markValue.setCellStyle(bodysStyle);

				HSSFCell resultValue = bodyRow.createCell(4);
				resultValue.setCellValue(studentPaper.getResult());
				resultValue.setCellStyle(bodysStyle);
				HSSFCell attedenceValue = bodyRow.createCell(5);
				attedenceValue.setCellValue(studentPaper.isPresent());
				attedenceValue.setCellStyle(bodysStyle);
				i++;

			}

			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
		
	public void fileDownload(String fullPath, HttpServletResponse response, String filename) {
			// TODO Auto-generated method stub
			File file = new File(fullPath);

			final int bufferSize = 4096;
			if (file.exists()) {
				try {
					FileInputStream fin = new FileInputStream(file);
					String mineType = servletContext.getMimeType(fullPath);
					response.setContentType(mineType);
					response.setHeader("content.dispostion", "attachment;filename=" + filename);
					OutputStream os = response.getOutputStream();
					byte[] buffer = new byte[bufferSize];
					int byteRead = 1;
					while ((byteRead = fin.read(buffer)) != -1)

					{

						os.write(buffer, 0, byteRead);

					}
					

					fin.close();
					os.close();

				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}

	

	public boolean createPdf(List<StudentPaper> studentPaperList, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response) {
		Document doc = new Document(PageSize.A4, 15, 15, 40, 30);
		try {
			String filePath = servletContext.getRealPath("/resources/paper/");
			String fileName = String.valueOf(studentPaperList.get(0).getPaper().getPaperCode());
			File file = new File(filePath);
			boolean exists= new File(filePath).exists();
			if (!exists) {
				exists = new File(filePath).mkdirs();

			}
			PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream(file + "/" + fileName + ".pdf"));

			writer.open();
			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Paragraph paragraph = new Paragraph("All Students", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			doc.add(paragraph);

			PdfPTable pTable = new PdfPTable(6);
			pTable.setWidthPercentage(100);
			pTable.setSpacingBefore(10f);
			pTable.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLUE);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLUE);

			float[] clounmWidths = { 2f, 2f, 2f, 2f,2f,2f };
			pTable.setWidths(clounmWidths);

			PdfPCell prn = new PdfPCell(new Paragraph("PRN", tableHeader));
			prn.setBorderColor(BaseColor.BLUE);
			prn.setPaddingLeft(10);
			prn.setHorizontalAlignment(Element.ALIGN_CENTER);
			prn.setVerticalAlignment(Element.ALIGN_CENTER);
			prn.setBackgroundColor(BaseColor.GRAY);
			prn.setExtraParagraphSpace(5f);
			pTable.addCell(prn);

			PdfPCell firstName = new PdfPCell(new Paragraph("First Name", tableHeader));
			firstName.setBorderColor(BaseColor.BLUE);
			firstName.setPaddingLeft(10);
			firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
			firstName.setVerticalAlignment(Element.ALIGN_CENTER);
			firstName.setBackgroundColor(BaseColor.GRAY);
			firstName.setExtraParagraphSpace(5f);
			pTable.addCell(firstName);

			PdfPCell lastName = new PdfPCell(new Paragraph("Last Name", tableHeader));
			lastName.setBorderColor(BaseColor.BLUE);
			lastName.setPaddingLeft(10);
			lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
			lastName.setVerticalAlignment(Element.ALIGN_CENTER);
			lastName.setBackgroundColor(BaseColor.GRAY);
			lastName.setExtraParagraphSpace(5f);
			pTable.addCell(lastName);

			PdfPCell marks = new PdfPCell(new Paragraph("Marks", tableHeader));
			marks.setBorderColor(BaseColor.BLUE);
			marks.setPaddingLeft(10);
			marks.setHorizontalAlignment(Element.ALIGN_CENTER);
			marks.setVerticalAlignment(Element.ALIGN_CENTER);
			marks.setBackgroundColor(BaseColor.GRAY);
			marks.setExtraParagraphSpace(5f);
			pTable.addCell(marks);

			PdfPCell result = new PdfPCell(new Paragraph("Result", tableHeader));
			result.setBorderColor(BaseColor.BLUE);
			result.setPaddingLeft(10);
			result.setHorizontalAlignment(Element.ALIGN_CENTER);
			result.setVerticalAlignment(Element.ALIGN_CENTER);
			result.setBackgroundColor(BaseColor.GRAY);
			result.setExtraParagraphSpace(5f);
			pTable.addCell(result);

			PdfPCell attendence = new PdfPCell(new Paragraph("Attendence", tableHeader));
			attendence.setBorderColor(BaseColor.BLUE);
			attendence.setPaddingLeft(10);
			attendence.setHorizontalAlignment(Element.ALIGN_CENTER);
			attendence.setVerticalAlignment(Element.ALIGN_CENTER);
			attendence.setBackgroundColor(BaseColor.GRAY);
			attendence.setExtraParagraphSpace(5f);
			pTable.addCell(attendence);

			for (StudentPaper studentPaper : studentPaperList) {

				PdfPCell prnValue = new PdfPCell(new Paragraph(studentPaper.getStudent().getPrn(), tableBody));
				prnValue.setBorderColor(BaseColor.BLACK);
				prnValue.setPaddingLeft(10);
				prnValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				prnValue.setVerticalAlignment(Element.ALIGN_CENTER);
				prnValue.setBackgroundColor(BaseColor.WHITE);
				prnValue.setExtraParagraphSpace(5f);
				pTable.addCell(prnValue);

				PdfPCell firstNameValue = new PdfPCell(
						new Paragraph(studentPaper.getStudent().getFirstName(), tableBody));
				firstNameValue.setBorderColor(BaseColor.BLACK);
				firstNameValue.setPaddingLeft(10);
				firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				firstNameValue.setBackgroundColor(BaseColor.WHITE);
				firstNameValue.setExtraParagraphSpace(5f);
				pTable.addCell(firstNameValue);

				PdfPCell lastNameValue = new PdfPCell(
						new Paragraph(studentPaper.getStudent().getLastName(), tableBody));
				lastNameValue.setBorderColor(BaseColor.BLACK);
				lastNameValue.setPaddingLeft(10);
				lastNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				lastNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				lastNameValue.setBackgroundColor(BaseColor.WHITE);
				lastNameValue.setExtraParagraphSpace(5f);
				pTable.addCell(lastNameValue);

				PdfPCell marksValue = new PdfPCell(new Paragraph(String.valueOf(studentPaper.getMarks()), tableBody));
				marksValue.setBorderColor(BaseColor.BLACK);
				marksValue.setPaddingLeft(10);
				marksValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				marksValue.setVerticalAlignment(Element.ALIGN_CENTER);
				marksValue.setBackgroundColor(BaseColor.WHITE);
				marksValue.setExtraParagraphSpace(5);
				pTable.addCell(marks);
				PdfPCell resultValue = new PdfPCell(new Paragraph(studentPaper.getResult(), tableBody));
				resultValue.setBorderColor(BaseColor.BLACK);
				resultValue.setPaddingLeft(10);
				resultValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				resultValue.setVerticalAlignment(Element.ALIGN_CENTER);
				resultValue.setBackgroundColor(BaseColor.WHITE);
				resultValue.setExtraParagraphSpace(5f);
				pTable.addCell(resultValue);

				PdfPCell attendenceValue = new PdfPCell(
						new Paragraph(String.valueOf(studentPaper.isPresent()), tableBody));
				attendenceValue.setBorderColor(BaseColor.BLACK);
				attendenceValue.setPaddingLeft(10);
				attendenceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				attendenceValue.setVerticalAlignment(Element.ALIGN_CENTER);
				attendenceValue.setBackgroundColor(BaseColor.WHITE);
				attendenceValue.setExtraParagraphSpace(5f);
				pTable.addCell(attendenceValue);

			}
			doc.add(pTable);
			doc.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
