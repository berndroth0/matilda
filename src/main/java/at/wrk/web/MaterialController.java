package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Lagerstandort;
import at.wrk.model.Material;
import at.wrk.model.Materialtyp;
import at.wrk.repository.LagerstandortRepository;
import at.wrk.repository.MaterialRepository;
import at.wrk.repository.MaterialtypRepository;

@Controller
public class MaterialController
{
	private MaterialRepository materialRepository;
	private MaterialtypRepository materialtypRepository;
	private LagerstandortRepository lagerstandortRepository;
	
	@Autowired
	public MaterialController(MaterialRepository materialRepository, MaterialtypRepository materialtypRepository,
			LagerstandortRepository lagerstandortRepository)
	{
		super();
		this.materialRepository = materialRepository;
		this.materialtypRepository = materialtypRepository;
		this.lagerstandortRepository = lagerstandortRepository;
	}
	
	// ************************************* Modelattribute   ***************************************
	
	@ModelAttribute("alleLagerstandorten")
	public List<Lagerstandort> alleLagerstandorten() 
	{	
		return lagerstandortRepository.findAll();
	}
	
	@ModelAttribute("alleMaterialtypen")
	public List<Materialtyp> alleMaterialtypen() 
	{	
		return materialtypRepository.findAll();
	}
	
	// ************************************* Lagerstandort List ************************************
	
	@RequestMapping(value="/materialen" , method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAll();
		if(lagerstandorten!=null)
		{
			model.addAttribute("lagerstandorten",lagerstandorten);
		}
		return "materialen";
	}
	
	// ************************************* Materialverwaltung List ************************************
	
	@RequestMapping(value="/materialverwaltung/{id}", method=RequestMethod.GET)
    public String materialVerwaltungForm(@PathVariable("id") long id, Model model) {
		
		Lagerstandort lagerstandort = lagerstandortRepository.findById(id);
		List<Material> materialen = materialRepository.findByLagerstandort(lagerstandort);
		
		if(materialen!=null)
		{
			model.addAttribute("materialen", materialen);
		}
		else
		{
			return "materialverwaltung?nomaterial";
		}
        return "materialverwaltung";
    }
		
	@RequestMapping(value="/materialupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Material exicting = materialRepository.findById(id);
        model.addAttribute("material", exicting);
        return "materialupdate";
    }
}
