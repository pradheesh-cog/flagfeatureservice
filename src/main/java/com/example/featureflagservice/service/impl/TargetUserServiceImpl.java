package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.entity.TargetUser;
import com.example.featureflagservice.exception.ResourceNotFoundException;
import com.example.featureflagservice.repository.FeatureFlagRepository;
import com.example.featureflagservice.repository.TargetUserRepository;
import com.example.featureflagservice.service.TargetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TargetUserServiceImpl
        implements TargetUserService {

    private final FeatureFlagRepository featureFlagRepository;
    private final TargetUserRepository targetUserRepository;

    @Override
    public void addUser(
            String flagKey,
            String userId) {

        FeatureFlag flag =
                featureFlagRepository.findByFlagKey(flagKey)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Feature flag not found"));

        TargetUser targetUser =
                TargetUser.builder()
                        .userId(userId)
                        .featureFlag(flag)
                        .build();

        targetUserRepository.save(targetUser);
    }
}