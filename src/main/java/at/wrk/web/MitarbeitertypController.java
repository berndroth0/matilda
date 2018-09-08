package at.wrk.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import at.wrk.model.Mitarbeitertyp;
import at.wrk.repository.MitarbeitertypRepository;

@Controller
public class MitarbeitertypController
{
	private MitarbeitertypRepository mitarbeitertypRepository;
	
	@Autowired
	public MitarbeitertypController(MitarbeitertypRepository mitarbeitertypRepository)
	{
		super();
		this.mitarbeitertypRepository = mitarbeitertypRepository;
	}
	
	// ************************************* MitarbeitertypList ************************************
	
	@RequestMapping(value="/mitarbeitertypen" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Mitarbeitertyp> mitarbeitertypen = mitarbeitertypRepository.findAll();
		if(mitarbeitertypen !=null)
		{
			model.addAttribute("mitarbeitertypen",mitarbeitertypen);
		}
		return "mitarbeitertypen";
	}
	
	// ************************************* Mitarbeitertyp hinzufügen ************************************
	
	@RequestMapping(value="/mitarbeitertyp", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("mitarbeitertyp", new Mitarbeitertyp());
        return "mitarbeitertyp";
    }
	
    @RequestMapping(value="/mitarbeitertyp", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute("mitarbeitertyp") @Valid Mitarbeitertyp mitarbeitertyp, BindingResult result) {
    	
    	Mitarbeitertyp existing =  mitarbeitertypRepository.findByKuerzel(mitarbeitertyp.getKuerzel());
		if(existing!=null )
		{	
			 result.rejectValue("kuerzel", null, "Es ist bereits ein Mitarbeitertyp mit gleichem Kürzel eingetragen");
		}
	    if (result.hasErrors()){
	        return "mitarbeitertyp";
	    }
	    
		mitarbeitertypRepository.save(mitarbeitertyp);
        return "redirect:/mitarbeitertypen?success";	
    }
    
	// ************************************* Mitarbeitertyp Ändern ************************************
    
	@RequestMapping(value="/mitarbeitertypupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Mitarbeitertyp exicting = mitarbeitertypRepository.findById(id);
        model.addAttribute("mitarbeitertyp", exicting);
        return "mitarbeitertypupdate";
    }
	
    @RequestMapping(value="/mitarbeitertypupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute("mitarbeitertyp") @Valid Mitarbeitertyp mitarbeitertyp,
    		BindingResult result) {
    	
    	Mitarbeitertyp existing = mitarbeitertypRepository.findById(id);
    	Mitarbeitertyp andere = mitarbeitertypRepository.findByKuerzel(mitarbeitertyp.getKuerzel());
     	
    	if(mitarbeitertyp.getKuerzel().equals(existing.getKuerzel()))		
    	{
    		existing.setKuerzel(mitarbeitertyp.getKuerzel());
    		existing.setName(mitarbeitertyp.getName());
    		
            if (result.hasErrors()){
                return "mitarbeitertypupdate";
            }
    		
    		mitarbeitertypRepository.save(existing);       	
        	return "redirect:/mitarbeitertypen?success";
    	}
    	if(andere!=null)
    	{
    		result.rejectValue("kuerzel", null, "Es ist bereits ein Mitarbeitertyp mit gleichem Namen eingetragen");
    	}
        if (result.hasErrors()){
            return "mitarbeitertypupdate";
        }
        
		existing.setKuerzel(mitarbeitertyp.getKuerzel());
		existing.setName(mitarbeitertyp.getName());
		
    	mitarbeitertypRepository.save(existing);       	
    	return "redirect:/mitarbeitertypen?success";
    	
	}   
    
	// ************************************* Einheitentyp Löschen ************************************
    
	@RequestMapping(value="/mitarbeitertypupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		mitarbeitertypRepository.deleteById(id);
		
		return "redirect:/mitarbeitertypen?loeschen";
	}
}
