package com.pedrodlf.iobuilders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IobuildersApplication{

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
/*
		// CON ESTO VEO LOS COMPONENTES DETECTADOS CUANDO SE LEVANTA EL CONTEXTO
		//No detecta el controller bajo esta traza:
		//Ignored because not a concrete top-level class controller
		applicationContext =
				new AnnotationConfigApplicationContext(IobuildersApplication.class);

		for (String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
		//RUN APP CODE
*/
		SpringApplication.run(IobuildersApplication.class, args);
	}

}
