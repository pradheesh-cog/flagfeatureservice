package com.example.featureflagservice.service;




import com.example.featureflagservice.dto.CreateFeatureFlagRequest;
import com.example.featureflagservice.dto.UpdateFeatureFlagRequest;
import com.example.featureflagservice.dto.FeatureFlagResponse;

import java.util.List;

public interface FeatureFlagService {

    FeatureFlagResponse createFlag(CreateFeatureFlagRequest request);

    FeatureFlagResponse getFlag(String flagKey);

    List<FeatureFlagResponse> getAllFlags();

    FeatureFlagResponse updateFlag(
            String flagKey,
            UpdateFeatureFlagRequest request);

    FeatureFlagResponse enableFlag(String flagKey);

    FeatureFlagResponse disableFlag(String flagKey);

    void deleteFlag(String flagKey);
}