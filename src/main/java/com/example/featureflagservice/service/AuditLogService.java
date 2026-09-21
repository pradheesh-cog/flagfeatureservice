package com.example.featureflagservice.service;



public interface AuditLogService {

    void log(
            String flagKey,
            String action,
            String oldValue,
            String newValue,
            String changedBy
    );
}