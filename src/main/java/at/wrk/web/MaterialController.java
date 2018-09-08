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

import at.wrk.model.Btransaktion;
import at.wrk.model.Lagerstandort;
import at.wrk.model.Material;
import at.wrk.model.Materialtyp;
import at.wrk.model.Ntransaktion;
import at.wrk.model.Veranstaltung;
import at.wrk.repository.BtransaktionRepository;
import at.wrk.repository.LagerstandortRepository;
import at.wrk.repository.MaterialRepository;
import at.wrk.repository.MaterialtypRepository;
import at.wrk.repository.NtransaktionRepository;
import at.wrk.repository.VeranstaltungRepository;

@Controller
public class MaterialController
{
	private MaterialRepository materialRepository;
	private MaterialtypRepository materialtypRepository;
	private LagerstandortRepository lagerstandortRepository;
	private NtransaktionRepository ntransaktionRepository;
	private BtransaktionRepository btransaktionRepository;
	private VeranstaltungRepository veranstaltungRepository;
	
	// die beide sind für hinzufügen verwendet:
	private Lagerstandort aktLagerstandort;
	private long aktLagerId;
	// die beide sind für lieferschein verwendet:
	private Material aktMaterial;
	private long aktMaterialId;
	
	@Autowired
	public MaterialController(MaterialRepository materialRepository, MaterialtypRepository materialtypRepository,
			LagerstandortRepository lagerstandortRepository , NtransaktionRepository ntransaktionRepository,
			BtransaktionRepository btransaktionRepository, VeranstaltungRepository veranstaltungRepository)
	{
		super();
		this.materialRepository = materialRepository;
		this.materialtypRepository = materialtypRepository;
		this.lagerstandortRepository = lagerstandortRepository;
		this.ntransaktionRepository = ntransaktionRepository;
		this.btransaktionRepository = btransaktionRepository;
		this.veranstaltungRepository = veranstaltungRepository;
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
	
	@ModelAttribute("alleVeranstaltungen")
	public List<Veranstaltung> alleVeranstaltungen() 
	{	
		return veranstaltungRepository.findAll();
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
		aktLagerstandort = lagerstandort;
		aktLagerId = lagerstandort.getId();
		
		model.addAttribute("material", new Material());
		
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
	
	// ************************************* Material hinzufügen  ************************************
	
    @RequestMapping(value="/materialen", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Material material) {
    	
    		material.setLagerstandort(aktLagerstandort);
    		if(material.getBestand()<0)
    		{
    	        return "redirect:/materialverwaltung/"+aktLagerId+"?nobestand"; 
    		}
    		else
    		{
        		materialRepository.save(material);
    	        return "redirect:/materialverwaltung/"+aktLagerId; 
    		}
    }
    
	// ************************************* Lieferschein - Ntransaktion  ************************************

	@RequestMapping(value="/ntransaktion/{id}", method=RequestMethod.GET)
    public String addNtransaktionForm(@PathVariable("id") long id, Model model) {
		
        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId(); 
        
        model.addAttribute("ntransaktion", new Ntransaktion(aktMaterial));
     
        return "ntransaktion";
    }
	
    @RequestMapping(value="/ntransaktion/{id}", method=RequestMethod.POST)
    public String ntransaktionSpeichern(@PathVariable("id") long id, @ModelAttribute Ntransaktion ntransaktion) {
    	
    	ntransaktion.setMaterial(aktMaterial);
		
    	if(ntransaktion.getArt()!=null)
    	{
    		if(ntransaktion.getArt().equals("ausgabe"))
    		{
    			if(ntransaktion.getMenge()>0)
    			{
	    			if(aktMaterial.getBestand()< ntransaktion.getMenge())
	    			{
	    				return "redirect:/ntransaktion/{id}?nomaterial";
	    			}
	    			else
	    			{
	    				if(ntransaktion.getLieferungsDatum()!=null)
	    				{
		    				int x = aktMaterial.getBestand();
		    				x = x - ntransaktion.getMenge();
		    				aktMaterial.setBestand(x);
		        			materialRepository.save(aktMaterial);
		        			ntransaktionRepository.save(ntransaktion);
		        				        			        			
		        			return "redirect:/materialverwaltung/"+aktLagerId+"?nerfolgreich";
	    				}
	    				else
	    				{
	    					return "redirect:/ntransaktion/{id}?datum";
	    				}
	    			}
    			}
    			else
    			{
    				return "redirect:/ntransaktion/{id}?menge";
    			}
    		}
    		else
    		{
    			if(ntransaktion.getMenge()>0)
    			{
    				if(ntransaktion.getLieferungsDatum()!=null)
    				{
		    			int x = aktMaterial.getBestand();
		    			x = x + ntransaktion.getMenge();
		    			aktMaterial.setBestand(x);
		    			materialRepository.save(aktMaterial);
		    			ntransaktionRepository.save(ntransaktion);
	    			
		    			return "redirect:/materialverwaltung/"+aktLagerId+"?nerfolgreich";
    				}
    				else
    				{
    					return "redirect:/ntransaktion/{id}?datum";
    				}
    			}
    			else
    			{
    				return "redirect:/ntransaktion/{id}?menge";
    			}
    		}    			
    	}
    	else
    	{
    		return "redirect:/ntransaktion/{id}?noart";
    	}
	} 
    
	// ************************************* Buchungsschein - Btransaktion  ************************************   
    
	@RequestMapping(value="/btransaktion/{id}", method=RequestMethod.GET)
    public String addBtransaktionForm(@PathVariable("id") long id, Model model) {
		
        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId(); 
        
        model.addAttribute("btransaktion", new Btransaktion(aktMaterial));
     
        return "btransaktion";
    }
	
    @RequestMapping(value="/btransaktion/{id}", method=RequestMethod.POST)
    public String btransaktionSpeichern(@PathVariable("id") long id, @ModelAttribute Btransaktion btransaktion) {
    	
    	btransaktion.setMaterial(aktMaterial);
		
    	if(btransaktion.getArt()!=null)
    	{
    		if(btransaktion.getArt().equals("buchen"))
    		{
    			if(btransaktion.getMenge()>0)
    			{
	    			if(aktMaterial.getBestand()< btransaktion.getMenge())
	    			{
	    				return "redirect:/ntransaktion/{id}?nomaterial";
	    			}
	    			else
	    			{
	    				int x = aktMaterial.getBestand();
	    				x = x - btransaktion.getMenge();
	    				aktMaterial.setBestand(x);
	        			materialRepository.save(aktMaterial);
	        			btransaktionRepository.save(btransaktion);
	        			
	        			return "redirect:/materialverwaltung/"+aktLagerId;
	    			}
    			}
    			else
    			{
    				return "redirect:/btransaktion/{id}?menge";
    			}
    		}
    		else
    		{
    			if(btransaktion.getMenge()>0)
    			{
	    			int x = aktMaterial.getBestand();
	    			x = x + btransaktion.getMenge();
	    			aktMaterial.setBestand(x);
	    			materialRepository.save(aktMaterial);
	    			btransaktionRepository.save(btransaktion);
    			
	    			return "redirect:/materialverwaltung/"+aktLagerId;
    			}
    			else
    			{
    				return "redirect:/btransaktion/{id}?menge";
    			}
    		}    			
    	}
    	else
    	{
    		return "redirect:/btransaktion/{id}?noart";
    	}
	} 
    
	// ************************************* Inventur   ************************************   

	@RequestMapping(value="/inventur/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		
		Material exicting = materialRepository.findById(id);
		aktLagerId = exicting.getLagerstandort().getId();
		
        model.addAttribute("material", exicting);
         
        return "inventur";
    }
	
    @RequestMapping(value="/inventur/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute("material") @Valid Material material,
    		BindingResult result) {
    	
		Material exicting = materialRepository.findById(id);
    	
    	if(material.getBestand()<0)
    	{
    		result.rejectValue("bestand", null, "Bestand kann nicht ein negatives Wert haben!");
    	}
    	if(result.hasErrors()) {
    		return "inventur";
    	}
    	
    	exicting.setBestand(material.getBestand());
    	exicting.setSeriennummer(material.getSeriennummer());
    	exicting.setEinkaufsdatum(material.getEinkaufsdatum());
    	exicting.setLetztesudatum(material.getLetztesudatum());
    	exicting.setNaechstesudatum(material.getNaechstesudatum());
    	exicting.setEinsatzbereitschaft(material.isEinsatzbereitschaft());
    	
    	materialRepository.save(exicting);
    		
    	return "redirect:/materialverwaltung/"+aktLagerId;
    }
    
}
