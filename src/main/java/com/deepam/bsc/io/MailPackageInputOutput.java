package com.deepam.bsc.io;

import java.io.IOException;
import java.util.Scanner;

import com.deepam.bsc.repository.MailPackage;
import com.deepam.bsc.services.PackageCollection;

public class MailPackageInputOutput {

	public static MailPackage getPackageInput(Scanner scanner) throws IOException {
		// Reading data using readLine
		String packageLine = scanner.nextLine();

		if (packageLine.equals("quit"))
			return null;
		else
			return MailPackageLineParser.parsePackageLine(packageLine);
	}

	public static void writePackageSummary() {
		for (MailPackage mailPackage : PackageCollection.INSTANCE.getPackagesSummary().values()) {
			System.out.println(mailPackage.toString());
		}
	}
}
