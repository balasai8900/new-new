package com.pack.fabo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.fabo.entity.Invoice;
import com.pack.fabo.repository.InvoiceRepository;
import com.pack.fabo.service.InvoiceService;

@Controller
public class InvoiceController {

	private InvoiceService invoiceService;
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	public InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository) {
		this.invoiceService = invoiceService;
		this.invoiceRepository = invoiceRepository;
	}
	
	
		//calling addinvoice form
	    @GetMapping("/addinvoice")
	    public String showaddInvoiceForm(Model model) {
	        model.addAttribute("invoice", new Invoice());
	        return "addinvoice"; 
	    }
	    //Connectin to html and saving invoice
	    @PostMapping("/createInvoice")
	    public String saveInvoice(@ModelAttribute("invoice") Invoice invoice) {
	        invoiceService.saveInvoice(invoice);
	        return "redirect:/viewinvoice";
	    }

	    @RequestMapping("/viewinvoice")
	    public String listAndSearchInvoices(
	            @RequestParam(value = "search", required = false) String search,
	            @RequestParam(value = "invoiceType", required = false) String invoiceType,
	            Model model) {

	        // Retrieve all distinct invoice types for the filter dropdown
	        List<String> distinctInvoiceTypes = invoiceRepository.findDistinctInvoiceType();
	        model.addAttribute("distinctInvoiceTypes", distinctInvoiceTypes);

	        // Retrieve all invoices from the service as a List
	        List<Invoice> invoices;

	        if (search != null && !search.isEmpty()) {
	            // If search parameter is provided, filter by search term
	            invoices = invoiceRepository.findBySearchTerm(search);
	        } else if (invoiceType != null && !invoiceType.isEmpty()) {
	            // If invoiceType parameter is provided, filter by invoice type
	            invoices = invoiceRepository.findByInvoiceType(invoiceType);
	        } else {
	            // Otherwise, retrieve all invoices
	            invoices = invoiceService.getAllInvoices();
	        }

	        // Add the list of invoices, search parameter, and selected invoiceType to the model
	        model.addAttribute("invoices", invoices);
	        model.addAttribute("search", search);
	        model.addAttribute("selectedInvoiceType", invoiceType);

	        return "viewinvoice"; 
	    }
	
	    //View
	    @RequestMapping("/viewinvoicedetails/{id}")
	    public String viewInvoiceDetails(@PathVariable Long id, Model model) {
	    	model.addAttribute("invoice", invoiceService.getInvoiceById(id));
	    	return "viewinvoicedetails";
	    }
	    
	    //Edit
	    @GetMapping("/invoice/edit/{id}")
	    public String editInvoic(@PathVariable Long id, Model model) {
	    	model.addAttribute("invoice", invoiceService.getInvoiceById(id) );
	    	return "invoiceEdit";	   
	    }
	    
	    
	    @GetMapping("/invoice/delete/{id}")
	    public String deleteInvoice(@PathVariable Long id) {
	        invoiceService.deleteInvoiceById(id);
	        return "redirect:/viewinvoice";
	    }

	    
	    	    
	    
}
