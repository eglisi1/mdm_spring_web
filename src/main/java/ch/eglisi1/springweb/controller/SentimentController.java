package ch.eglisi1.springweb.controller;

import ch.eglisi1.springweb.model.SentimentAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SentimentController {

    private final Logger logger = LoggerFactory.getLogger(SentimentController.class);
    private final SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();

    @GetMapping("/sentiment")
    public String getSentiment(@RequestParam(name = "text") String text) {
        if (text == null || text.length() == 0) {
            logger.debug("Text was either null or an empty String");
            return "Need to assign a parameter text!";
        }
        var prediction = sentimentAnalysis.getPredictionAsJson(text);
        logger.debug("Prediction for text '" + text + "' was: \n" + prediction);
        return prediction;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @PostMapping("/sentimentThymeleaf")
    public ModelAndView analyzeSentiment(@RequestParam("text") String text, Model model) {
        logger.info("text: " + text);
        String classifications = sentimentAnalysis.getPredictionAsJson(text);
        logger.info("classification:\n" + classifications);
        model.addAttribute("result", classifications);
        return new ModelAndView("result");
    }
}
