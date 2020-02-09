package com.ietpune.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ietpune.model.McqQuestion;
import com.ietpune.model.Paper;
import com.ietpune.model.Question;
import com.ietpune.model.StudentPaper;
import com.ietpune.service.PaperService;
import com.ietpune.service.QuestionService;
import com.ietpune.service.StudentPaperService;
import com.ietpune.service.StudentService;
@Controller
@RequestMapping("/Student/")
public class McqExamController {
	@Autowired
	public StudentService studentService;
	@Autowired
	public PaperService paperService;
	@Autowired
	public QuestionService questionService;
	@Autowired StudentPaperService studentPaperService;
	private static final Logger log = LoggerFactory.getLogger(McqExamController.class);

	@GetMapping("startExam/{id}")
	public String forStartExam(Model model, @PathVariable int id,Authentication auth) {
		Paper paper = paperService.getPaperById(id);
		
		if(studentPaperService.existsPaperOfStudent(paper,studentService.getStudentByPrn(auth.getName()).orElse(null))) {
			model.addAttribute("msg", "You have already appered for this exam.");
		}
		model.addAttribute("paperId", paper.getPaperId());
		model.addAttribute("paperCode", paper.getPaperCode());
		return "student/startExam";
	}
	@PostMapping("startExam/{id}")
	public String postStartExam(Model model, HttpSession session, @PathVariable int id,@RequestParam("paperId") String paperId,@RequestParam("paperCode") String paperCode,@RequestParam("code") String code) {
		if(paperCode.equals(code)) {
			Paper paper=paperService.getPaperById(Integer.parseInt(paperId));
			List<McqQuestion> qList=questionService.getMcqQuestion(paper);
			session.setAttribute("qList", qList);
			session.setAttribute("paperId", paperId);
			session.setAttribute("timeOver", System.currentTimeMillis()+(Integer.parseInt(paper.getPaperTiming())*60000));
			session.setAttribute("subject", paper.getSubject().getName());
			HashMap<Integer, Character> ansKey=new HashMap<>();
			session.setAttribute("ansKey", ansKey);
			return "redirect:/Student/mcqExam/1";
		}
		model.addAttribute("paperId", paperId);
		model.addAttribute("paperCode", paperCode);
		return "student/startExam";
	}
	@RequestMapping("mcqExam/{index}")
	public String getQuestionOfIndex(Model model,HttpSession session,@PathVariable int index){
		index-=1;
		List<McqQuestion> list=(List<McqQuestion>) session.getAttribute("qList");
		if(list==null)
			return "redirect:/Student";
		if(index==list.size())
			return "redirect:/Student/mcqExam/1";
		else if(index<0)
			return "redirect:/Student/mcqExam/"+list.size();
		long timeOver =(long)session.getAttribute("timeOver");
		long currentTime = System.currentTimeMillis();
		if( timeOver <= currentTime) {
			return "redirect:/Student/exam/submit";
		}
		list.get(index).setRead(true);
		long readed = list.stream().filter(McqQuestion::isRead).count();
		long mreview = list.stream().filter(McqQuestion::isMarkedReview).count();
		McqQuestion question=list.get(index);
		model.addAttribute("readed", readed);
		model.addAttribute("mReview", mreview);
		model.addAttribute("unReaded", list.size()-readed);
		model.addAttribute("list",list);
		model.addAttribute("question", question);
		model.addAttribute("index", index+1);
		model.addAttribute("remainingTime", timeOver-currentTime);
		return "student/mcqPaper";
		
	}
	@PostMapping("mcqExamPost/previous")
	public String postNextQue(Model model,HttpSession session,@RequestParam("index") String ind,@RequestParam("queId") String queId, @RequestParam("ans") Optional<String> ans ) {
		int index=Integer.parseInt(ind);
		return "redirect:/Student/mcqExam/"+(index-1);
	}
	@PostMapping("mcqExamPost/save")
	public String postSaveAns(Model model,HttpSession session,@RequestParam("index") String ind,@RequestParam("queId") String queId, @RequestParam("ans") Optional<String> ans ) {
		int index=Integer.parseInt(ind);
		List<McqQuestion> list=(List<McqQuestion>) session.getAttribute("qList");
		if(ans.isPresent()) {
			((HashMap<Integer, Character>)session.getAttribute("ansKey")).put(Integer.parseInt(queId), ans.get().charAt(0));
			list.get(index-1).setAns(ans.get().charAt(0));
			list.get(index-1).setMarkedReview(false);
		}
		return "redirect:/Student/mcqExam/"+(index+1);
	}
	@PostMapping("mcqExamPost/markedReview")
	public String postMarkedAns(Model model,HttpSession session,@RequestParam("index") String ind,@RequestParam("queId") String queId, @RequestParam("ans") Optional<String> ans ) {
		int index=Integer.parseInt(ind);
		List<McqQuestion> list=(List<McqQuestion>) session.getAttribute("qList");
		list.get(index-1).setMarkedReview(true);
		if(ans.isPresent()) {
			((HashMap<Integer, Character>)session.getAttribute("ansKey")).put(Integer.parseInt(queId), ans.get().charAt(0));
			list.get(index-1).setAns(ans.get().charAt(0));
			
		}
		return "redirect:/Student/mcqExam/"+(index+1);
	}
	@RequestMapping("exam/submit")
	public String getExamSubmit(Model model,HttpSession session,Principal principal) {
		HashMap<Integer, Character> ansKey=(HashMap<Integer, Character>)session.getAttribute("ansKey");
		if(ansKey==null)
			return "redirect:/";
		int paperId=Integer.parseInt((String)session.getAttribute("paperId"));
		int count=0;
		Paper paper=paperService.getPaperById(paperId);
		List<Question> ques=questionService.getAllQuestionOfPaper(paper);
		for(Question que:ques) {
			if(ansKey.get(que.getQueId())!=null && que.getCorrectOption()==ansKey.get(que.getQueId())) {
				count++;
			}
		}
		int numOfQuestion=ques.size();
		int pers=Math.round(((float)count/numOfQuestion)*100);
		String result;
		if(pers>=40)
			result="Pass";
		else
			result="Failed";
		StudentPaper sp=new StudentPaper();
		sp.setMarks(count);
		sp.setResult(result);
		sp.setPaperDate(new java.sql.Date(new Date().getTime()));
		sp.setPaper(paper);
		sp.setPresent(true);
		sp.setStudent(studentService.getStudentByPrn(principal.getName()).orElse(null));
		sp.setStudentAnsMap(ansKey);
		session.removeAttribute("qList");
		session.removeAttribute("paperId");
		session.removeAttribute("timeOver");
		session.removeAttribute("subject");
		session.removeAttribute("ansKey");
		studentPaperService.addStudentPaper(sp);
		model.addAttribute("total", numOfQuestion);
		model.addAttribute("marks", count);
		model.addAttribute("result",result);
		return "student/mcqPaperResult";
	}
}