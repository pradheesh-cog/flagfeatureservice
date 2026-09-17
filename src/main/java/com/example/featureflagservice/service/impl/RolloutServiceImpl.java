package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.service.RolloutService;

public class RolloutServiceImpl implements RolloutService {
    @Override
    public boolean evaluateFlag(String flagKey, String userId) {
        return false;
    }
}
