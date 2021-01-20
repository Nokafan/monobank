package com.example.monobank.repositories;

import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    Optional<Bid> findFirstByStatus(Status status);
}
