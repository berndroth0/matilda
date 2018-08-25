package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Benutzer;
import at.wrk.model.Lagerstandort;
import at.wrk.repository.LagerstandortRepository;
import at.wrk.repository.UserRepository;

@Controller
public class LagerstandortController
{
	@Autowired
	private LagerstandortRepository lagerstandortRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public LagerstandortController(LagerstandortRepository lagerstandortRepository, UserRepository userRepository)
	{
		super();
		this.lagerstandortRepository = lagerstandortRepository;
		this.userRepository = userRepository;
	}
	
	// ************************************* Modelattribute   ***************************************
	
	@ModelAttribute("alleBenutzer")
	public List<Benutzer> alleBenutzer() 
	{	
		return userRepository.findAll();
	}
	
//  ****************************************   Lagerstandorten List  *****************************************
	
	@RequestMapping(value="/lagerstandorten" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAll();
		if(lagerstandorten!=null)
		{
			model.addAttribute("lagerstandorten",lagerstandorten);
		}
		return "lagerstandorten";
	}
	
//  ****************************************   Lagerstandort Hinzufügen  ***************************************

	@RequestMapping(value="/lagerstandort", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("lagerstandort", new Lagerstandort());
        return "lagerstandort";
    }
	
    @RequestMapping(value="/lagerstandort", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Lagerstandort lagerstandort) {
  	    	
    	Lagerstandort existing =  lagerstandortRepository.findByName(lagerstandort.getName());
    	
    	
		if(existing!=null )
		{	
			return "redirect:/lagerstandort?alert";
		}
		
		else
		{
			lagerstandortRepository.save(lagerstandort);
	        return "redirect:/lagerstandorten?success";
		}
    }
    
	// ************************************* Lagerstandort Ändern ************************************
    
	@RequestMapping(value="/lagerstandortupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Lagerstandort exicting = lagerstandortRepository.findById(id);
        model.addAttribute("lagerstandort", exicting);
        return "lagerstandortupdate";
    }
	
    @RequestMapping(value="/lagerstandortupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute Lagerstandort lagerstandort) {
    	
    	Lagerstandort existing = lagerstandortRepository.findById(id);
    	Lagerstandort andere = lagerstandortRepository.findByName(lagerstandort.getName());
     	
    	if(lagerstandort.getName().equals(existing.getName()))		
    	{
    		existing.setName(lagerstandort.getName());
    		existing.setAdresse(lagerstandort.getAdresse());
    		existing.setBeschreibung(lagerstandort.getBeschreibung());
    		existing.setBenutzer(lagerstandort.getBenutzer());
    		
    		lagerstandortRepository.save(existing);       	
        	return "redirect:/lagerstandorten?success";
    	}
    	else if(andere==null)
    	{
     		existing.setName(lagerstandort.getName());
    		existing.setAdresse(lagerstandort.getAdresse());
    		existing.setBeschreibung(lagerstandort.getBeschreibung());
    		existing.setBenutzer(lagerstandort.getBenutzer());
    		
        	lagerstandortRepository.save(existing);       	
        	return "redirect:/lagerstandorten?success";
    	}
    	return "redirect:/lagerstandortupdate/{id}?alert";
	}    

	// ************************************* Lagerstandort Löschen ************************************
    
	@RequestMapping(value="/lagerstandortupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		lagerstandortRepository.deleteById(id);
		
		return "redirect:/lagerstandorten?loeschen";
	}
    

}