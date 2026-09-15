package com.example.featureflagservice.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "target_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TargetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_flag_id")
    private FeatureFlag featureFlag;
}
