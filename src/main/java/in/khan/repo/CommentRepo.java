package in.khan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.khan.entity.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	//@Modifying
	//@Query(value="DELETE FROM COMMENT_TBL WHERE id =:id",nativeQuery = true)
	//public void deleteCmtById(@Param("id") Integer id);
}
