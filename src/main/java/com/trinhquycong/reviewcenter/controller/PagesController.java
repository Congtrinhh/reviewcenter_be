package com.trinhquycong.reviewcenter.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trinhquycong.reviewcenter.dto.UserRegistrationForm;
import com.trinhquycong.reviewcenter.exception.UserAlreadyExistAuthenticationException;
import com.trinhquycong.reviewcenter.service.MessageService;
import com.trinhquycong.reviewcenter.service.UserService;

@RestController
public class PagesController {

	private final Logger logger = LogManager.getLogger(getClass());

	@Resource
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "error") String error,
			@RequestParam(required = false, value = "logout") String logout,
			@RequestParam(required = false, value = "errorCode") String errorCode)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();

		if (error != null) {
			mav.addObject("css", "danger");
			mav.addObject("msg", error);
		} else if (logout != null) {
			mav.addObject("css", "success");
			mav.addObject("msg", messageService.getMessage("message.logout." + logout));
		}

		mav.addObject("title", "Login Page");
		mav.setViewName("login"); // define view name
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return new ModelAndView("register", "userRegistrationForm", new UserRegistrationForm());
	}

	@PostMapping("/register")
	public ModelAndView registerUser(@Valid UserRegistrationForm userRegistrationForm, BindingResult result,
			final HttpServletRequest request, RedirectAttributes attributes) {
		ModelAndView mav = new ModelAndView("register");

		if (!result.hasErrors()) {
			try {
				userService.registerNewUser(userRegistrationForm);
				attributes.addFlashAttribute("css", "success");
				attributes.addFlashAttribute("msg", messageService.getMessage("message.regSucc"));
				mav = new ModelAndView("redirect:/login");
			} catch (UserAlreadyExistAuthenticationException e) {
				// TODO: handle exception
				logger.error(e);
				result.rejectValue("email", "message.regError");
			}
		}
		return mav;
	}
	
	@GetMapping({ "/", "/home" })
	public ModelAndView home(@RequestParam(value = "view", required = false) String view) {
		logger.info("Entering home page");
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("title", "Home");
		mav.addObject("view", view);
		return mav;
	}
}
