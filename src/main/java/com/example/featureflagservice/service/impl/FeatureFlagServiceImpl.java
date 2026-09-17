package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.dto.CreateFeatureFlagRequest;
import com.example.featureflagservice.dto.UpdateFeatureFlagRequest;
import com.example.featureflagservice.dto.FeatureFlagResponse;
import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.exception.ResourceNotFoundException;
import com.example.featureflagservice.repository.FeatureFlagRepository;
import com.example.featureflagservice.service.FeatureFlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureFlagServiceImpl implements FeatureFlagService {

    private final FeatureFlagRepository featureFlagRepository;

    @Override
    public FeatureFlagResponse createFlag(
            CreateFeatureFlagRequest request) {

        FeatureFlag featureFlag = FeatureFlag.builder()
                .flagKey(request.getFlagKey())
                .description(request.getDescription())
                .enabled(request.getEnabled())
                .rolloutType(request.getRolloutType())
                .rolloutPercentage(request.getRolloutPercentage())
                .build();
        if (featureFlagRepository.existsByFlagKey(
                request.getFlagKey())) {

            throw new IllegalArgumentException(
                    "Flag already exists");
        }
        FeatureFlag savedFlag =
                featureFlagRepository.save(featureFlag);

        return mapToResponse(savedFlag);
    }

    @Override
    public FeatureFlagResponse getFlag(String flagKey) {

        FeatureFlag flag = findFlagByKey(flagKey);

        return mapToResponse(flag);
    }

    @Override
    public List<FeatureFlagResponse> getAllFlags() {

        return featureFlagRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FeatureFlagResponse updateFlag(
            String flagKey,
            UpdateFeatureFlagRequest request) {

        FeatureFlag flag = findFlagByKey(flagKey);

        flag.setDescription(request.getDescription());
        flag.setEnabled(request.getEnabled());
        flag.setRolloutType(request.getRolloutType());
        flag.setRolloutPercentage(
                request.getRolloutPercentage());

        FeatureFlag updatedFlag =
                featureFlagRepository.save(flag);

        return mapToResponse(updatedFlag);
    }

    @Override
    public FeatureFlagResponse enableFlag(String flagKey) {

        FeatureFlag flag = findFlagByKey(flagKey);

        flag.setEnabled(true);

        return mapToResponse(
                featureFlagRepository.save(flag)
        );
    }

    @Override
    public FeatureFlagResponse disableFlag(String flagKey) {

        FeatureFlag flag = findFlagByKey(flagKey);

        flag.setEnabled(false);

        return mapToResponse(
                featureFlagRepository.save(flag)
        );
    }

    @Override
    public void deleteFlag(String flagKey) {

        FeatureFlag flag = findFlagByKey(flagKey);

        featureFlagRepository.delete(flag);
    }

    private FeatureFlag findFlagByKey(String flagKey) {

        return featureFlagRepository.findByFlagKey(flagKey)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Feature flag not found: " + flagKey
                        ));
    }

    private FeatureFlagResponse mapToResponse(
            FeatureFlag featureFlag) {

        return FeatureFlagResponse.builder()
                .id(featureFlag.getId())
                .flagKey(featureFlag.getFlagKey())
                .description(featureFlag.getDescription())
                .enabled(featureFlag.getEnabled())
                .rolloutType(featureFlag.getRolloutType())
                .rolloutPercentage(
                        featureFlag.getRolloutPercentage()
                )
                .build();
    }
}