package in.khan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.khan.binding.CommentBinding;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;
import in.khan.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@GetMapping("/")
	public String index(Model model)
	{
		
		List<PostEntity> list = indexService.getPost();
		model.addAttribute("list", list);
		return "index";
	}
	@GetMapping("/viewpost")
	public String viewPost(@RequestParam String id, Model model)
	{
		
		into(id, model);
		return "view-blog";
	}
	private void into(String id, Model model) {
		PostEntity postById = indexService.getPostById(Integer.parseInt(id));
		model.addAttribute("list", postById);
		List<Comment> cmt = indexService.findCmt(Integer.parseInt(id));
		model.addAttribute("cmt", cmt);
		model.addAttribute("form", new CommentBinding());
	}
	
	@PostMapping("/comment")
	public String comment(@ModelAttribute("form") CommentBinding form,@RequestParam String id,Model model)
	{
		indexService.doComment(form, Integer.parseInt(id));
		into(id, model);

		return "view-blog";
	}
}
