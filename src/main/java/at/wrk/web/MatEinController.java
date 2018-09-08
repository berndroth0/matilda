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
import at.wrk.model.Materialtyp;
import at.wrk.model.Materialtyp_Einheitentyp;
import at.wrk.repository.EinheitentypRepository;
import at.wrk.repository.MatEinRepository;
import at.wrk.repository.MaterialtypRepository;

@Controller
public class MatEinController
{
	@Autowired
	private MatEinRepository matEinRepository;
	@Autowired
	private EinheitentypRepository einheitentypRepository;
	@Autowired
	private MaterialtypRepository materialtypRepository;
	
	@Autowired
	public MatEinController(MatEinRepository matEinRepository, EinheitentypRepository einheitentypRepository,
			MaterialtypRepository materialtypRepository)
	{
		super();
		this.matEinRepository = matEinRepository;
		this.einheitentypRepository = einheitentypRepository;
		this.materialtypRepository = materialtypRepository;
	}
	
	// ************************************* Modelattribute   ***************************************
	
	@ModelAttribute("alleEinheitentypen")
	public List<Einheitentyp> alleEinheitentypen() 
	{	
		return einheitentypRepository.findAll();
	}
	
	@ModelAttribute("alleMaterialtypen")
	public List<Materialtyp> alleMaterialtypen() 
	{	
		return materialtypRepository.findAll();
	}
	
	// ************************************* MatEin List ************************************
	
	@RequestMapping(value="/materialeinheiten" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Materialtyp_Einheitentyp> materialeinheiten = matEinRepository.findAll();
		
		if(materialeinheiten !=null)
		{
			model.addAttribute("materialeinheiten",materialeinheiten);
		}
		return "materialeinheiten";
	}
	
//  ****************************************   MatEin Hinzufügen  ***************************************

	@RequestMapping(value="/materialeinheit", method=RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("materialtyp_einheitentyp", new Materialtyp_Einheitentyp());
        return "materialeinheit";
    }
	
    @RequestMapping(value="/materialeinheit", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Materialtyp_Einheitentyp materialtyp_einheitentyp) {
  	    	
			matEinRepository.save(materialtyp_einheitentyp);
	        return "redirect:/materialeinheiten?success";		
    }	
    
	// ************************************* MatEin Ändern ************************************
    
	@RequestMapping(value="/mateinupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Materialtyp_Einheitentyp exicting = matEinRepository.findById(id);
		
        model.addAttribute("materialtyp_einheitentyp", exicting);
        return "mateinupdate";
    }
	
    @RequestMapping(value="/mateinupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute Materialtyp_Einheitentyp materialtyp_einheitentyp) {
    	
    	Materialtyp_Einheitentyp existing = matEinRepository.findById(id);
     	
    		existing.setManzahl(materialtyp_einheitentyp.getManzahl());
    		existing.setMaterialtyp(materialtyp_einheitentyp.getMaterialtyp());
    		existing.setEanzahl(materialtyp_einheitentyp.getEanzahl());
    		existing.setEinheitentyp(materialtyp_einheitentyp.getEinheitentyp());
    		existing.setBeschreibung(materialtyp_einheitentyp.getBeschreibung());
    		
        	matEinRepository.save(existing);       	
        	return "redirect:/materialeinheiten?success";
	}   
    
	// ************************************* MatEin Löschen ************************************
  
	@RequestMapping(value="/mateinupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		matEinRepository.deleteById(id);
		
		return "redirect:/materialeinheiten?loeschen";
	}
}
