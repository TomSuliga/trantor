package org.suliga.trantor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.suliga.trantor.service.earthquake.EarthquakeService;

@Controller
public class TrantorMainController {
	@Autowired
	private EarthquakeService earthquakeService;
	
	@RequestMapping("/")
	public String home(Model model) { // Model = interface, ModelMap = class
		model.addAttribute("applicationName", "Trantor Tech");
		return "index";
	}
	
	@RequestMapping(value = "/earthquake", method = RequestMethod.GET)
	public String getEarthquake(Model model, String period) {
		model.addAttribute("eqdata", earthquakeService.getTopSixEvents(period));
		return "earthquake";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) { // Model = interface
		model.addAttribute("applicationName", "Trantor Tech");
		return "admin";
	}
	
	@RequestMapping("/dba")
	public String dba(Model model) { // Model = interface
		model.addAttribute("applicationName", "Trantor Tech");
		return "dba";
	}
}
