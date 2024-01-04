package com.pack.fabo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.fabo.entity.Invoice;
import com.pack.fabo.repository.InvoiceRepository;
import com.pack.fabo.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	 @Override
	    public List<Invoice> getAllInvoices() {
	        return invoiceRepository.findAll();
	    }

	    @Override
	    public Invoice saveInvoice(Invoice invoice) {
	        return invoiceRepository.save(invoice);
	    }

		@Override
		public Invoice getInvoiceById(Long id) {
			return invoiceRepository.findById(id).get();
		}

		@Override
		public Invoice updateInvoice(Invoice invoice) {
			return invoiceRepository.save(invoice);
		}

		@Override
		public void deleteInvoiceById(Long id) {
			invoiceRepository.deleteById(id);
			
		}

		
		
	   

}
