package com.deepam.bsc.io;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.deepam.bsc.repository.MailPackage;

public class MailPackageLineParser {

	public static MailPackage parsePackageLine(String packageLine) throws IOException {
		MailPackage mailPackage = new MailPackage();

		String[] packageData = packageLine.split(" ");

		if (packageData.length != 2)
			throw new IOException("Line missing one delimiter.");

		Pattern digitPattern = Pattern.compile("^\\d{5}$");

		if (digitPattern.matcher(packageData[1]).find())
			mailPackage.setPostalCode(packageData[1]);
		else
			throw new IOException("Wrong number of digits in postal code.");

		Pattern floatPattern = Pattern.compile("^\\d+(.\\d{1,3})?$");

		if (floatPattern.matcher(packageData[0]).find())
			mailPackage.setWeightKg(new BigDecimal(packageData[0]));
		else
			throw new IOException("Wrong number format of weight.");

		return mailPackage;
	}

}
