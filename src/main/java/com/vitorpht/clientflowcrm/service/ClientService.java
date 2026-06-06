package com.vitorpht.clientflowcrm.service;

import com.vitorpht.clientflowcrm.model.Client;
import com.vitorpht.clientflowcrm.repository.ClientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public List<Client> searchClients(String search) {
		if (search == null || search.isBlank()) {
			return getAllClients();
		}
		return clientRepository.findByNameContainingIgnoreCase(search.trim());
	}

	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	public Optional<Client> updateClient(Long id, Client updated) {
		return clientRepository.findById(id).map(existing -> {
			existing.setName(updated.getName());
			existing.setEmail(updated.getEmail());
			existing.setPhone(updated.getPhone());
			existing.setCompany(updated.getCompany());
			return clientRepository.save(existing);
		});
	}

	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

	public long getTotalClients() {
		return clientRepository.count();
	}

	public long getClientsCreatedToday() {
		LocalDate today = LocalDate.now();
		LocalDateTime startOfDay = today.atStartOfDay();
		LocalDateTime startOfTomorrow = today.plusDays(1).atStartOfDay();
		return clientRepository.countByCreatedAtGreaterThanEqualAndCreatedAtLessThan(startOfDay, startOfTomorrow);
	}

	public List<Client> getLatestClients() {
		return clientRepository.findTop5ByOrderByCreatedAtDesc();
	}

}
