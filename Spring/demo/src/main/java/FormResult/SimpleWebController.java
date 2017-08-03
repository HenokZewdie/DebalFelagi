package FormResult;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class SimpleWebController {

	
	 @RequestMapping(value="/form", method=RequestMethod.GET)
	    public String customerForm(Model model) {
	        model.addAttribute(new Customer());
	        return "form";
	    }
	 
	    @RequestMapping(value="/form", method=RequestMethod.POST)
	    public String customerSubmit(@ModelAttribute Customer customer, Model model) {
	         
	        model.addAttribute(customer);
	        /*String info = customer.getId()+ customer.getFirstname()+ customer.getLastname();
	        //log.info(info);
*/	         
	        return "result";
	    }
}
