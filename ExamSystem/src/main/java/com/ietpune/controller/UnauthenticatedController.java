package com.ietpune.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ietpune.dao.ConfirmationTokenRepository;
import com.ietpune.dao.UserDAO;
import com.ietpune.model.ConfirmationToken;
import com.ietpune.model.Course;
import com.ietpune.model.Student;
import com.ietpune.model.User;
import com.ietpune.model.dto.StudentDTO;
import com.ietpune.service.CourseService;
import com.ietpune.service.EmailSenderService;
import com.ietpune.service.FileService;
import com.ietpune.service.QuestionService;
import com.ietpune.service.SecurityQuestionService;
import com.ietpune.service.StudentService;

@Controller
public class UnauthenticatedController {
	private static final String ERRMSG = "errmsg";
	private static final String SIGNUP = "signup";
	@Autowired private StudentService studentService;
	@Autowired private QuestionService questionService;
	@Autowired private CourseService courseService;
	@Autowired private EmailSenderService emailSenderService;
	@Autowired private UserDAO userDAO;
	@Autowired private FileService fileService;
	@Autowired private SecurityQuestionService securityQuestionService;
	@Autowired private ConfirmationTokenRepository confirmationTokenRepository;
	@Value("${examSystem.URL}")
	private String systemUrl;
	@Autowired private Map<String, Boolean> myMap;
	@GetMapping("/")
	public String forIndex() {
		return "index1";
	}
	@GetMapping(value = "/signout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/signin?logout=true";
	}

	@GetMapping("/signin")
	public String forLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessge = null;
		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}		
		model.addAttribute("errorMessge", errorMessge);
		return "signin";
	}

	@GetMapping("/signup")
	public String forRegistration(Model model) {
		model.addAttribute("qList", questionService.getAllSecurityQuestion());
		model.addAttribute("command", new StudentDTO());
		return SIGNUP;
	}

	@PostMapping("/signup")
	public String forSingupPost(Model model, @Valid @ModelAttribute("command") StudentDTO studentDTO, BindingResult result,HttpSession hs,HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("qList", questionService.getAllSecurityQuestion());
			return SIGNUP;
		}
		
		if (!studentDTO.getPassword().equals(studentDTO.getConformPass())) {
			model.addAttribute("qList", questionService.getAllSecurityQuestion());
			model.addAttribute(ERRMSG, "Password and conform password not same...");
			return SIGNUP;
		}
		Optional<Course> optCourse = courseService.getCourseByCode(studentDTO.getCourseCode());
		if(!optCourse.isPresent()){
			model.addAttribute("qList", questionService.getAllSecurityQuestion());
			model.addAttribute(ERRMSG, "Wrong course code...");
			return SIGNUP;
		}
		if(studentService.findEmail(studentDTO.getEmailId())) {
			model.addAttribute("qList", questionService.getAllSecurityQuestion());
			model.addAttribute(ERRMSG, "This email id already register...");
			return SIGNUP;
		}
		
		if(studentService.findPrn(studentDTO.getPrn())) {
			model.addAttribute("qList", questionService.getAllSecurityQuestion());
			model.addAttribute(ERRMSG, "This PRN already register...");
			return SIGNUP;
		}
		Student student=new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setEmailId(studentDTO.getEmailId());
		student.setPassword(studentDTO.getPassword());
		student.setPrn(studentDTO.getPrn());
		student.setCourse(optCourse.get());
		student.setGender(studentDTO.getGender()=='m'?true:false);
		if(studentDTO.getGender()=='m'?true:false)
			student.setImgUrl("male.jpg");
		else
			student.setImgUrl("female.jpg");
		student.setSecurityQeustion(
				securityQuestionService.getQuestionById(
						studentDTO.getSqId()).orElse(null));
		student.setSecurityAnswer(studentDTO.getSecurityAnswer());
		student = studentService.save(student);
		if (student == null) {
			model.addAttribute(ERRMSG, "Thier is an some error in registration...");
			return "signup?error";
		}
		ConfirmationToken confirmationToken = new ConfirmationToken((User)student);
		
		confirmationTokenRepository.save(confirmationToken);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(student.getEmailId());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("info@ietpune.com");
		mailMessage.setText("To confirm your account, please click here : "
		+systemUrl+"/confirm-account?token="+confirmationToken.getConfirmationToken());
		
		emailSenderService.sendEmail(mailMessage);
		model.addAttribute("msg", "You are register successfully...<br>Pleace check email for activate your account.");
		model.addAttribute("command", new StudentDTO());	
		return SIGNUP;

	}
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null)
		{
			User user = studentService.getByPrn(token.getUser().getPrn()).get();
			user.setEnabled(true);
			userDAO.save(user);
			confirmationTokenRepository.delete(token);
			model.addAttribute("successMessge","Your Account is activated now you can login.");
		}
		else
		{
			model.addAttribute("errorMessge","The link is invalid or broken!");
		}
		
		return "/signin";
	}
	
	@GetMapping("/emailForPasswordReset")
	public String foremailPasswordReset(Model model) {
		return "emailForPassReset";
	}
	@PostMapping("/emailForPasswordReset")
	public String foremailPasswordResetPost(Model model,@RequestParam("email")String email) {
		Optional<Student> optStud = studentService.getStudentByEmail(email);
		if(optStud.isPresent()) {
			model.addAttribute("question",optStud.get().getSecurityQeustion().getQuestion());
			model.addAttribute("email",optStud.get().getEmailId());
			return "securityQuesPassReset";
		}
		model.addAttribute("errorMessge","Please Enter valid email<br>This email is not register.");
		return "emailForPassReset";
	}
	@PostMapping("/resetPassword")
	public String forResetPassword(Model model,@RequestParam("answer")String ans,@RequestParam("email")String email) {
		Optional<Student> optStud = studentService.getStudentByEmail(email);
		if(optStud.isPresent()) {
			if(ans.equals(optStud.get().getSecurityAnswer())) {
				Student stud=optStud.get();
				String pass=fileService.genrateRandomeCode(8);
				stud.setPassword(pass);
				studentService.changePass(stud);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(stud.getEmailId());
				mailMessage.setSubject("Password reset for exam system");
				mailMessage.setFrom("info@ietpune.com");
				mailMessage.setText("Your password is reset.\n "+pass+" is your new password");
				
				emailSenderService.sendEmail(mailMessage);
				model.addAttribute("successMessge", "Password is reset.<br>Please check mail for new password");
				return "redirect:/signin";
			}
			model.addAttribute("errorMessge", "Wrong answer...");
			model.addAttribute("question",optStud.get().getSecurityQeustion().getQuestion());
			model.addAttribute("email",email);
			return "securityQuesPassReset";
		}
		model.addAttribute("errorMessge","Please Enter valid email<br>This email is not register.");
		return "emailForPassReset";
	}
}
