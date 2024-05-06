package in.khan.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.khan.binding.CommentBinding;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;
import in.khan.repo.CommentRepo;
import in.khan.repo.PostRepo;
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Override
	public List<PostEntity> getPost() {
		List<PostEntity> all = postRepo.findAll();
		
		 List<PostEntity> collect = all.stream()
					.filter(e -> e.getIsActive().equals("A"))
					.collect(Collectors.toList());
		return collect;
	}
	
	
	@Override
	public PostEntity getPostById(Integer id) {
		PostEntity byId = postRepo.findById(id).get();
		
		return byId;
	}
	@Override
	public boolean doComment(CommentBinding form ,Integer id) {
		Comment comment =new Comment();
		BeanUtils.copyProperties(form, comment);
		PostEntity postEntity = postRepo.findById(id).get();
		comment.setPost(postEntity);
		comment.setIsActive("A");
		commentRepo.save(comment);
		
		return true;
	}
	@Override
	public List<Comment> findCmt(Integer id) {
		PostEntity postEntity = postRepo.findById(id).get();
		
		List<Comment> comments = postEntity.getComments();
		
		List<Comment> list = comments.stream()
		.filter(e -> e.getIsActive().equals("A"))
		.collect(Collectors.toList());
		
		return list;
	}
}
