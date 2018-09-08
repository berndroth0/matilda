package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Veranstaltung;
import at.wrk.repository.VeranstaltungRepository;

@Controller
public class VeranstaltungController
{
	private VeranstaltungRepository veranstaltungRepository;

	@Autowired
	public VeranstaltungController(VeranstaltungRepository veranstaltungRepository)
	{
		super();
		this.veranstaltungRepository = veranstaltungRepository;
	}
	
	// ************************************* VeranstaltungList ************************************
	
	@RequestMapping(value="/veranstaltungen" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();
		if(veranstaltungen!=null)
		{
			model.addAttribute("veranstaltungen",veranstaltungen);
		}
		return "veranstaltungen";
	}
	
	// ************************************* Veranstaltung hinzufügen ***********************************
	
	@RequestMapping(value="/veranstaltung", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("veranstaltung", new Veranstaltung());
        return "veranstaltung";
    }
	
    @RequestMapping(value="/veranstaltung", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Veranstaltung veranstaltung) {
    	
    	Veranstaltung existing =  veranstaltungRepository.findByName(veranstaltung.getName());
		if(existing!=null )
		{	
			return "redirect:/veranstaltung?alert";
		}
		else
		{
			veranstaltungRepository.save(veranstaltung);
	        return "redirect:/veranstaltungen?success";
		}
    }
	
	// ************************************* Veranstaltung Ändern ************************************
    
	@RequestMapping(value="/veranstaltungupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Veranstaltung exicting = veranstaltungRepository.findById(id);
        model.addAttribute("veranstaltung", exicting);
        return "veranstaltungupdate";
    }
	
    @RequestMapping(value="/veranstaltungupdate/{id}", method=RequestMethod.POST)
    public String aendernSepeichern(@PathVariable("id") long id, @ModelAttribute Veranstaltung veranstaltung) {
    	
    	Veranstaltung existing = veranstaltungRepository.findById(id);
    	Veranstaltung andere = veranstaltungRepository.findByName(veranstaltung.getName());
     	
    	if(veranstaltung.getName().equals(existing.getName()))		
    	{ 
    		existing.setBeginn(veranstaltung.getBeginn());
    		existing.setEnde(veranstaltung.getEnde());
    		
        	veranstaltungRepository.save(existing);
        	return "redirect:/veranstaltungen?success";
    	}
    	else if(andere==null)
    	{
    		existing.setName(veranstaltung.getName());
    		existing.setBeginn(veranstaltung.getBeginn());
    		existing.setEnde(veranstaltung.getEnde());
    		
    		veranstaltungRepository.save(existing);       	
        	return "redirect:/veranstaltungen?success";
    	}
    	
    	System.out.println(andere.getName());
    	return "redirect:/veranstaltungupdate/{id}?alert";
	}  
    
	// ************************************* Einheitentyp Löschen ************************************
    
	@RequestMapping(value="/veranstaltungupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{ 	
		
		veranstaltungRepository.deleteById(id);
		
		return "redirect:/veranstaltungen?loeschen";
	}
}
