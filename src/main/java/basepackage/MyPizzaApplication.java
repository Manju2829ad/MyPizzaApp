package basepackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "basepackage")  // Make sure this is at the root package of your project

public class MyPizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPizzaApplication.class, args);
	}

}
