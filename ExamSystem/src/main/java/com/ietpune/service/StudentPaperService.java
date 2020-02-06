package com.ietpune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.StudentPaperDAO;
import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;

@Service
public class StudentPaperService {
	@Autowired StudentPaperDAO studentPaperDAO;
	@Autowired QuestionService questionService;
	public StudentPaper addStudentPaper(StudentPaper studentPaper) {
		return studentPaperDAO.save(studentPaper);
	}
	public boolean existsPaperOfStudent(Paper paper, Student student) {
		return studentPaperDAO.existsByPaperAndStudent(paper, student);
	}
	public List<StudentPaper> getAllPaper(Student student) {
		return studentPaperDAO.findByStudent(student);
	}
	public Optional<StudentPaper> getStudentPaper(int studPaperId) {
		return studentPaperDAO.findById(studPaperId);
		
	}
	public List<StudentPaper> getTopFiveStudentOfPaper(Paper paper) {
		return studentPaperDAO.findTop5ByPaperAndResultOrderByMarksDesc(paper, "Pass");
	}
	public Optional<StudentPaper> getStudentPaperWithQuestion(int id) {
		Optional<StudentPaper> optStudPaper=getStudentPaper(id);
		if(optStudPaper.isPresent()) {
			optStudPaper.get().getPaper().setQuestionList(questionService.getAllQuestionOfPaper(optStudPaper.get().getPaper()));
			return optStudPaper;
		}
		return null;
	}
	
}
