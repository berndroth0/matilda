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
import at.wrk.model.Material;
import at.wrk.model.Ntransaktion;
import at.wrk.model.Veranstaltung;
import at.wrk.model.Veranstaltung_Einheitentyp;
import at.wrk.repository.EinheitentypRepository;
import at.wrk.repository.VerEinRepository;
import at.wrk.repository.VeranstaltungRepository;

@Controller
public class VerEinController
{
	private VerEinRepository verEinRepository;	
	private VeranstaltungRepository veranstaltungRepository;	
	private EinheitentypRepository einheitentypRepository;	
	
	private long aktVeranstaltungId;
	private Veranstaltung aktVeranstaltung;
	
	@Autowired
	public VerEinController(VerEinRepository verEinRepository, VeranstaltungRepository veranstaltungRepository,
			EinheitentypRepository einheitentypRepository)
	{
		super();
		this.verEinRepository = verEinRepository;
		this.veranstaltungRepository = veranstaltungRepository;
		this.einheitentypRepository = einheitentypRepository;
	}
	
	// ************************************* Modelattribute   ***************************************
	
	@ModelAttribute("alleEinheitentypen")
	public List<Einheitentyp> alleEinheitentypen() 
	{	
		return einheitentypRepository.findAll();
	}		
	
	// ************************************* Ver-Ein List   ***************************************
	
	@RequestMapping(value="/veranstaltungeinheit/{id}", method=RequestMethod.GET)
    public String addNtransaktionForm(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("veranstaltung_einheitentyp", new Veranstaltung_Einheitentyp());
		
		Veranstaltung existing = veranstaltungRepository.findById(id);
		aktVeranstaltungId = existing.getId();
		List<Veranstaltung_Einheitentyp> verein= verEinRepository.findByVeranstaltung(existing);
		
        if(verein!=null)
        {
        	model.addAttribute("verein",verein);
        }
     
        return "veranstaltungeinheit";
    }
	
    @RequestMapping(value="/veranstaltungeinheit", method=RequestMethod.POST)
    public String addSpeichern(@ModelAttribute("veranstaltung_einheitentyp") @Valid Veranstaltung_Einheitentyp veranstaltung_einheitentyp,
    		BindingResult result) {
    	
    	aktVeranstaltung = veranstaltungRepository.findById(aktVeranstaltungId);
    	
    	veranstaltung_einheitentyp.setVeranstaltung(aktVeranstaltung);
    	
		if(veranstaltung_einheitentyp.getEinheitentyp()==null)
		{	
			 result.rejectValue("einheitentyp", null, "Der Einheitentyp kann nicht NULL sein");
		}
        if (result.hasErrors()){
            return "veranstaltungeinheit";         
        }

    	verEinRepository.save(veranstaltung_einheitentyp);
        return "redirect:/veranstaltungeinheit/"+aktVeranstaltungId; 	
    }
	
}
