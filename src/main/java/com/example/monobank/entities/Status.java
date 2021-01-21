package com.example.monobank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    @NonNull()
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    @Builder.Default
    private Status.BidStatus bidStatus = BidStatus.NEW;

    public enum BidStatus {
        NEW,
        IN_PROGRESS,
        ERROR,
        DONE
    }
}
