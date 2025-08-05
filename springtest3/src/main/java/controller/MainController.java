package controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	// @GetMapping("/main")
	  //  public String main(
	    //        @RequestParam(value = "view", defaultValue = "main") String view,
	      //      Model model) {
	        //model.addAttribute("view", view);
	        //return "main";   // → /WEB-INF/views/main.jsp 렌더링
	    //}
	 @GetMapping("/main3")
	    public String main3() {
	        return "main3"; // /WEB-INF/views/main3.jsp
	    }
}