package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.service.AuditLogService;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl
        implements AuditLogService {
    @Override
    public void logChange(String flagKey, String oldValue, String newValue, String changedBy) {

    }
}
