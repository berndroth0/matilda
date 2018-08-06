package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.wrk.model.Benutzer;
import at.wrk.repository.UserRepository;

@Controller
public class UserListController
{
	private UserRepository userRepository;
	
	@Autowired
	public UserListController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value="/users" , method=RequestMethod.GET)
	public String users(Model model)
	{
		List<Benutzer> users = userRepository.findAll();
		
		if(users !=null)
		{
			model.addAttribute("benutzern",users);
		}
		return "users";
	}
	
//	@RequestMapping(value="/users/{benutzername}/delete", method = RequestMethod.POST)
//	public String deleteUser(@PathVariable("benutzername") String benutzername) 
//	{
//		userRepository.delete(benutzername);
//		
//		return "redirect:/users";
//	}
	
	
//	@RequestMapping(value="/{benutzername}" , method=RequestMethod.POST)
//	public String addToUserList(@PathVariable("benutzername") String benutzername, Benutzer benutzer)
//	{
//		benutzer.setBenutzername(benutzername);
//		userRepository.save(benutzer);
//		return "redirect:/{benutzername}";
//	}
}

