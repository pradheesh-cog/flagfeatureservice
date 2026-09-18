package com.example.featureflagservice.controller;

import com.example.featureflagservice.dto.FeatureFlagResponse;
import com.example.featureflagservice.service.FeatureFlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flags")
@RequiredArgsConstructor
public class FeatureFlagController {

    private final FeatureFlagService featureFlagService;

    @GetMapping("/{flagKey}")
    public FeatureFlagResponse getFlag(
            @PathVariable String flagKey) {

        return featureFlagService.getFlag(flagKey);
    }
}