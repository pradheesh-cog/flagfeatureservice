package com.example.featureflagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureEvaluationResponse {

    private String flagKey;

    private String userId;

    private Boolean enabled;
}