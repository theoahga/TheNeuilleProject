package com.theoahga.emergencyapi;

import com.theoahga.emergencyapi.util.PropertyUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class EmergencyApiApplication {
	public static void main(String[] args) throws IOException {
		PropertyUtils.load();
		SpringApplication.run(EmergencyApiApplication.class, args);
	}

}
