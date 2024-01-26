package com.pack.fabo.service;


import com.pack.fabo.entity.AddProductAdmin;



public interface AddProductAdminService {

	AddProductAdmin saveAddProductAdmin(AddProductAdmin addProductAdmin);

	AddProductAdmin getAddProductAdminById(Long id);

	void saveCommentAndStatusById(Long id, String commentText, String clientVisible, String requestStatus);

	void deleteAddProductAdminById(Long id);

}
 