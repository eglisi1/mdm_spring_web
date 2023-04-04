package ch.eglisi1.springweb.controller;

import ch.eglisi1.springweb.model.SentimentAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
