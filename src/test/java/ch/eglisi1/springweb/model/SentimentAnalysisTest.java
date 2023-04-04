package ch.eglisi1.springweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentimentAnalysisTest {

    SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();

    @Test
    void getPrediction() {
        var result = sentimentAnalysis.getPredictionAsJson("Hello World!");
        assertNotNull(result);
    }

    @Test
    void getPrediction_null() {
        var result = sentimentAnalysis.getPredictionAsJson(null);
        assertNull(result);
    }

    @Test
    void getPrediction_noText() {
        var result = sentimentAnalysis.getPredictionAsJson("");
        assertNull(result);
    }
}