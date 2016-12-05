package org.suliga.trantor.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suliga.trantor.model.TCar;
import org.suliga.trantor.model.CarRepo;
import org.suliga.trantor.model.TDriver;
import org.suliga.trantor.model.DriverRepo;
import org.suliga.trantor.model.Person;
import org.suliga.trantor.model.RareBook;
import org.suliga.trantor.model.RareBookRepository;
import org.suliga.trantor.service.CarDriverService;
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
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
	private RareBookRepository rareBookRepository;
	
    @Autowired
    private CarRepo carRepo;
    
    @Autowired
    private DriverRepo driverRepo;
    
    @Autowired
    private CarDriverService carDriverService;
	
	@RequestMapping("/")
	public String home(Model model) { // Model = interface, ModelMap = class
		model.addAttribute("applicationName", "Trantor Tech");
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@GetMapping("/ajaxphp")
	public String ajaxphp(Model model) {
		return "ajaxphp";
	}
	
	@GetMapping("/ws")
	public String ws(Model model) {
		return "ws";
	}
	
	@GetMapping("/required")
	public String required(Model model) {
		return "required";
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
	public String getMinesweeper(Model model, HttpServletRequest request) {
		System.out.println("ms request=" 
				+ request.getLocalName() + ", " 
				+ request.getRemoteUser() + ", " 
				+ request.getSession().getId());
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
	
	@RequestMapping("/test1")
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
		return "test1";
	}
	
	@RequestMapping("/try1")
	public String tempjsp(Model model) {
		model.addAttribute("name","Tom");
		model.addAttribute("age","54");
		model.addAttribute("id","123XYZ");
		System.out.println("server.context-path=" + System.getProperty("server.context-path"));
		return "jsp/try1";
	}
	
	@GetMapping("/cardriver")
	public String cardriver(Model model) {
		model.addAttribute("cars", carRepo.findAll());
		model.addAttribute("drivers", driverRepo.findAll());
		return "cardriver";
	}
	
	@PostMapping("/cardriver")
	public String carddriverPost(Model model, TCar car, TDriver driver, String carId, String driverId) {
		carDriverService.add(car, driver);
		carDriverService.remove(carId, driverId);
		return "redirect:/cardriver";
	}
	
	@GetMapping("/myupload")
    public String handleFileUploadGet(Model model) {
        return "myupload";
    }
	
	@PostMapping("/myupload")
    public String handleFileUploadPost(
    		@RequestParam("file1") MultipartFile file1,
    		@RequestParam("file2") MultipartFile file2,
            RedirectAttributes redirectAttributes) {

		try {
            String uploadsDir = "/uploads/";
            String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
            
            if (! new File(realPathtoUploads).exists()) {
                new File(realPathtoUploads).mkdir();
            }

            if (file1 != null && file1.getOriginalFilename().length() > 0) {
            	file1.transferTo(new File(realPathtoUploads + file1.getOriginalFilename()));
            }
            
            if (file2 != null && file2.getOriginalFilename().length() > 0) {
            	file2.transferTo(new File(realPathtoUploads + file2.getOriginalFilename()));
            }
            
    		if (file1 != null || file2 != null) {
    			StringBuilder sb = new StringBuilder("You successfully uploaded:");
    			if (file1 != null) {
    				sb.append(" " + file1.getOriginalFilename());
    			}
    			if (file2 != null) {
    				sb.append(" " + file2.getOriginalFilename());
    			}
    			redirectAttributes.addFlashAttribute("message", sb.toString());
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Needed to prevent multiple submits of same screen
        return "redirect:/myupload";
    }
	
	@GetMapping("/rarebooks")
	@Transactional
	public String getRareBooks(Model model) {
		model.addAttribute("mainHeading", "Rare Books");
		List<RareBook> list = (List<RareBook>) rareBookRepository.findAll();
		model.addAttribute("rareBooks", list);
		
		//rareBookRepository.count();
		//rareBookRepository.delete(entity);
		//rareBookRepository.delete(id);
		//rareBookRepository.deleteAll();
		//rareBookRepository.findOne(id);
		//rareBookRepository.save(entity);
		
		//Session session = sessionFactory.openSession();
		//System.out.println("sessionFactory.openSession()=" + session);
		//session.beginTransaction();
		//rareBookRepository.save(new RareBook("X Title 1", "X Author 1", Condition.EXCELLENT, true));
		//rareBookRepository.save(new RareBook("X Title 2", "X Author 2", Condition.EXCELLENT, true));
		//if (System.currentTimeMillis() > 1) {
		//	throw new RuntimeException("temp");
		//}
		//session.getTransaction().commit();
		return "rarebooks";
	}
}





















