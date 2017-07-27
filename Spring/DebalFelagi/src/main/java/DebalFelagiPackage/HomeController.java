package DebalFelagiPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import javax.mail.internet.InternetAddress;



@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private CustomerRepository customerRepository;


    String emailSession;
    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute(new User());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        emailSession = user.getEmail();
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        user = userRepository.findByUsername(user.getUsername());
        model.addAttribute("register1", user);
        return "/login";
    }

    @RequestMapping(value = "/houseregister", method = RequestMethod.GET)
    public String houseGet(Model model){
        model.addAttribute("house", new House());
        return "houseregister";
    }

    @RequestMapping(value = "/houseregister", method = RequestMethod.POST)
    public String housePOST(@ModelAttribute("house") House house, Model model, Principal principal){
        house.setDate(new Date());
        house.setUsername(principal.getName());
        houseRepository.save(house);
        model.addAttribute(new House());
        return "redirect:/houseregister";
    }

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String displayTemp(Model model, Principal principal, User user, House house){
        model.addAttribute("house", new House());
        user = userRepository.findByUsername(principal.getName());
        long min = user.getZipCode() - 50;
        long max = user.getZipCode() + 50;
        List<House> zipList = new ArrayList<>();
        for (long i = min; i<=max; i++){
            List<House> houseList = houseRepository.findByZipCode(i);
            zipList.addAll(houseList);
        }

        model.addAttribute("houseList", zipList);
        return "/displaytemplate";
    }

    @RequestMapping(value = "/searchstate", method = RequestMethod.GET)
    public String searchstateGet(Model model){
        model.addAttribute("house", new House());
        return "/searchstate";
    }
    @RequestMapping(value = "/searchstate", method = RequestMethod.POST)
    public String searchstate(Model model,  House house){
        model.addAttribute("house", new House());
        List<House> zipList = houseRepository.findByState(house.getState());
        model.addAttribute("houseList", zipList);
        return "/displaytemplate";
    }

    @RequestMapping(value = "/searchzip", method = RequestMethod.GET)
    public String searchzipGet(Model model){
        model.addAttribute("house", new House());
        return "/searchzip";
    }
    @RequestMapping(value = "/searchzip", method = RequestMethod.POST)
    public String searchzip(Model model,  House house){
        model.addAttribute("house", new House());
        long min = house.getZipCode() - 10;
        long max = house.getZipCode() + 10;
        List<House> zipSearch = new ArrayList<>();
        for(long i=min;i<=max;i++){
            List<House> zipList = houseRepository.findByZipCode(i);
            zipSearch.addAll(zipList);
        }
        model.addAttribute("houseList", zipSearch);
        return "/displaytemplate";
    }


    @RequestMapping(value = "/email/{username}", method = RequestMethod.GET)
    public String ToEmail(@PathVariable("username") String username, User user){
        user = userRepository.findByUsername(username);
        try {
            sendEmailWithoutTemplating(username, user.getEmail());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "loginSuccess"; //temp return
    }


    @Autowired
    public EmailService emailService;
    public void sendEmailWithoutTemplating(String username, String email2) throws UnsupportedEncodingException {
        final DefaultEmail email = DefaultEmail.builder()
                .from(new InternetAddress("daylinzack@gmail.com", "Admin Darth Vader"))
                .to(Lists.newArrayList(new InternetAddress(email2, username)))
                .subject("I got someone for you")
                .body("Dear loopin.fullname, pricipal.getname of fullname want to rent your room")
                .encoding("UTF-8").build();
        emailService.send(email);
    }


    public UserValidator getUserValidator() {
        return userValidator;
    }
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
