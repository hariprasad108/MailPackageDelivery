package com.deepam.bsc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.deepam.bsc.io.MailPackageInputOutput;
import com.deepam.bsc.io.MailPackageLineParser;
import com.deepam.bsc.quartz.QuartzScheduler;
import com.deepam.bsc.repository.MailPackage;
import com.deepam.bsc.services.MailPackageService;

/**
 * 
 * @author hariprasad
 * 
 *         Main class for Package application
 *
 */
public class MailPackageApplication {

	private MailPackageService mailService;

	// Reading data from file
	public void readFile(String fileName) throws IOException {

		// check filename
		String fileType = "";
		if (fileName != null && fileName.lastIndexOf(".") != -1)
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!fileType.equalsIgnoreCase("txt"))
			throw new IOException("File name has not valid name. Expected form is <path><name>.txt: " + fileName);

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String packageLine;
			while ((packageLine = reader.readLine()) != null) {
				MailPackage mailPackage = MailPackageLineParser.parsePackageLine(packageLine);
				mailService.insertMailPackage(mailPackage);
			}
		} catch (Exception e) {
			throw new IOException("Exception occurred trying to read " + fileName);
		}
	}

	// read data from input
	private void readInput() {
		System.out.println(
				"<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>\n");

		try {
			while (true) {
				Scanner scanner = new Scanner(System.in);
				MailPackage mailPackage = MailPackageInputOutput.getPackageInput(scanner);
				if (mailPackage == null) {
					return;
				} else {
					mailService.insertMailPackage(mailPackage);
				}
			}
		} catch (IOException e) {
			System.out.println("Input line format:\n"
					+ "<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {

		String fileName = null;

		MailPackageApplication application = new MailPackageApplication();
		application.mailService = new MailPackageService();

		if (args.length > 0) {
			fileName = args[0];
			// System.out.println("File path: " + fileName);
			try {
				application.readFile(fileName);
			} catch (IOException e) {
				System.out.println("Error either in file name or in format of lines inside. Line format:\n"
						+ "<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>");
				e.printStackTrace();
				System.exit(1);
			}
		}

		QuartzScheduler scheduler = new QuartzScheduler();

		try {
			scheduler.createTimer();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		application.readInput();

		QuartzScheduler.shutdownTimer();

	}
}
