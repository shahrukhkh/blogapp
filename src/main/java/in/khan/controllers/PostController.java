package in.khan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.khan.binding.Post;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;
import in.khan.service.PostService;
import jakarta.servlet.http.HttpSession;
@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("/post")
	public String handlePost(@ModelAttribute("form") Post form,Model model)
	{
		
		boolean status = postService.doPost(form);
		if(status){
			model.addAttribute("sucMsg", "Blog Posted!");
		}
		else
		{
			model.addAttribute("errMsg", "Please Login");
		}
		return "post";
	}
	
	@GetMapping("/post")
	public String post(Post form,Model model)
	{   Integer userId=(Integer) httpSession.getAttribute("userId");
	
		if(userId!=null)
		{
			model.addAttribute("form", form);
			model.addAttribute("sucMsg", "");
			model.addAttribute("errMsg", "");
			return "post";
		}
		
		return "redirect:/error";
		
	}
	@GetMapping("/yourpost")
	public String getPost(Model model)
	{
		Integer  userId=(Integer) httpSession.getAttribute("userId");
		if(userId!=null)
		{
			List<PostEntity> cmt = postService.findPost(userId);
			model.addAttribute("list", cmt);
			return "post-list";
		}
		else
		{
			return "redirect:/error";
		}
		
	}
	@GetMapping("/allcomment")
	public String getCmt(Model model)
	{
		Integer  userId=(Integer) httpSession.getAttribute("userId");
		
		
		if(userId!=null)
		{
			List<Comment> cmt = postService.findAllCmt(userId);
			model.addAttribute("list", cmt);
			return "comment-list";
		}
		else
		{
			return "redirect:/error";
		}
			
		
		
		
	}
	@GetMapping("/edit/{id}")
	public String editPost(@PathVariable Integer id ,Model model)
	{
		PostEntity byPostId = postService.findByPostId(id);
		if(byPostId !=null)
		{
			Post form=new Post();
			
			form.setContent(byPostId.getContent());
			form.setDescription(byPostId.getDescription());
			form.setTitle(byPostId.getTitle());
			form.setPostId(byPostId.getPostId());
			model.addAttribute("form", form);
			return "post";
		}
		return "";
		 
	}
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable Integer id)
	{
		postService.deleteByPostId(id);
		return "redirect:/yourpost";
	}
	@GetMapping("/deleteCmt/{id}")
	public String deleteComment(@PathVariable Integer id)
	{
		postService.deleteCmtById(id);
		return "redirect:/allcomment";
	}
	
	@GetMapping("/error")
	public String errorPage()
	{
		return "error";
	}
}
