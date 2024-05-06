package in.khan.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Table(name = "USER_TBL")
@Entity
@Getter
@Setter
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPwd;
	@CreationTimestamp
	private LocalDate createdDate;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	private List<PostEntity> post;
}
