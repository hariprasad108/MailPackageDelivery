package com.deepam.bsc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepam.bsc.repository.MailPackage;

/**
 * Thread safe singleton for keeping data about packages in memory
 * 
 * @author hariprasad
 *
 */
public enum PackageCollection {

	INSTANCE;

	private List<MailPackage> packages = new ArrayList<>();

	private Map<String, MailPackage> packagesSummary = new HashMap<>();

	public List<MailPackage> getPackages() {
		return packages;
	}

	public void addPackage(MailPackage mailPackage) {
		packages.add(mailPackage);
	}

	public Map<String, MailPackage> getPackagesSummary() {
		return packagesSummary;
	}

	public void addToPackagesSummary(String postalCode, MailPackage mailPackage) {
		if (packagesSummary.containsKey(postalCode)) {
			MailPackage actMailPackage = packagesSummary.get(postalCode);
			MailPackage sumMailPackage = new MailPackage(postalCode,
					actMailPackage.getWeightKg().add(mailPackage.getWeightKg()));
			packagesSummary.put(postalCode, sumMailPackage);
		} else
			packagesSummary.put(postalCode, mailPackage);
	}

}
