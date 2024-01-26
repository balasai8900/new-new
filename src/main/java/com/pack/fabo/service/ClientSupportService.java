package com.pack.fabo.service;

import com.pack.fabo.entity.ClientSupport;

public interface ClientSupportService {

	ClientSupport saveClientSupport(ClientSupport clientSupport);

	ClientSupport getClientSupportById(Long id);

	ClientSupport updateAdmin(ClientSupport existingClientSupport);

	void saveComment(Long id, String comment);

	void saveCloseComment(Long id, String closeComment, String close);

	void deleteClientSuppportById(Long id);






} 
