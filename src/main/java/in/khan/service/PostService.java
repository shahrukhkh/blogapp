package in.khan.service;

import java.util.List;

import in.khan.binding.Post;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;

public interface PostService {

	
	public boolean doPost(Post form);
	public  List<PostEntity>  findPost(Integer  id);
	//public List<Comment> findCmt(Integer id);
	public List<Comment> findAllCmt(Integer id);
	
	public PostEntity findByPostId(Integer id);
	public void deleteByPostId(Integer id);
	public void deleteCmtById(Integer id);
	
}
