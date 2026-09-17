package com.example.featureflagservice.service;

public interface AuditLogService {

    void logChange(
            String flagKey,
            String oldValue,
            String newValue,
            String changedBy);
}