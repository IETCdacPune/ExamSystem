package com.ietpune.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.model.PaperQuestion;
import com.ietpune.model.QuestionOption;

@Service
public class FileService {
	
	public List<PaperQuestion> fileToList(MultipartFile file) throws IOException{
		String extesion=FilenameUtils.getExtension(file.getOriginalFilename());
		List<PaperQuestion> questions=null;
		if(extesion.equalsIgnoreCase("xls")||extesion.equalsIgnoreCase("xlsx")) {
			questions= readDataFromExcel(file);
		}
		return questions;
	}

	private List<PaperQuestion> readDataFromExcel(MultipartFile file) throws IOException {
		Workbook workbook=getWorkBook(file);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator<Row>rows=sheet.iterator();
		rows.next();
		List<PaperQuestion> questions=new ArrayList<>();
		while(rows.hasNext()) {
			Row row =rows.next();
			PaperQuestion question= new PaperQuestion();
			if(row.getCell(0).getCellType()==Cell.CELL_TYPE_STRING) {
				question.setQuestion(row.getCell(0).getStringCellValue());
			}
			if(row.getCell(1).getCellType()==Cell.CELL_TYPE_STRING) {
				question.setCorrectOption(row.getCell(1).getStringCellValue().charAt(0));
			}
			if(row.getCell(2).getCellType()==Cell.CELL_TYPE_STRING) {
				question.setDescription(row.getCell(2).getStringCellValue());
			}
			List<QuestionOption> options=new ArrayList<>();
			for(int i=3;row.getCell(i)!=null && row.getCell(i).getCellType()==Cell.CELL_TYPE_STRING;i++) {
				QuestionOption option=new QuestionOption();
				
					option.setAnswer(row.getCell(i).getStringCellValue());
				
				option.setOption((char)(65+i-3));
				
				options.add(option);
			}
			System.out.println("\noptions:"+options);
			question.setOptionList(options);
			questions.add(question);
		}
		
		return questions; 
	}

	private Workbook getWorkBook(MultipartFile file) throws IOException {
		Workbook workbook=null;
		String extesion=FilenameUtils.getExtension(file.getOriginalFilename());
		if(extesion.equalsIgnoreCase("xlsx")) {
			workbook=new XSSFWorkbook(file.getInputStream());
		}else if(extesion.equalsIgnoreCase("xls")) {
			workbook=new HSSFWorkbook(file.getInputStream());
		}
		return workbook;
	}
}
