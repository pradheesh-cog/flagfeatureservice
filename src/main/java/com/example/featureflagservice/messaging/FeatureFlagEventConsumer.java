package com.example.featureflagservice.messaging;

import com.example.featureflagservice.dto.FeatureFlagEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class FeatureFlagEventConsumer {

    @JmsListener(destination = "feature-flag-events")
    public void consume(
            FeatureFlagEvent event) {

        System.out.println(
                "Received event : " + event
        );
    }
}