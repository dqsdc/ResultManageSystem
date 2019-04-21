package com.qilinxx.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ResultManagerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultManagerSystemApplication.class, args);
	}

}
