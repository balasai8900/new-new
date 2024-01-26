package com.pack.fabo.service.impl;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.AdminComments;
import com.pack.fabo.repository.AdminCommentsRepository;
import com.pack.fabo.service.AdminCommentService;
@Service
public class AdminCommentsServiceImpl implements AdminCommentService{
	
	private AdminCommentsRepository adminCommentsRepository;

	public AdminCommentsServiceImpl(AdminCommentsRepository adminCommentsRepository) {
		super();
		this.adminCommentsRepository = adminCommentsRepository;
	}

	@Override
	public AdminComments saveComment(AdminComments comment) {
		return adminCommentsRepository.save(comment);
	}

}
