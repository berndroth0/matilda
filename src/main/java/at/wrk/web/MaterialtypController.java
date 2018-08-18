package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Materialtyp;
import at.wrk.repository.MaterialtypRepository;

@Controller
public class MaterialtypController
{
	private MaterialtypRepository materialtypRepository;
	
	@Autowired
	private MaterialtypController (MaterialtypRepository materialtypRepository)
	{
		this.materialtypRepository = materialtypRepository;
	}
	
	// ************************************* MaterialtypenList ************************************
	
	@RequestMapping(value="/materialtypen" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Materialtyp> materialtypen = materialtypRepository.findAll();
		
		if(materialtypen !=null)
		{
			model.addAttribute("materialtypen",materialtypen);
		}
		return "materialtypen";
	}
	
	// ************************************* Materialtyp Hinzufügen ************************************
		
	@RequestMapping(value="/materialtyp", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("materialtyp", new Materialtyp());
        return "materialtyp";
    }
	
	
    @RequestMapping(value="/materialtyp", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Materialtyp materialtyp) {
    	
    	Materialtyp existing = materialtypRepository.findByName(materialtyp.getName());
		if(existing!=null )
		{	

			return "redirect:/materialtyp?alert";
		}
		else
		{
	    	materialtypRepository.save(materialtyp);
	        return "redirect:/materialtypen?success";
		}
    }
	
	// ************************************* Materialtyp Ändern ************************************
    
	@RequestMapping(value="/materialtypupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Materialtyp exicting = materialtypRepository.findById(id);
        model.addAttribute("materialtyp", exicting);
        return "materialtypupdate";
    }
	
    @RequestMapping(value="/materialtypupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute Materialtyp materialtyp) {
    	
    	Materialtyp existing = materialtypRepository.findById(id);
    	Materialtyp andere = materialtypRepository.findByName(materialtyp.getName());
     	
    	if(materialtyp.getName().equals(existing.getName()))		
    	{
    		existing.setName(materialtyp.getName());
    		existing.setMenge(materialtyp.getMenge());
    		existing.setBeschreibung(materialtyp.getBeschreibung());
    		existing.setLink(materialtyp.getLink());
    		
        	materialtypRepository.save(existing);       	
        	return "redirect:/materialtypen?success";
    	}
    	else if(andere==null)
    	{
    		existing.setName(materialtyp.getName());
    		existing.setMenge(materialtyp.getMenge());
    		existing.setBeschreibung(materialtyp.getBeschreibung());
    		existing.setLink(materialtyp.getLink());
    		
        	materialtypRepository.save(existing);       	
        	return "redirect:/materialtypen?success";
    	}
    	return "redirect:/materialtypupdate/{id}?alert";
	}    
    
	// ************************************* Materialtyp Löschen ************************************
    
	@RequestMapping(value="/materialtypupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		materialtypRepository.deleteById(id);
		
		return "redirect:/materialtypen?loeschen";
	}
    
}
