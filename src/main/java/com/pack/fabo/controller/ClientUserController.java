package com.pack.fabo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.fabo.entity.ClientUser;
import com.pack.fabo.repository.ClientUserRepository;
import com.pack.fabo.service.ClientUserService;
import com.pack.fabo.service.RolesTableService;

@Controller
public class ClientUserController {
			
	private ClientUserService clientUserService;
	private ClientUserRepository clientUserRepository;
	private RolesTableService rolesTableService;
	//private ClientUserEmailService clientUserEmailService;

	public ClientUserController(ClientUserService clientUserService, ClientUserRepository clientUserRepository,
			RolesTableService rolesTableService) {
		this.clientUserService = clientUserService;
		this.clientUserRepository = clientUserRepository;
		this.rolesTableService = rolesTableService;
	}


	@PostMapping("/add")
	public String addClientUser(@RequestParam String email,
	    Model model,
	    @RequestParam("storeName") String storename,
        @RequestParam("storeCode") String storecode,
	    @RequestParam List<Long> roleIds,
	    @ModelAttribute("clientUser") ClientUser clientUser,
	    @RequestParam(value = "accesses", required = false) List<String> accesses
	) {
	   	    
	    String username = clientUser.getUserName();
	    
	    if (clientUserService.isUsernameDuplicate(username)) {
            // Username is a duplicate, show error
            model.addAttribute("error", "Username already exists");
            return "redirect:/faboClientUsers"; // Return to the form with an error message
        }
	    else {
	    
	    // Logic to add ClientUser and associated roles
	    clientUserService.addClientUserAndRoles(clientUser, roleIds);
	    //clientUserEmailService.sendSimpleMessage(clientUser, false);

	    // Save the updated clientUser object (including concatenatedRoleNames)
	    clientUserService.saveUser(clientUser);
	    String concatenatedRoleNames = rolesTableService.getConcatenatedRoleNamesByEmail(email, roleIds);
	    clientUserService.updateConcatenatedRolesByEmail(email, concatenatedRoleNames);
        model.addAttribute("email", email);
	    return "redirect:/faboClientUsers";
	}
	}
 
	 @PostMapping("/ClientUsers")
	 public String saveClientUsers(@ModelAttribute("clientUser") ClientUser clientUser) {
		 clientUserService.saveUser(clientUser);
		 return "redirect:/faboClientUsers";
	 }
	
	@RequestMapping(value = {"/faboClientUsers", "/faboClientUsers"}, method = {RequestMethod.GET, RequestMethod.POST})
	 public String accessAndStorecodeFilters(@RequestParam(value = "search", required = false) String search,
	                                      @RequestParam(value = "storeName", required = false) String storeName,
	                                      @RequestParam(value = "storeCode", required = false) String storeCode, 
	                                      @RequestParam(value = "accesses", required = false) String accesses,
	                                      Model model) {

	     // Filter
	     List<String> storeCodes = clientUserRepository.findDistinctStoreCodes();
	     List<String> storeNames = clientUserRepository.findDistinctStoreNames();
	     model.addAttribute("storeCodes", storeCodes);
	     model.addAttribute("storeNames", storeNames);	    

	     List<ClientUser> clientUsers;
	    
	     if (accesses != null && !accesses.isEmpty()) {
	         clientUsers = clientUserRepository.findByConcatenatedRoleNamesContaining(accesses);
	     }
	     else if (search != null && !search.isEmpty()) {
	         // Search
	         clientUsers = clientUserRepository.findBySearchTerm(search);
	     } else if (storeCode != null && !storeCode.isEmpty()) {
	         // Filter by state
	         clientUsers = clientUserRepository.findByStoreCode(storeCode);
	     }else if (storeName != null && !storeName.isEmpty()) {
	         // Filter by state
	         clientUsers = clientUserRepository.findByStoreName(storeName);
	     }
	     else {
	         // No search or filter, return all clients
	         clientUsers = clientUserRepository.findAll();
	     }
	    

	     model.addAttribute("clientUsers", clientUsers);
	     return "userslist";
	 }
	
 
	//EDIT
	@GetMapping("/clientUsers/edit/{userName}")
	public String editClientUser(@PathVariable String userName, Model model) {
		model.addAttribute("clientUser", clientUserService.getClientUserById(userName));
		return "usersedit";
	}
	
	@RequestMapping("/Users/{userName}")
	public String viewClientUser(@PathVariable String userName, Model model) {
		model.addAttribute("clientUser", clientUserService.getClientUserById(userName));
		return "userview";
	}
	
	@PostMapping("/clientUsers/{userName}")
	public String updateClientUser(
	    @PathVariable String userName,  String email,
	    @RequestParam List<Long> roleIds,
	    @ModelAttribute("clientUser") ClientUser clientUser,
	    Model model,
	    @RequestParam(value = "accesses", required = false) List<ClientUser> accesses
	) {
	    // Add new roles and clear previous roles
	    clientUserService.addClientUserAndRoles(clientUser, roleIds);

	    // Retrieve existing client user
	    ClientUser existingClientUser = clientUserService.getClientUserById(userName);

	    // Set updated properties
	    existingClientUser.setStoreCode(clientUser.getStoreCode());
	    existingClientUser.setUserName(clientUser.getUserName());
	    existingClientUser.setEmail(clientUser.getEmail());
	    existingClientUser.setConcatenatedRoleNames("");
	    String concatenatedRoleNames = rolesTableService.getConcatenatedRoleNamesByEmail(email, roleIds);
	    clientUserService.updateConcatenatedRolesByEmail(email, concatenatedRoleNames);
        model.addAttribute("email", email);

	    // Update the user
	    clientUserService.updateUser(existingClientUser);

	    return "redirect:/faboClientUsers";
	}

	@GetMapping("/clientUser/{email}")
	public String deleteClientUser(@PathVariable String email) {
	    clientUserService.removeClientUserAndAssociationsByEmail(email);
	    return "redirect:/faboClientUsers";
	}
	
	@GetMapping("/Click")
    public String handleButtonClick(Model model) {
        return "redirect:/faboClientUsers"; 
    }
	
	/*
	 @PostMapping("/faboclients/search")
	 public String searchUser(@RequestParam(value = "search", required = false) String search, Model model) {
	     List<AddUser> addUser;

	     if (search != null && !search.isEmpty()) {

	    	 addUser = AddUserRepository.findBySearchTerm(search);
	     } else {

	    	 //addUser = UserRepository.findAll();
	     }
	    // model.addAttribute("clients", clients);

	     return "faboclients";
}*/
}