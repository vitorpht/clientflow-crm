package com.vitorpht.clientflowcrm.repository;

import com.vitorpht.clientflowcrm.model.Client;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

	long countByCreatedAtGreaterThanEqualAndCreatedAtLessThan(LocalDateTime start, LocalDateTime end);

	List<Client> findTop5ByOrderByCreatedAtDesc();

	List<Client> findByNameContainingIgnoreCase(String name);

}
