package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.exception.ResourceNotFoundException;
import com.example.featureflagservice.repository.FeatureFlagRepository;
import com.example.featureflagservice.service.RolloutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.featureflagservice.enums.RolloutType;

@Service
@RequiredArgsConstructor
public class RolloutServiceImpl implements RolloutService {

    private final FeatureFlagRepository featureFlagRepository;

    @Override
    public boolean evaluateFlag(
            String flagKey,
            String userId) {

        FeatureFlag flag = featureFlagRepository
                .findByFlagKey(flagKey)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Feature flag not found: " + flagKey
                        ));

        if (!Boolean.TRUE.equals(flag.getEnabled())) {
            return false;
        }

        if (flag.getRolloutType() == RolloutType.GLOBAL) {
            return true;
        }

        if (flag.getRolloutType() == RolloutType.PERCENTAGE) {

            int bucket =
                    Math.abs(userId.hashCode()) % 100;

            return bucket < flag.getRolloutPercentage();
        }

        return false;
    }
}