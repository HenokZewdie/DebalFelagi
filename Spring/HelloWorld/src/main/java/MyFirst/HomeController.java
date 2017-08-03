package MyFirst;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

	public String hello(){
		return "First Hello";
	}
}
