package com.pack.fabo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.fabo.entity.Client;
import com.pack.fabo.repository.ClientRepository;
import com.pack.fabo.service.ClientService;

import jakarta.mail.internet.MimeMessage;

@Controller
public class ClientController {
	
	private final ClientRepository clientRepository;
    private final ClientService clientService;


	public ClientController(ClientRepository clientRepository, ClientService clientService) {
		this.clientRepository = clientRepository;
		this.clientService = clientService;
	}
	
	 @Autowired
	    private JavaMailSender javaMailSender;

	@RequestMapping("/faboAdd")
	public String faboPage(Model model) {
		Client client = new Client();
		model.addAttribute("client",client);
		return "AddClient";
	}
	
	@RequestMapping(value = {"/faboclients", "/faboclients"}, method = {RequestMethod.GET, RequestMethod.POST})
	 public String searchAndFilterClients(@RequestParam(value = "search", required = false) String search,
	                                      @RequestParam(value = "state", required = false) String state,
	                                      @RequestParam(value = "city", required = false) String city,
	                                      Model model) {

	     // Filter
	     List<String> states = clientRepository.findDistinctStates();
	     List<String> cities = clientRepository.findDistinctCities();
	     model.addAttribute("states", states);
	     model.addAttribute("cities", cities);

	     List<Client> clients;

	     if (search != null && !search.isEmpty()) {
	         // Search
	         clients = clientRepository.findBySearchTerm(search);
	     } else if (state != null && city != null) {
	         // Filter by state and city
	         clients = clientRepository.findByStateAndCity(state, city);
	     } else if (state != null && !state.isEmpty()) {
	         // Filter by state
	         clients = clientRepository.findByState(state);
	     } else if (city != null && !city.isEmpty()) {
	         // Filter by city
	         clients = clientRepository.findByCity(city);
	     } else {
	         // No search or filter, return all clients
	         clients = clientRepository.findAll();
	     }
	     
	 

	     model.addAttribute("clients", clients);
	     return "clientslist";
	 }

	@PostMapping("/fabopage")
	public String saveClients(@ModelAttribute("client") Client client) {
		clientService.saveClient(client);
		return "redirect:/faboclients";
	}
	
	@PostMapping("/clients")
	public String saveClient(@ModelAttribute("client") Client client) {
	    boolean isUpdate = false;
	     String email = client.getEmail();
	    Optional<Client> existingClientOptional = clientService.getClientByEmail(email);

	    if (existingClientOptional.isPresent()) {
	        // If the client exists, it's an update operation
	        isUpdate = true;
	    } else {
	        // If the client doesn't exist, it's a new entry, so save it
	        clientService.saveClient(client);
	    }

	    sendEmailNotification(client.getEmail(), client, isUpdate);
	    return "redirect:/faboclients";
	}



	
	private void sendEmailNotification(String toEmail, Client client, boolean isUpdate) {
	    try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, false); // Set to false for plain text

	        String subject = "Confirmation: Your Information is " + (isUpdate ? "Updated" : "Submitted");

	        // Construct email content with user details
	        String emailContent = "Your information has been ";
	        emailContent += (isUpdate ? "updated" : "submitted") + " successfully. Below are the details you provided:\n\n";
	        emailContent += "Store Name: " + client.getStorename() + "\n";
	        emailContent += "Store Code: " + client.getStorecode() + "\n";
	        emailContent += "Owner Name: " + client.getOwnername() + "\n";
	        emailContent += "State: " + client.getState() + "\n";
	        emailContent += "City: " + client.getCity() + "\n";
	        emailContent += "Full Address: " + client.getFullAddress() + "\n";
	        // Add other fields similarly
	        System.out.println("isUpdate value: " + isUpdate);
	        System.out.println("Subject: " + subject);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(emailContent);

	        javaMailSender.send(message); // Send the email
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle email sending exception
	    }
	}

	
	@GetMapping("clients/edit/{Id}")
	public String editClient(@PathVariable Long Id, Model model) {
		model.addAttribute("client", clientService.getClientById(Id));
		return "clientsedit";
	}
	
	@RequestMapping("clientview/{Id}")
	public String viewClient(@PathVariable Long Id, Model model) {
		model.addAttribute("client", clientService.getClientById(Id));
		return "clientsview";
	}

	@PostMapping("clients/{Id}")
	public String updateClient(@PathVariable Long Id,String email,
	        @ModelAttribute("client") Client client, Model model) {
	    Client existingClient = clientService.getClientById(Id);
	    if (existingClient != null) {
	        existingClient.setStorecode(client.getStorecode());
	        existingClient.setStorename(client.getStorename());
	        existingClient.setEmail(client.getEmail());
	        existingClient.setPrimaryNumber(client.getPrimaryNumber());
	        existingClient.setSecondaryNumber(client.getSecondaryNumber());
	        existingClient.setState(client.getState());
	        existingClient.setCity(client.getCity());
	        existingClient.setFullAddress(client.getFullAddress());

	        clientService.updateClient(existingClient);
	        sendEmailNotification(email, existingClient, true); // Pass isUpdate as true
	    }
	    return "redirect:/faboclients";
	}
	
	@GetMapping("/client/{Id}")
	public String deleteClient(@PathVariable Long Id) {
		clientService.deleteClientById(Id);
		return "redirect:/faboclients";
	}
	
	@PostMapping("/handleButtonClick")
    public String handleButtonClick(Model model) {
        return "redirect:/faboclients"; 
    }
	
	 
	 /*@PostMapping("/faboclients")
	 public String searchClients(@RequestParam(value = "search", required = false) String search, Model model) {
	     List<Client> clientss;

	     if (search != null && !search.isEmpty()) {

	         clientss = clientRepository.findBySearchTerm(search);
     } else {

	         clientss = clientRepository.findAll();
	 }

     model.addAttribute("clients", clientss);

     return "faboclients";
	 }*/
	 

}
