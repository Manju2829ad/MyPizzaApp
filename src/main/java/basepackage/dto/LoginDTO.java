package basepackage.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import basepackage.model.User;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	
	private Long  lid;
	
	private LocalDateTime localDateTime;
	
	private User user;
}
