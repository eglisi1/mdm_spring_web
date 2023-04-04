package ch.eglisi1.springweb.controller;

import ai.djl.Application;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SentimentController {
    Predictor<String, Classifications> predictor;
    private final Logger logger = LoggerFactory.getLogger(SentimentController.class);

    public SentimentController() {
        try {
            Criteria<String, Classifications> criteria = Criteria.builder()
                    .optApplication(Application.NLP.SENTIMENT_ANALYSIS)
                    .setTypes(String.class, Classifications.class)
                    // This model was traced on CPU and can only run on CPU .optDevice(Device.cpu())
                    .optProgress(new ProgressBar())
                    .build();
            ZooModel<String, Classifications> model = criteria.loadModel();
            predictor = model.newPredictor();
        } catch (MalformedModelException | ModelNotFoundException | IOException e) {
            logger.error("Can't instantiate model");
            logger.error(e.getMessage());
        }
    }

    @GetMapping("/sentiment")
    public String getSentiment(@RequestParam(name = "text") String text) {
        if (text == null || text.length() == 0) {
            return "Need to assign a parameter text!";
        }
        try {
            return predictor.predict(text).toJson();
        } catch (TranslateException e) {
            logger.error("Can't translate text: " + text);
            logger.error(e.getMessage());
            return "Error. Contact your System Administrator";
        }
    }
}
