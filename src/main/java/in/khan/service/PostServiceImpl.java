package in.khan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.khan.binding.Post;
import in.khan.entity.Comment;
import in.khan.entity.PostEntity;
import in.khan.entity.UserDtlsEntity;
import in.khan.repo.CommentRepo;
import in.khan.repo.PostRepo;
import in.khan.repo.UserDtlsEntityRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserDtlsEntityRepo dtlsEntityRepo;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public boolean doPost(Post form) {
		PostEntity post = new PostEntity();
		BeanUtils.copyProperties(form, post);
		
		Integer userId=(Integer)httpSession.getAttribute("userId");
		if(userId!=null) {
			
			Optional<UserDtlsEntity> byId = dtlsEntityRepo.findById(userId);
			if(byId!=null)	
			{   UserDtlsEntity entity = byId.get();
				post.setUser(entity);
				post.setIsActive("A");
				postRepo.save(post);
				return true;
			}
		}
		

		return false;
	}
	
	@Override
	public List<PostEntity> findPost(Integer id) {
		UserDtlsEntity userDtlsEntity = dtlsEntityRepo.findById(id).get();
		List<PostEntity> post = userDtlsEntity.getPost();
		
		
		 List<PostEntity> collect = post.stream()
				.filter(e -> e.getIsActive().equals("A"))
				.collect(Collectors.toList());
		
		return collect;
	}
	@Override
	public List<Comment> findAllCmt(Integer id) {
		Optional<UserDtlsEntity> userDtlsEntity = 
				dtlsEntityRepo.findById(id);
		if(userDtlsEntity.isPresent())
		{
			UserDtlsEntity userDtlsEntity2 = userDtlsEntity.get();
			List<PostEntity> post = userDtlsEntity2.getPost();
			List<PostEntity> collect = post.stream()
					.filter(e -> e.getIsActive().equals("A"))
					.collect(Collectors.toList());
			if(!collect.isEmpty())
			   {
				List<Comment> newPostEntity = new ArrayList<Comment>();
				
				for(int i=0; i<collect.size(); i++)
				{
					PostEntity postEntity = collect.get(i);
					List<Comment> comments = postEntity.getComments();
					
					newPostEntity.addAll(comments);
				}
				   
				   List<Comment> list = newPostEntity.stream()
							.filter(e -> e.getIsActive().equals("A"))
							.collect(Collectors.toList());
				   
				   return list;
			   }
			
		}
		
		return null;
		
	}
	@Override
	public PostEntity findByPostId(Integer id) {
		Optional<PostEntity> optional = postRepo.findById(id);
		if(optional.isPresent())
		{
			PostEntity postEntity = optional.get();
			return postEntity;
					
		}
		return null;
	}
	
	@Override
	@Transactional
	public void deleteByPostId(Integer id) {
		
		//postRepo.deletePostById(id);
		Optional<PostEntity> optional = postRepo.findById(id);
		if(optional.isPresent())
		{
			PostEntity postEntity = optional.get();
			postEntity.setIsActive("D");
			postRepo.save(postEntity);
						
		}
		
		
	}
	@Override
	@Transactional
	public void deleteCmtById(Integer id) {
		Optional<Comment> byId = commentRepo.findById(id);
		if(byId.isPresent())
		{
			Comment comment = byId.get();
			comment.setIsActive("D");
			commentRepo.save(comment);
			
		}
		
	}
}
