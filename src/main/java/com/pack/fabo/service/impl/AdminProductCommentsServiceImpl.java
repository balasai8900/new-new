package com.pack.fabo.service.impl;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.AdminProductComments;
import com.pack.fabo.repository.AdminProductCommentsRepository;
import com.pack.fabo.service.AdminProductCommentService;


@Service
public class AdminProductCommentsServiceImpl implements AdminProductCommentService{
	
	private AdminProductCommentsRepository adminProductCommentsRepository;

	public AdminProductCommentsServiceImpl(AdminProductCommentsRepository adminProductCommentsRepository) {
		super();
		this.adminProductCommentsRepository = adminProductCommentsRepository;
	}

	@Override
	public AdminProductComments saveComment(AdminProductComments comment) {
		return adminProductCommentsRepository.save(comment);
	}

}
