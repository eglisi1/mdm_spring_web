package ch.eglisi1.springweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ThymeleafController {
    Logger logger = LoggerFactory.getLogger(ThymeleafController.class);

    @GetMapping("/helloWorld")
    public ModelAndView hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        logger.debug("Passed value: " + name);
        model.addAttribute("name", name);
        return new ModelAndView("hello");
    }
}
