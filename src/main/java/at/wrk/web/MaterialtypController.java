package at.wrk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
//	@RequestMapping(value="/{name}", method=RequestMethod.POST)
//	public String addToMaterialtypList(@PathVariable("name") String name, Materialtyp materialtyp) 
//	{
//		materialtyp.setName(name);
//		materialtypRepository.save(materialtyp);
//		return "redirect:/{name}";
//	}
}
