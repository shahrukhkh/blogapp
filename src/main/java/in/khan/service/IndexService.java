package in.khan.service;

import java.util.List;

import in.khan.binding.CommentBinding;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;

public interface IndexService {

	
	
	public List<PostEntity> getPost();
	public PostEntity getPostById(Integer id);
	public boolean doComment(CommentBinding form,Integer id);
	public List<Comment> findCmt(Integer id);
}


