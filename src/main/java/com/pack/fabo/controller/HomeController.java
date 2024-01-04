package com.pack.fabo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pack.fabo.entity.Admin;
import com.pack.fabo.entity.Client;
import com.pack.fabo.entity.ClientUser;
import com.pack.fabo.service.ClientService;

@Controller
public class HomeController {
	
private ClientService clientService;
	
	public HomeController(ClientService clientService) {
		this.clientService = clientService;
	}
	
    @GetMapping("/home")    
    public String show(Model model) {
		Client client = new Client();
		model.addAttribute("client",client);
		ClientUser clientUser = new ClientUser();
		model.addAttribute("clientUser",clientUser);
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		 List<Client> clients = clientService.getAllClients();
	        model.addAttribute("clients", clients);
		return "home"; 
    }
}
