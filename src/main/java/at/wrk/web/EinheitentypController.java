package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Einheitentyp;
import at.wrk.repository.EinheitentypRepository;

@Controller
public class EinheitentypController
{
	private EinheitentypRepository einheitentypRepository;

	@Autowired
	public EinheitentypController(EinheitentypRepository einheitentypRepository)
	{
		super();
		this.einheitentypRepository = einheitentypRepository;
	}
	
	// ************************************* EinheitentypList ************************************
	
	@RequestMapping(value="/einheitentypen" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Einheitentyp> einheitentypen = einheitentypRepository.findAll();
		if(einheitentypen!=null)
		{
			model.addAttribute("einheitentypen",einheitentypen);
		}
		return "einheitentypen";
	}
	
	// ************************************* Einheitentyp hinzufügen ************************************
	
	@RequestMapping(value="/einheitentyp", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("einheitentyp", new Einheitentyp());
        return "einheitentyp";
    }
	
    @RequestMapping(value="/einheitentyp", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Einheitentyp einheitentyp) {
    	
    	Einheitentyp existing =  einheitentypRepository.findByName(einheitentyp.getName());
		if(existing!=null )
		{	
			return "redirect:/einheitentyp?alert";
		}
		else
		{
			einheitentypRepository.save(einheitentyp);
	        return "redirect:/einheitentypen?success";
		}
    }
	
	// ************************************* Einheitentyp Ändern ************************************
    
	@RequestMapping(value="/einheitentypupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Einheitentyp exicting = einheitentypRepository.findById(id);
        model.addAttribute("einheitentyp", exicting);
        return "einheitentypupdate";
    }
	
    @RequestMapping(value="/einheitentypupdate/{id}", method=RequestMethod.POST)
    public String aendernSepeichern(@PathVariable("id") long id, @ModelAttribute Einheitentyp einheitentyp) {
    	
    	Einheitentyp existing = einheitentypRepository.findById(id);
    	Einheitentyp andere = einheitentypRepository.findByName(einheitentyp.getName());
     	
    	if(einheitentyp.getName().equals(existing.getName()))		
    	{   		 
        	return "redirect:/einheitentypen";
    	}
    	else if(andere==null)
    	{
    		existing.setName(einheitentyp.getName());
    		
    		einheitentypRepository.save(existing);       	
        	return "redirect:/einheitentypen?success";
    	}
    	
    	System.out.println(andere.getName());
    	return "redirect:/einheitentypupdate/{id}?alert";
	}    
    
	// ************************************* Einheitentyp Löschen ************************************
    
	@RequestMapping(value="/einheitentypupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		einheitentypRepository.deleteById(id);
		
		return "redirect:/einheitentypen?loeschen";
	}
}