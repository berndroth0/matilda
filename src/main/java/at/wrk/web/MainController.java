package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Autowired
	private SessionRegistry sessionRegistry;
	
    @GetMapping("/")
    public String root(Model model) {
    	
		List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
		for (Object principal : loggedUsers) {
			final User loggedUser = (User) principal;
			model.addAttribute("loggedInUser", loggedUsers); 
		}
    	
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
}
