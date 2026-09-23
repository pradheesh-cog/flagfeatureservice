package com.example.featureflagservice.messaging;

import com.example.featureflagservice.dto.FeatureFlagEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureFlagEventProducer {

    private final JmsTemplate jmsTemplate;

    public void publish(
            FeatureFlagEvent event) {

        jmsTemplate.convertAndSend(
                "feature-flag-events",
                event
        );
    }
}