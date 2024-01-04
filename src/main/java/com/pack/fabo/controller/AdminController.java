package com.pack.fabo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.fabo.entity.Admin;
import com.pack.fabo.entity.ClientUser;
import com.pack.fabo.repository.AdminRepository;
import com.pack.fabo.service.AdminService;
import com.pack.fabo.service.RolesTableService; 

@Controller
public class AdminController {

	private final AdminRepository adminRepository;
    private final AdminService adminService;
    private RolesTableService rolesTableService;
    
    public AdminController(AdminRepository adminRepository, AdminService adminService,
			RolesTableService rolesTableService) {
		this.adminRepository = adminRepository;
		this.adminService = adminService;
		this.rolesTableService = rolesTableService;
	}

	//private final AdminEmailService adminEmailService;
	
 

	@PostMapping("/addAdmin")
    public String addAdmin(@RequestParam String email,Model model,@ModelAttribute("admin") Admin admin,
                                @RequestParam List<Long> roleIds){
			String userName = admin.getUserName();
	    
	    if (adminService.isUsernameDuplicate(userName)) {
            // Username is a duplicate, show error
            model.addAttribute("error", "Username already exists");
            return "redirect:/faboAdmins"; // Return to the form with an error message
        }
	    else {
		adminService.addAdminAndRoles(admin, roleIds);
        adminService.saveAdmin(admin);
        String concatenatedRoleNames = rolesTableService.getConcatenatedRoleNamesByEmail(email, roleIds);
	    adminService.updateConcatenatedRolesByEmail(email, concatenatedRoleNames);
        model.addAttribute("email", email);
		return "redirect:/faboAdmins";
    }
	}
	
	@RequestMapping("/faboAdmins")
	public String listAdmins(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "accesses", required = false) String accesses,Model model) {
		 List<Admin> admins;
		 if (accesses != null && !accesses.isEmpty()) {
	         admins = adminRepository.findByConcatenatedRoleNamesContaining(accesses);
	     }
		 else if (search != null && !search.isEmpty()) {
	         admins = adminRepository.findBySearchTerm(search);
	         }
		 else {
	         admins = adminRepository.findAll();
	     }
        model.addAttribute("admins", admins);
		return "adminslist";
	}
	
	
	@GetMapping("admins/edit/{userName}")
	public String editAdmin(@PathVariable String userName, Model model) {
		model.addAttribute("admin", adminService.getAdminById(userName));
		return "adminedit";
	}
	
	@RequestMapping("adminview/{userName}")
	public String viewAdminUser(@PathVariable String userName, Model model) {
		model.addAttribute("admin", adminService.getAdminById(userName));
		return "adminview";
	}

	@PostMapping("admins/{userName}")
	public String updateAdmin(@PathVariable String userName,String email,
			@RequestParam List<Long> roleIds,
	        @ModelAttribute("admin") Admin admin, Model model) {
	    Admin existingAdmin = adminService.getAdminById(userName);
	    
	    adminService.addAdminAndRoles(admin, roleIds);

	    // Update admin details
	    existingAdmin.setAdminName(admin.getAdminName());
	    existingAdmin.setEmail(admin.getEmail());
	    existingAdmin.setUserName(admin.getUserName()); 
	    existingAdmin.setPhoneNumber(admin.getPhoneNumber());
	    existingAdmin.setConcatenatedRoleNames("");
	    String concatenatedRoleNames = rolesTableService.getConcatenatedRoleNamesByEmail(email, roleIds);
	    adminService.updateConcatenatedRolesByEmail(email, concatenatedRoleNames);
        model.addAttribute("email", email);
	   
	    adminService.updateAdmin(existingAdmin);
	    return "redirect:/faboAdmins";
	}
	
	@GetMapping("admin/{email}")
	public String deleteAdmin(@PathVariable String email) {
		adminService.removeAdminAndAssociationsByEmail(email);
		return "redirect:/faboAdmins";
	}
	
	@PostMapping("/Clicks")
    public String handleButtonClick(Model model) {
        return "redirect:/faboAdmins"; 
    }
	

}
