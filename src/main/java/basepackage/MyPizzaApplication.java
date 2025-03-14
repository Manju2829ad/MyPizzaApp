package basepackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "basepackage")  // Make sure this is at the root package of your project

public class MyPizzaApplication {

	public static void main(String[] args) {
		
		
		
//		System.out.println("DB URL: " + System.getenv("RENDER_DB_URL"));
//	    System.out.println("DB Username: " + System.getenv("DB_USER"));
	    
	    
		SpringApplication.run(MyPizzaApplication.class, args);

	

	           System.out.println("Hello");

	}

}
