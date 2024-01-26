package com.pack.fabo.service;

import com.pack.fabo.entity.AddSupportAdmin;
import com.pack.fabo.entity.ClientSupport;

public interface SupportMirrorService {

	void addSupportRecordToBothSides(AddSupportAdmin addSupportAdmin);
	
	void addSupportRecordToBothSide(ClientSupport clientSupport);

}
