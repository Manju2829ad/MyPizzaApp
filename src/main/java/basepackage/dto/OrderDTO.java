package basepackage.dto;

import java.time.LocalDateTime;
import java.util.List;

import basepackage.model.OrderItem;
import basepackage.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	

	private Long id;
	private User user;
	private List<OrderItem> orderItem;
 	private LocalDateTime orderDate;
	
	
	
}
