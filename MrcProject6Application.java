package MrcProject6.MrcProject6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@ServletComponentScan
@Controller
@SpringBootApplication
public class MrcProject6Application {

	public static void main(String[] args) {
		SpringApplication.run(MrcProject6Application.class, args);
	}
	@GetMapping("/api")
	public String index() {
		return "index.html";
	}

}
