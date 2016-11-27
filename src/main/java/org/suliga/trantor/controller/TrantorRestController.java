package org.suliga.trantor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suliga.trantor.model.Greeting;

@RestController
public class TrantorRestController {
	   @RequestMapping("/greeting")
	    public Greeting greeting(@RequestParam(value="id", defaultValue="42") long id,
	    						 @RequestParam(value="name", defaultValue="Tom") String name) {
	        return new Greeting(id, String.format("Are you %s?", name));
	    }
}
