package com.example.featureflagservice.dto;


import com.example.featureflagservice.enums.RolloutType;
import lombok.Data;

@Data
public class UpdateFeatureFlagRequest {

    private String description;

    private Boolean enabled;

    private RolloutType rolloutType;

    private Integer rolloutPercentage;
}