package com.pack.fabo.service;


import com.pack.fabo.entity.AddSupportAdmin;


public interface AddSupportAdminService {

	AddSupportAdmin saveAddSupportAdmin(AddSupportAdmin addSupportAdmin);

	AddSupportAdmin getAddSupportAdminById(Long id);

	void saveCommentAndStatusById(Long id, String commentText, String clientVisible, String requestStatus);

	void deleteAddSupportAdminById(Long id);

}
