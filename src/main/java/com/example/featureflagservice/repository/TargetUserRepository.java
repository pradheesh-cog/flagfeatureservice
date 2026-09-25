package com.example.featureflagservice.repository;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.entity.TargetUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetUserRepository extends JpaRepository<TargetUser, Long> {
    boolean existsByFeatureFlagAndUserId(FeatureFlag featureFlag, String userId);

    List<TargetUser> findByFeatureFlag(FeatureFlag featureFlag);
}
