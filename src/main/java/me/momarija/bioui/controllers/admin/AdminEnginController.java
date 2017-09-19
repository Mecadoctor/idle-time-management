package me.momarija.bioui.controllers.admin;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.service.StorageService;
import me.momarija.bioui.service.admin.AdminChantierService;
import me.momarija.bioui.service.admin.AdminEnginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/chantier/{idC}")
public class AdminEnginController {

	@Autowired
	private AdminChantierService adminCantierService;

	@Autowired
	private AdminEnginService adminEnginService;

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "")
	public String enginList(@PathVariable int idC, Model model){
		Chantier chantier = adminCantierService.getChantier(idC);
		model.addAttribute("title", chantier.getNom());
		model.addAttribute("chantier", chantier);
		return "admin/enginList";
	}

	@RequestMapping(value = "/addEngin", method = RequestMethod.GET)
	public String addEnginGet(@PathVariable int idC, Model model){
		Chantier chantier = adminCantierService.getChantier(idC);

		model.addAttribute("title", "Ajouter un engin");
		model.addAttribute("engin", new Engin());
		model.addAttribute("idC", idC);
		model.addAttribute("chantier", chantier);

		return "admin/enginForm";
	}

	@RequestMapping(value = "/addEngin", method = RequestMethod.POST)
	public String addEnginPost(@PathVariable int idC, Model model, @Valid Engin engin, BindingResult bindingResult,
							   @RequestParam(value = "file", required = false) MultipartFile file){
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			model.addAttribute("chantier", adminCantierService.getChantier(idC));
			return "admin/enginForm";
		}
		if (file.isEmpty())
			engin.setPhoto("photo1.jpg");
		else
			engin.setPhoto(storageService.store(file));
		adminEnginService.addEngin(engin, idC);
		return "redirect:/admin/chantier/"+idC;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String deleteEngin(@PathVariable int id, @PathVariable int idC, @RequestParam(defaultValue = "false") boolean deleteData){
		adminEnginService.deleteEngin(id, deleteData);
		return "redirect:/admin/chantier/"+idC;
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editEnginGet(@PathVariable int id, @PathVariable int idC, Model model) {
		Chantier chantier = adminCantierService.getChantier(idC);
		model.addAttribute("title", "Modifier un engin");
		model.addAttribute("edit", true);
		model.addAttribute("engin", adminEnginService.getEngin(id));
		model.addAttribute("idC", idC);
		model.addAttribute("chantier", chantier);
		return "admin/enginForm";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	private String editEnjinPost(@PathVariable int idC, @PathVariable int id, Model model,
								 @Valid Engin engin, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Erreur");
			model.addAttribute("chantier", adminCantierService.getChantier(idC));
			model.addAttribute("edit", true);
			model.addAttribute("idC", idC);
			return "admin/enginForm";
		}
		engin.setChantier(adminCantierService.getChantier(idC));
		adminEnginService.updateEngin(engin);
		return "redirect:/admin/chantier/" + idC;
	}

	@RequestMapping(value = "changestate/{id}", method = RequestMethod.GET)
	public String changestate(@PathVariable int id, @PathVariable int idC) {
		adminEnginService.changeState(id);
		return "redirect:/admin/chantier/" + idC;
	}
}
