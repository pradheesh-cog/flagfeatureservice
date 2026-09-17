package com.example.featureflagservice.service;

public interface RolloutService {

    boolean evaluateFlag(
            String flagKey,
            String userId);
}
