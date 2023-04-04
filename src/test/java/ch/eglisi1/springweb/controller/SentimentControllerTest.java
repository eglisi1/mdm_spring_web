package ch.eglisi1.springweb.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SentimentControllerTest {

    private final SentimentController sentimentController = new SentimentController();

    @Test
    void getSentiment() {
        var result = sentimentController.getSentiment("Hello World!");
        Assertions.assertNotNull(result);
    }

    @Test
    void getSentiment_null() {
        var result = sentimentController.getSentiment(null);
        Assertions.assertNotNull(result);
    }
}