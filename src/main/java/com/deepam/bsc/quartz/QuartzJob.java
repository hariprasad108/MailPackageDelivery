package com.deepam.bsc.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deepam.bsc.io.MailPackageInputOutput;
import com.deepam.bsc.services.PackageCollection;

public class QuartzJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO HAP remove 2 obsolete lines
		// LocalDateTime localTime = LocalDateTime.now();
		// System.out.println("Run QuartzJob at " + localTime.toString());

		if (!PackageCollection.INSTANCE.getPackagesSummary().isEmpty()) {
			System.out.println();
			MailPackageInputOutput.writePackageSummary();
		}

	}

}