package com.example.featureflagservice.service;

public interface TargetUserService {

    void addUser(
            String flagKey,
            String userId
    );
}