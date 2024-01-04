package com.pack.fabo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.Invoice;

@Service
public interface InvoiceService {

List<Invoice> getAllInvoices();
	
	Invoice saveInvoice(Invoice Invoice);
	
	Invoice getInvoiceById(Long id);
	
	Invoice updateInvoice(Invoice Invoice);
	
	void deleteInvoiceById(Long id);

}
