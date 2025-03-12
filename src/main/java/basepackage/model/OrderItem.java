package basepackage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JsonBackReference(value = "orderitem-orders")
	@JoinColumn(name="order_id")
	private  Order order;
	
	
	@ManyToOne
	@JsonBackReference(value="pizza-orderitems")
	@JoinColumn(name="pizza_id")
	private Pizza pizza;
	
	
	private int quantity;
	
}
