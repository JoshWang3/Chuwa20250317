package chuwa.backend.springbootdemo;

import chuwa.backend.springbootdemo.controller.MySecondController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootDemoApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);

		MySecondController controller = context.getBean(MySecondController.class);

	}
}
