package com.pack.fabo.service.impl;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.InvoiceComments;
import com.pack.fabo.repository.InvoiceCommentsRepository;
import com.pack.fabo.service.InvoiceCommentsService;

@Service
public class InvoiceCommentsServiceImpl implements InvoiceCommentsService{
	
	private InvoiceCommentsRepository invoiceCommentsRepository;

	public InvoiceCommentsServiceImpl(InvoiceCommentsRepository invoiceCommentsRepository) {
		super();
		this.invoiceCommentsRepository = invoiceCommentsRepository;
	}

	@Override
	public InvoiceComments saveComment(InvoiceComments comment) {
		return invoiceCommentsRepository.save(comment);
	}

}
