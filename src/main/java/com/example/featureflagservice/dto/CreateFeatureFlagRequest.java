package com.example.featureflagservice.dto;



import com.example.featureflagservice.enums.RolloutType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFeatureFlagRequest {

    @NotBlank
    private String flagKey;

    private String description;

    private Boolean enabled;

    private RolloutType rolloutType;

    private Integer rolloutPercentage;
}