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

import at.wrk.model.Einheitentyp;
import at.wrk.model.Mitarbeitertyp;
import at.wrk.model.Mitarbeitertyp_Einheitentyp;
import at.wrk.repository.EinheitentypRepository;
import at.wrk.repository.MitEinRepository;
import at.wrk.repository.MitarbeitertypRepository;

@Controller
public class MitEinController
{
	private MitEinRepository mitEinRepository;
	private MitarbeitertypRepository mitarbeitertypRepository;
	private EinheitentypRepository einheitentypRepository;
	
	@Autowired
	public MitEinController(MitEinRepository mitEinRepository, MitarbeitertypRepository mitarbeitertypRepository,
			EinheitentypRepository einheitentypRepository)
	{
		super();
		this.mitEinRepository = mitEinRepository;
		this.mitarbeitertypRepository = mitarbeitertypRepository;
		this.einheitentypRepository = einheitentypRepository;
	}
	
	// ************************************* Modelattribute   ***************************************
	
	@ModelAttribute("alleEinheitentypen")
	public List<Einheitentyp> alleEinheitentypen() 
	{	
		return einheitentypRepository.findAll();
	}
	
	@ModelAttribute("alleMitarbeitertypen")
	public List<Mitarbeitertyp> alleMitarbeitertypen()
	{	
		return mitarbeitertypRepository.findAll();
	}
	
	// ************************************* MitEin List ************************************
	
	@RequestMapping(value="/mitarbeitereinheiten" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Mitarbeitertyp_Einheitentyp> mitarbeitereinheiten = mitEinRepository.findAll();
		
		if(mitarbeitereinheiten !=null)
		{
			model.addAttribute("mitarbeitereinheiten",mitarbeitereinheiten);
		}
		return "mitarbeitereinheiten";
	}
	
//  ****************************************   MitEin Hinzufügen  ***************************************

	@RequestMapping(value="/mitarbeitereinheit", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("mitarbeitertyp_einheitentyp", new Mitarbeitertyp_Einheitentyp());
        return "mitarbeitereinheit";
    }
	
    @RequestMapping(value="/mitarbeitereinheit", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute("mitarbeitertyp_einheitentyp") @Valid Mitarbeitertyp_Einheitentyp mitarbeitertyp_einheitentyp,
    		BindingResult result) {
  	    	
	    if (result.hasErrors()){
	        return "mitarbeitereinheit";
	    }
	    
		mitEinRepository.save(mitarbeitertyp_einheitentyp);		
        return "redirect:/mitarbeitereinheiten?success";		
    }	
    
	// ************************************* MitEin Ändern ************************************
    
	@RequestMapping(value="/miteinupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Mitarbeitertyp_Einheitentyp exicting = mitEinRepository.findById(id);
		
        model.addAttribute("mitarbeitertyp_einheitentyp", exicting);
        return "miteinupdate";
    }
	
    @RequestMapping(value="/miteinupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute Mitarbeitertyp_Einheitentyp mitarbeitertyp_einheitentyp) {
    	
    	Mitarbeitertyp_Einheitentyp existing = mitEinRepository.findById(id);
     	
    		existing.setManzahl(mitarbeitertyp_einheitentyp.getManzahl());
    		existing.setMitarbeitertyp(mitarbeitertyp_einheitentyp.getMitarbeitertyp());
    		existing.setEanzahl(mitarbeitertyp_einheitentyp.getEanzahl());
    		existing.setEinheitentyp(mitarbeitertyp_einheitentyp.getEinheitentyp());
    		existing.setBeschreibung(mitarbeitertyp_einheitentyp.getBeschreibung());
    		
        	mitEinRepository.save(existing);       	
        	return "redirect:/mitarbeitereinheiten?success";
	}   
    
	// ************************************* MitEin Löschen ************************************
  
	@RequestMapping(value="/miteinupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		mitEinRepository.deleteById(id);
		
		return "redirect:/mitarbeitereinheiten?loeschen";
	}
}
