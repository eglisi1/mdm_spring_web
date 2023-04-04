package ch.eglisi1.springweb.model;

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

import java.io.IOException;

public class SentimentAnalysis {
    private final Logger logger = LoggerFactory.getLogger(SentimentAnalysis.class);
    private Predictor<String, Classifications> predictor;

    public SentimentAnalysis() {
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

    public String getPredictionAsJson(String text) {
        try {
            Classifications classifications = predictor.predict(text);
            return classifications.toJson();
        } catch (TranslateException e) {
            logger.error("Can't predict");
            logger.error(e.getMessage());
            return null;
        }
    }
}
