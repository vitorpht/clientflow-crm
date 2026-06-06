package com.vitorpht.clientflowcrm.repository;

import com.vitorpht.clientflowcrm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
