package com.example.featureflagservice.service.impl;



import com.example.featureflagservice.entity.AuditLog;
import com.example.featureflagservice.repository.AuditLogRepository;
import com.example.featureflagservice.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void log(
            String flagKey,
            String action,
            String oldValue,
            String newValue,
            String changedBy) {

        AuditLog auditLog = AuditLog.builder()
                .flagKey(flagKey)
                .action(action)
                .oldValue(oldValue)
                .newValue(newValue)
                .changedBy(changedBy)
                .build();

        auditLogRepository.save(auditLog);
    }
}