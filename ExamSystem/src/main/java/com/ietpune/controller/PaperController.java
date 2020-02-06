package com.ietpune.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ietpune.exception.ExcelFileException;
import com.ietpune.model.Course;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.Subject;
import com.ietpune.model.dto.PaperDTO;
import com.ietpune.model.dto.QuestionDTO;
import com.ietpune.service.CourseService;
import com.ietpune.service.FileService;
import com.ietpune.service.PaperService;
import com.ietpune.service.QuestionService;
import com.ietpune.service.SubjectService;

@Controller
public class PaperController {
	private static final String COURSE_LIST = "courseList";
	private static final String PAPER_QUESTION_EDIT = "paper/questionEdit";
	private static final String PAPER_ADD_PAPER = "paper/addPaper";
	private static final String ERRMSG = "errmsg";
	private static final String COMMAND = "command";
	private static final String PAPER_ADD_MORE_QUESTION="paper/allMoreQuestion";
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private FileService fileService;
	private Logger log=Logger.getLogger(PaperController.class);
	@GetMapping(value = "Admin/addPaper" )
	public String forAddPaperGet(Model model) {
		model.addAttribute(COURSE_LIST, courseService.getAllCourses());
		model.addAttribute(COMMAND, new PaperDTO());
		return PAPER_ADD_PAPER;
	}

	@PostMapping("Admin/addPaper")
	public String forAddPaperPost(Model model, @ModelAttribute(COMMAND) PaperDTO paperDTO,
			@RequestParam("file") MultipartFile file, BindingResult result) {
			
	boolean paperCode=paperService.forExsits(paperDTO.getPaperCode());
				
		if (result.hasErrors()) {
			model.addAttribute(COURSE_LIST, courseService.getAllCourses());
			model.addAttribute(COMMAND, new PaperDTO());
			return PAPER_ADD_PAPER;
		}		
		try {
			
			if(paperCode)
			{
				
				model.addAttribute(ERRMSG, "This paper code is already present");
				model.addAttribute(COURSE_LIST, courseService.getAllCourses());
				model.addAttribute(COMMAND, new PaperDTO());
				return PAPER_ADD_PAPER;
				
			}
			else
			{
			
			Paper paper = new Paper();
			paper.setPaperCode(paperDTO.getPaperCode());
			paper.setPaperTiming(paperDTO.getPaperTiming());
			paper.setSubject(subjectService.getSubjectById(Integer.parseInt(paperDTO.getSubject())));
			paper.setEnabled(false);
			List<Question> questions = fileService.fileToList(file, paper);
			if (questions == null || questions.isEmpty()) {
				model.addAttribute(ERRMSG, "Thier is an error in reading File...");
				return PAPER_ADD_PAPER;
			}
			paper.setQuestionList(questions);
			paper = paperService.addPaper(paper);
			if (paper == null) {
				model.addAttribute(ERRMSG, "Thier is an error in adding paper...");
				return PAPER_ADD_PAPER;
			}
			
			model.addAttribute("msg", "Paper Added successfully...");
			model.addAttribute(COURSE_LIST, courseService.getAllCourses());
			model.addAttribute(COMMAND, new PaperDTO());
			}
			return PAPER_ADD_PAPER;
		} catch (ExcelFileException e) {
			model.addAttribute(ERRMSG, e.getMessage());
			return PAPER_ADD_PAPER;
		} catch (IOException e) {
			return PAPER_ADD_PAPER;
		}
		
	}

	@GetMapping("/Admin/allPapers")
	public String forAllPaperGet(Model model) {
		List<Course> allCourse=courseService.getAllCourses();
		allCourse.forEach(
				(course)->{
					course.setSubjectList(subjectService.getAllSubjectByCourse(course));});

		if (!allCourse.isEmpty()) {
			model.addAttribute("courseList",allCourse);
		}
		return "paper/allPaper";
	}

	@GetMapping("/Admin/allQuestion/{id}")
	public String forAllQuestionGet(@PathVariable int id, Model model) {
		Paper paper = paperService.getPaperById(id);
		
		if (paper != null) {
			List<Question> allQue = questionService.getAllQuestionOfPaper(paper);
			model.addAttribute("paperId",id);
			model.addAttribute("enableStatus",paper.isEnabled());
			model.addAttribute("list", allQue);
		} else {
			model.addAttribute(ERRMSG, "Please select valid paper");
			
		}
		return "paper/allQuestion";
	}

	@GetMapping("/Admin/questionEdit/{id}")
	public String forQuestionEdit(@PathVariable int id, Model model) {
		Question question = questionService.getQuestionById(id);
		if (question != null) {
			question.setOptionList(questionService.getAllOptions(question));
			model.addAttribute(COMMAND, question);
			return PAPER_QUESTION_EDIT;
		} else {
			model.addAttribute(ERRMSG, "Please select valid question");
		}
		return "paper/allQuestion";
	}

	
	
	
	
	
	@PostMapping(value = "/Admin/questionEdit/{id}")
	public String forQuestionEditPost(@PathVariable int id, Model model,
			@Valid @ModelAttribute(COMMAND) QuestionDTO questionDTO, BindingResult result) {
		

		
		if (result.hasErrors()) {
			return PAPER_QUESTION_EDIT;
		}
		Question question = new Question();
		question.setQueId(questionDTO.getQueId());
		question.setFullQuestion(questionDTO.getFullQuestion());
		question.setDescription(questionDTO.getDescription());
		question.setCorrectOption(questionDTO.getCorrectOption());
		question.setPaper(questionDTO.getPaper());
		question = questionService.editQuestion(question);
		if (question == null) {
			model.addAttribute(ERRMSG, "Thier is an error in edittig question...");
			return PAPER_QUESTION_EDIT;
		}
		model.addAttribute("msg", "Subject Added successfully...");
		model.addAttribute(COMMAND, question);
		return PAPER_QUESTION_EDIT;
	}
	

	@RequestMapping("Admin/Paper/enable/{paperId}")
	public String forEnableStatusChange(@PathVariable("paperId")int paperId)
	{
		Paper paper=paperService.getPaper(paperId);
		paper.setEnabled(true);
		paper.setNumOfQuestion(questionService.getMcqQuestion(paper).size());
		paperService.addPaper(paper);
		
		
		return "redirect:/Admin/allPapers";
	}
	
	@RequestMapping("Admin/Paper/disable/{paperId}")
	public String forDisableStatusChange(@PathVariable("paperId")int paperId)
	{
		Paper paper=paperService.getPaper(paperId);
		paper.setEnabled(false);
		paper.setNewPaper(false);
		paperService.addPaper(paper);
		
		
		return "redirect:/Admin/allPapers";
	}
	@GetMapping("/Admin/Paper/addMoreQuestion/{paperId}")
	public String forAddMoreQuestion(@PathVariable("paperId")int paperId,Model model) throws IOException, ExcelFileException
	{
		
		return "paper/addMoreQuestion";
	}
	@PostMapping("/Admin/Paper/addMoreQuestion/{paperId}")
	public String forSaveMoreQuestion(@RequestParam("file") MultipartFile file,@PathVariable("paperId")int paperId,Model model) throws IOException, ExcelFileException
	{
		
		Paper paper=paperService.getPaperWithQuestions(paperId);
		log.info(paper+""+paper.getQuestionList());
		if(paper==null)
		{
			
			model.addAttribute(ERRMSG, "Thier is an error in reading File...");
			return "PAPER_ADD_MORE_QUESTION";
		}
	
		List<Question> questions = fileService.fileToList(file, paper);
		
		
	
		
		if (questions == null || questions.isEmpty()) {
			model.addAttribute(ERRMSG, "Thier is an error in reading File...");
			return PAPER_ADD_MORE_QUESTION;
		}
		paper.setQuestionList(questions);
		paper = paperService.addPaper(paper);
		
		
		
		return "redirect:/Admin/allQuestion/"+paperId;
	}

	
	
}
