package com.deepam.bsc.services;

import java.util.Collections;
import java.util.HashMap;
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

	private Map<String, MailPackage> packagesSummary = Collections.synchronizedMap(new HashMap<>());

	public Map<String, MailPackage> getPackagesSummary() {
		return packagesSummary;
	}

	public void addToPackagesSummary(String postalCode, MailPackage mailPackage) {
		synchronized (packagesSummary) {
			if (packagesSummary.containsKey(postalCode)) {
				MailPackage actMailPackage = packagesSummary.get(postalCode);
				MailPackage sumMailPackage = new MailPackage(postalCode,
						actMailPackage.getWeightKg().add(mailPackage.getWeightKg()));
				packagesSummary.put(postalCode, sumMailPackage);
			} else
				packagesSummary.put(postalCode, mailPackage);
		}
	}

}
