package it.failutti.damiano;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@SpringBootApplication
@Configuration
@EnableAutoConfiguration

public class RadioApplication {
	
	

	
	

	public static void main(String[] args) {
		SpringApplication.run(RadioApplication.class, args);
		
		
	}	

}
