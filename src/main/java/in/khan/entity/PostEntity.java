package in.khan.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="POST_TBL")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String title;
	@Lob
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;
	private String isActive;
	@Lob
	@Column(name = "content", columnDefinition = "LONGTEXT")
	private String content;
	@CreationTimestamp
	@Column(name = "CREATED_ON",updatable = false)
	private LocalDate createdOn;
	@UpdateTimestamp
	@Column(insertable  = false)
	private LocalDate updatedOn;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDtlsEntity user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	private List<Comment> comments;
}
