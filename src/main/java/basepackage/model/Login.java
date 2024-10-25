package basepackage.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="login_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lid;
	
	
	@Column(name="login_time")
	private  LocalDateTime loginTime;
	
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private  User user;
	
	
}
