package com.example.featureflagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class FeatureFlagEvent {

        private String flagKey;

        private String action;

        private String oldValue;

        private String newValue;
    }
