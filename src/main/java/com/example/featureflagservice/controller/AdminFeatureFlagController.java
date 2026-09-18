package com.example.featureflagservice.controller;


import com.example.featureflagservice.dto.CreateFeatureFlagRequest;
import com.example.featureflagservice.dto.UpdateFeatureFlagRequest;
import com.example.featureflagservice.dto.FeatureFlagResponse;
import com.example.featureflagservice.service.FeatureFlagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/flags")
@RequiredArgsConstructor
public class AdminFeatureFlagController {

    private final FeatureFlagService featureFlagService;

    @PostMapping
    public FeatureFlagResponse createFlag(
            @Valid @RequestBody CreateFeatureFlagRequest request) {

        return featureFlagService.createFlag(request);
    }

    @GetMapping
    public List<FeatureFlagResponse> getAllFlags() {

        return featureFlagService.getAllFlags();
    }

    @GetMapping("/{flagKey}")
    public FeatureFlagResponse getFlag(
            @PathVariable String flagKey) {

        return featureFlagService.getFlag(flagKey);
    }

    @PutMapping("/{flagKey}")
    public FeatureFlagResponse updateFlag(
            @PathVariable String flagKey,
            @RequestBody UpdateFeatureFlagRequest request) {

        return featureFlagService.updateFlag(flagKey, request);
    }

    @PutMapping("/{flagKey}/enable")
    public FeatureFlagResponse enableFlag(
            @PathVariable String flagKey) {

        return featureFlagService.enableFlag(flagKey);
    }

    @PutMapping("/{flagKey}/disable")
    public FeatureFlagResponse disableFlag(
            @PathVariable String flagKey) {

        return featureFlagService.disableFlag(flagKey);
    }

    @DeleteMapping("/{flagKey}")
    public void deleteFlag(
            @PathVariable String flagKey) {

        featureFlagService.deleteFlag(flagKey);
    }
}