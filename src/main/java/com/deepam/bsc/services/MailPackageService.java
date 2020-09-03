package com.deepam.bsc.services;

import com.deepam.bsc.repository.MailPackage;

public class MailPackageService {

	public void insertMailPackage(MailPackage mailPackage) {
		PackageCollection.INSTANCE.addPackage(mailPackage);
		PackageCollection.INSTANCE.addToPackagesSummary(mailPackage.getPostalCode(), mailPackage);
	}
}
