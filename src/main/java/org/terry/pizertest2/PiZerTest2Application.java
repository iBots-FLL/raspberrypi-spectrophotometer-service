package org.terry.pizertest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
@RequestMapping("analyzer")
@EnableAsync
public class PiZerTest2Application {

	@Autowired
	private AnalysisService service;

	public static void main(String[] args) {
		SpringApplication.run(PiZerTest2Application.class, args);
	}

	@RequestMapping("results")
	public String getResults() throws Exception {
		System.out.println("Service ref is: " + service);
		service.analyze();
		return "Done";
	}

	@RequestMapping("test")
	public String test() {
		return "Mofongo: " + LocalDateTime.now();
	}
}
