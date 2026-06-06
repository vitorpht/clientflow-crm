package com.vitorpht.clientflowcrm.controller;

import com.vitorpht.clientflowcrm.model.Client;
import com.vitorpht.clientflowcrm.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("totalClients", clientService.getTotalClients());
		model.addAttribute("clientsCreatedToday", clientService.getClientsCreatedToday());
		model.addAttribute("latestClients", clientService.getLatestClients());
		return "index";
	}

	@GetMapping("/clients")
	public String listClients(@RequestParam(required = false) String search, Model model) {
		model.addAttribute("clients", clientService.searchClients(search));
		model.addAttribute("search", search != null ? search : "");
		return "clients";
	}

	@GetMapping("/clients/new")
	public String showCreateForm(Model model) {
		model.addAttribute("client", new Client());
		return "client-form";
	}

	@PostMapping("/clients")
	public String createClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "client-form";
		}
		clientService.saveClient(client);
		return "redirect:/clients";
	}

	@GetMapping("/clients/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		return clientService.getClientById(id)
				.map(client -> {
					model.addAttribute("client", client);
					return "client-form";
				})
				.orElse("redirect:/clients");
	}

	@PostMapping("/clients/update/{id}")
	public String updateClient(@PathVariable Long id, @Valid @ModelAttribute("client") Client client,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			client.setId(id);
			return "client-form";
		}
		if (clientService.updateClient(id, client).isEmpty()) {
			return "redirect:/clients";
		}
		return "redirect:/clients";
	}

	@PostMapping("/clients/delete/{id}")
	public String deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
		return "redirect:/clients";
	}

}
