package DebalFelagiPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.utils.ObjectUtils;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//For Email Service
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            private CloudinaryConfig cloudc;

    String ReceiverFullNameSession, SenderFullNameSession;

    @RequestMapping("/")
    public String index(Model model, House house)
    {
        model.addAttribute("house",new House());
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
    public String housePOST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                            Model model, @ModelAttribute House house, Principal principal){

        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            Map uploadResult =  cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));

            model.addAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            String filename = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
            //String effect = p.getTitle();
            house.setPhoto("<img src='http://res.cloudinary.com/henokzewdie/image/upload/" +filename+"' width='200px'/>");
            //System.out.printf("%s\n", cloudc.createUrl(filename,900,900, "fit"));

        } catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "Sorry I can't upload that!");
        }

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
    public String ToEmail(@PathVariable("username") String username, User user, User userSender, Principal principal){
        user = userRepository.findByUsername(username);
       userSender = userRepository.findByUsername(principal.getName());
        SenderFullNameSession = userSender.getFullName();
        ReceiverFullNameSession = user.getFullName();
          try {
            sendEmailWithoutTemplating(username, user.getEmail());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/"; //temp return
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(){
        return "/adminpage";
    }

    @Autowired
    public EmailService emailService;
    public void sendEmailWithoutTemplating(String username, String receiver) throws UnsupportedEncodingException {
        final DefaultEmail email = DefaultEmail.builder()
                .from(new InternetAddress("debal.felagi@gmail.com", "Debal's ADMIN"))
                .to(Lists.newArrayList(new InternetAddress(receiver, username)))
                .subject("I got someone for you")
                .body("Dear " + ReceiverFullNameSession +  "\n" + SenderFullNameSession + " wants to rent your room."+ "\n"
                 + "Please reply for this email if the room is still Available."+ "\n" +  "\n" + "Kindly Regards, " + "\n" + "DEBAL ADMIN")
                .encoding("UTF-8").build();
        emailService.send(email);
    }


}
