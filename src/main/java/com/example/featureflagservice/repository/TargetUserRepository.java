package com.example.featureflagservice.repository;

import com.example.featureflagservice.entity.TargetUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetUserRepository extends JpaRepository<TargetUser, Long> {

}
