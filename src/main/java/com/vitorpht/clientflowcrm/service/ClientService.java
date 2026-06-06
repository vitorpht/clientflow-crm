package com.vitorpht.clientflowcrm.service;

import com.vitorpht.clientflowcrm.model.Client;
import com.vitorpht.clientflowcrm.repository.ClientRepository;
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

	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

}
