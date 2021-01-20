package com.example.monobank.repositories;

import com.example.monobank.entities.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByBidStatus(Status.BidStatus bidStatus);
}
