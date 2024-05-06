package in.khan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.khan.binding.Login;
import in.khan.binding.SignUp;
import in.khan.service.UserService;
@Controller
public class UserController {

	@Autowired
	private UserService service;
	@GetMapping("/signup")
	public String signUp(SignUp form,Model model)
	{
		model.addAttribute("form", form);
		
		return "SignUp";
	}
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("form") SignUp form,Model model)
	{
		boolean status = service.saveUser(form);
		if(status)
		{
			model.addAttribute("sucMsg", "Your Account Is Created!");
		}
		else {
			model.addAttribute("errMsg", "Already have Account With this Email!");
		}
		return "SignUp";
	}
	@GetMapping("/login")
	public String login(Login form,Model model)
	{
		model.addAttribute("form", form);
		return "Login";
	}
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute("form") Login form,Model model)
	{
		boolean status = service.doLogin(form);
		if(status==false)
		{
			model.addAttribute("errMsg","Invalid Credentials!");
			return "Login";
		}
		return "redirect:/yourpost";
	}
	@GetMapping("/dashboard")
	public String dashboard()
	{
		
		return "dashboard";
	}
	
	
	
	
	@GetMapping("/view")
	public String viewBlog()
	{
		return "view-blog";
	}
	@GetMapping("/logout")
	public String logout()
	{
		service.doLogout();
		return "redirect:/";
	}
	
}
