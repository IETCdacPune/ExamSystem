package com.ietpune.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.ietpune.model.Course;
import com.ietpune.model.Student;
import com.ietpune.model.StudentPaper;
import com.ietpune.service.CourseService;
import com.ietpune.service.FileService;
import com.ietpune.service.StudentPaperService;
import com.ietpune.service.StudentService;
@Controller
public class AdminController {
	@Autowired private StudentService studentService;
	@Autowired  private FileService fileService;
	@Autowired private StudentPaperService studentPaperService;
	@Autowired private CourseService courseService;
	@Autowired private ServletContext servletContext;
	Logger log = Logger.getLogger(AdminController.class);

	@GetMapping("Admin")
	public String forAdminDashboard() {
		return "admin/adminDashboard";
	}

	@GetMapping("Admin/listOfStudent")
	public String forListOfStudent(Model model) {
		List<Student> studentAllList = studentService.getAllStudentList();

		model.addAttribute("studentAllList", studentAllList);
		return "admin/listOfStudent";
	}

	@GetMapping("Admin/listOfPredacStudent")
	public String forListOfPredacStudent(Model model) {
		List<Student> studentAllPredacList = studentService.getAllPredacStudentList();
		model.addAttribute("studentAllList", studentAllPredacList);
		return "admin/listOfStudent";
	}

	@GetMapping("Admin/genratedResult")
	public String forGenratedResult(Model model) {
		List<Course> courseList = courseService.getAllCoursesWithEagerLoad();

		if (!courseList.isEmpty()) {
			model.addAttribute("courseList", courseList);
		}
		return "admin/generatedResult";
	}

	@RequestMapping("Admin/viewForResult/{paperId}")
	public String forViewResult(@PathVariable("paperId") int paperId, MultipartFile file, Model model)
			throws FileNotFoundException, IOException {

		List<StudentPaper> studentPaperList = studentPaperService.forGernaratedResult(paperId);

		List<StudentPaper> topFive = studentPaperService.getTopFiveStudentOfPaper(paperId);
		int pass = studentPaperService.forPassStudent(paperId);
		int failed = studentPaperService.forFailStudent(paperId);

		model.addAttribute("topFive", topFive);
		model.addAttribute("pass", pass);
		model.addAttribute("failed", failed);
		model.addAttribute("studentResultList", studentPaperList);
		return "admin/viewForResult";
	}

	@GetMapping("/Admin/downloadExcelResult/{paperId}")
	public void forDownloadExcel(@PathVariable("paperId") int paperId, HttpServletRequest request,
			HttpServletResponse response) {
		List<StudentPaper> studentPaperList = studentPaperService.forGernaratedResult(paperId);

		boolean isFlag = fileService.createExcel(studentPaperList, servletContext, request, response);
		String fileName = String.valueOf(studentPaperList.get(0).getPaper().getPaperCode());
		

		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/paper/" + fileName + ".xls");

			fileService.fileDownload(fullPath, response, fileName + ".xls");

		}

	}

	@GetMapping("/Admin/downloadPdfResult/{paperId}")
	public void forDownloadPdf(@PathVariable("paperId") int paperId, HttpServletRequest request,
			HttpServletResponse response) {
		List<StudentPaper> studentPaperList = studentPaperService.forGernaratedResult(paperId);

		boolean isFlag = fileService.createPdf(studentPaperList, servletContext, request, response);
		String fileName = String.valueOf(studentPaperList.get(0).getPaper().getPaperCode());
		if (isFlag) {

			String fullPath = request.getServletContext().getRealPath("/resources/paper/" + fileName + ".pdf");
			fileService.fileDownload(fullPath, response, fileName +".pdf");
		}

	}

}
