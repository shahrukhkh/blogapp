package in.khan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.khan.entity.UserDtlsEntity;

@Repository
public interface UserDtlsEntityRepo extends JpaRepository<UserDtlsEntity, Integer>{

	
	public UserDtlsEntity findByUserEmail(String userEmail);
	@Query(value="Select * from USER_TBL where user_email=:email and user_pwd=:pwd" , nativeQuery = true)
	public UserDtlsEntity findByUserEmailAndUserPwd(@Param("email") String email,@Param("pwd") String pwd);
}
