package com.ietpune.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ietpune.dao.PaperDAO;
import com.ietpune.dao.StudentPaperDAO;

import com.ietpune.model.Paper;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;


@Service
public class StudentPaperService {
	@Autowired
	StudentPaperDAO studentPaperDAO;
	@Autowired
	QuestionService questionService;
	@Autowired
	PaperService paperService;
	@Autowired
	PaperDAO paperDAO;
	@Autowired
	StudentService studentService;

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
		Optional<StudentPaper> optStudPaper = getStudentPaper(id);
		if (optStudPaper.isPresent()) {
			optStudPaper.get().getPaper()
					.setQuestionList(questionService.getAllQuestionOfPaper(optStudPaper.get().getPaper()));
			return optStudPaper;
		}
		return null;
	}



	public List<StudentPaper> forGernaratedResult(int paperId) {
		Paper p = paperService.getPaper(paperId);
		
		List<StudentPaper> studentPaperList = studentPaperDAO.findAllByPaper(p);
	
		
		
		 List<Student>students=studentService.getAllStudentCourseWise(p.getSubject(). getCourse(),studentPaperList.stream().map(studPaper->studPaper.getStudent().getPrn()).collect(Collectors.toList())); 
		 students.forEach(stud->{
			 StudentPaper sp=new StudentPaper();
				sp.setMarks(0);
				sp.setResult("Failed");
				sp.setPaperDate(new java.sql.Date(new Date().getTime()));
				sp.setPaper(p);
				sp.setPresent(false);
				sp.setStudent(stud);
				sp.setStudentAnsMap(new HashMap<Integer, Character>());
				addStudentPaper(sp);
			 
		 });
		 studentPaperList = studentPaperDAO.findAllByPaper(p);
		
		 
		return studentPaperList;
	}

	public List<StudentPaper> getTopFiveStudentOfPaper(int paperId) {
		Paper p = paperService.getPaper(paperId);
		return studentPaperDAO.findTop5ByPaperAndResultOrderByMarksDesc(p, "Pass");
	}

	public int forPassStudent(int paperId) {
		Paper p = paperService.getPaper(paperId);
		return studentPaperDAO.countByPaperAndResult(p,"Pass");
	}

	public int forFailStudent(int paperId) {
		Paper p = paperService.getPaper(paperId);
		return studentPaperDAO.countByPaperAndResult(p,"Failed");
	}




}
