package in.khan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.khan.entity.PostEntity;
@Repository
public interface PostRepo extends JpaRepository<PostEntity, Integer> {

//	@Modifying
//	@Query(value="DELETE FROM POST_TBL WHERE post_id =:id",nativeQuery = true)
//	public void deletePostById(@Param("id") Integer id);
}
