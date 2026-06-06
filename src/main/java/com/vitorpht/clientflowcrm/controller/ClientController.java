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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/clients")
	public String listClients(Model model) {
		model.addAttribute("clients", clientService.getAllClients());
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

}
