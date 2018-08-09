package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@RequestMapping(value="/materialtypen" , method=RequestMethod.GET)
	public String materialTypen(Model model)
	{
		List<Materialtyp> materialtypen = materialtypRepository.findAll();
		
		if(materialtypen !=null)
		{
			model.addAttribute("materialTypen",materialtypen);
		}
		return "materialtypen";
	}
	
	@RequestMapping(value="/materialtypen/add", method=RequestMethod.POST)
	public String addToMaterialtyp(Materialtyp materialtyp , BindingResult result) 
	{
		
		Materialtyp existing = materialtypRepository.findByName(materialtyp.getName());
		if(existing!=null)
		{
			result.rejectValue("name", null, "Es ist bereits ein Materialtyp mit diesem Namen in der Datenbank");
		}
        if (result.hasErrors()){
            return "materaltypen";
        }
        
//		materialtypRepository.save(materialtyp);
//		saveAndFlush should invoke a direct saving..
		materialtypRepository.saveAndFlush(materialtyp);
		return "redirect:/materialtypen";
	}
}
