package org.suliga.trantor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.suliga.trantor.model.Person;
import org.suliga.trantor.service.MinesweeperService;
import org.suliga.trantor.service.crossword.CrosswordPuzzleService;
import org.suliga.trantor.service.earthquake.EarthquakeService;

@Controller
public class TrantorMainController {
	@Autowired
	private EarthquakeService earthquakeService;
	
	@Autowired
	private CrosswordPuzzleService crosswordPuzzleService;
	
	@Autowired
	private MinesweeperService minesweeperService;
	
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
	
	@RequestMapping(value = "/crossword", method = RequestMethod.GET)
	public String getMobyDickCrosswordPuzzle(Model model, HttpServletRequest request) {
		String session = request.getSession().toString();
		//System.out.println("Session = " + session);
		model.addAttribute("title", "Moby-Dick Crossword Puzzle");
		model.addAttribute("grid", crosswordPuzzleService.getGrid(session));
		model.addAttribute("httpSession",session);
		model.addAttribute("autoCheck", crosswordPuzzleService.getAutoCheck(session));
		return "crossword";
	}
	
	@RequestMapping(value="/minesweeper", method=RequestMethod.GET)
	public String getMinesweeper(Model model) {
		model.addAttribute("grid", minesweeperService.getGrid());
		return "minesweeper";
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
	
	@RequestMapping("/temp")
	public String temp(Model model) { // Model = interface, ModelMap = class
		model.addAttribute("safeText", "Trantor Tech");
		model.addAttribute("unsafeText", "This is <b>Bold</b>");
		model.addAttribute("firstName", "Tom");
		// temp
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom Suliga", 54, "Robotics"));
		persons.add(new Person("James Bond", 37, "Secret Agent"));
		persons.add(new Person("Lisa Simpson", 13, "Actress"));
		model.addAttribute("persons",persons);
		return "temp";
	}
	
	@RequestMapping("/try1")
	public String tempjsp(Model model) {
		model.addAttribute("name","Tom");
		model.addAttribute("age","54");
		model.addAttribute("id","123XYZ");
		return "jsp/try1";
	}
}





















