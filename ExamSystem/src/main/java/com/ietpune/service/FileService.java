package com.ietpune.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.exception.ExcelFileException;
import com.ietpune.model.Options;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.StudentPaper;

@Service
public class FileService {
	private static String UPLOADED_FOLDER = "./src/main/resources/static/Common/avatar/";
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
				}catch(ExcelFileException ex) {
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

	public void writeFile(List<StudentPaper> spaper, MultipartFile file) throws FileNotFoundException, IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet studentsSheet = workbook.createSheet("Students");
		studentsSheet.setDefaultColumnWidth(30);
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        font.setColor(HSSFColor.BLUE.index);
        style.setFont(font);
        Row row = studentsSheet.createRow(0);
        row.createCell(0).setCellValue("PRN");
        row.createCell(1).setCellValue("Name of Student");
        row.createCell(2).setCellValue("Marks");
     
        row.createCell(3).setCellValue("Result");
      
        row.createCell(3).setCellValue("Attendence");
       
 int  rowIndex=1;
        for(StudentPaper student : spaper){
            row = studentsSheet.createRow(rowIndex++);
            int cellIndex = 0;
            
            row.createCell(cellIndex++).setCellValue(student.getStudent().getPrn());
 
            row.createCell(cellIndex++).setCellValue(student.getStudent().getFirstName()+"  "+student.getStudent().getLastName());
            row.createCell(cellIndex++).setCellValue(student.getMarks());
 
     
            row.createCell(cellIndex++).setCellValue(student.getResult());
 
        
          }
       

        try (FileOutputStream outputStream = new FileOutputStream("Result1.xlsx")) {
  workbook.write(outputStream); }
 
	}

	public boolean isValidImg(MultipartFile multipart){
		if(multipart.getContentType().split("/")[0].equals("image") && (multipart.getSize()/1024)<500)
				return true;
		return false;
	}

	public String saveImg(MultipartFile file, String prn) throws IOException {
		byte[] bytes = file.getBytes();
		String newName=prn+"."+FilenameUtils.getExtension(file.getOriginalFilename());;
		Path path = Paths.get(UPLOADED_FOLDER + newName);
		Files.write(path, bytes);
		return newName;
	}
}
