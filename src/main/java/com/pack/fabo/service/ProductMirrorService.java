package com.pack.fabo.service;

import com.pack.fabo.entity.AddProductAdmin;
import com.pack.fabo.entity.ClientProduct;


public interface ProductMirrorService {

	void addProductRecordToBothSides(AddProductAdmin addProductAdmin);
	
	void addProductRecordToBothSide(ClientProduct clientProduct);

}
