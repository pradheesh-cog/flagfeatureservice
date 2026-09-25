package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.enums.RolloutType;
import com.example.featureflagservice.exception.ResourceNotFoundException;
import com.example.featureflagservice.repository.FeatureFlagRepository;
import com.example.featureflagservice.repository.TargetUserRepository;
import com.example.featureflagservice.service.RolloutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolloutServiceImpl implements RolloutService {

    private final FeatureFlagRepository featureFlagRepository;
    private final TargetUserRepository targetUserRepository;

    @Override
    public boolean evaluateFlag(
            String flagKey,
            String userId) {

        FeatureFlag flag = featureFlagRepository
                .findByFlagKey(flagKey)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Feature flag not found: "
                                        + flagKey
                        ));

        // Master switch
        if (!Boolean.TRUE.equals(flag.getEnabled())) {
            return false;
        }

        // Global rollout
        if (flag.getRolloutType() == RolloutType.GLOBAL) {
            return true;
        }

        // Percentage rollout
        if (flag.getRolloutType() == RolloutType.PERCENTAGE) {

            int bucket =
                    Math.abs(userId.hashCode()) % 100;

            return bucket < flag.getRolloutPercentage();
        }

        // User specific rollout
        if (flag.getRolloutType() == RolloutType.USER_TARGETED) {

            return targetUserRepository
                    .existsByFeatureFlagAndUserId(
                            flag,
                            userId
                    );
        }

        return false;
    }
}