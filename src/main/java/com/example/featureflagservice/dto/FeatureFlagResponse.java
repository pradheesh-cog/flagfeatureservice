package com.example.featureflagservice.dto;



import com.example.featureflagservice.enums.RolloutType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureFlagResponse {

    private Long id;

    private String flagKey;

    private String description;

    private Boolean enabled;

    private RolloutType rolloutType;

    private Integer rolloutPercentage;
}