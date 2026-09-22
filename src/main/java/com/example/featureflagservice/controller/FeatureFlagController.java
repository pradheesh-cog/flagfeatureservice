package com.example.featureflagservice.controller;


import com.example.featureflagservice.dto.FeatureEvaluationResponse;
import com.example.featureflagservice.service.RolloutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flags")
@RequiredArgsConstructor
public class FeatureFlagController {

    private final RolloutService rolloutService;

    @GetMapping("/{flagKey}/evaluate")
    public FeatureEvaluationResponse evaluateFlag(
            @PathVariable String flagKey,
            @RequestParam String userId) {

        boolean enabled =
                rolloutService.evaluateFlag(
                        flagKey,
                        userId
                );

        return new FeatureEvaluationResponse(
                flagKey,
                userId,
                enabled
        );
    }
}