package com.deepam.bsc.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {
	private static final String TRIGGER_NAME = "PrintPackages";
	private static final String GROUP = "packages_Group";
	private static final String JOB_NAME = "packagesJob";
	private static Scheduler scheduler;

	public void createTimer() throws Exception {

		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();

		Trigger trigger = buildSimpleSchedulerTrigger();
		scheduleJob(trigger);
	}

	public static void shutdownTimer() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static void scheduleJob(Trigger trigger) throws Exception {
		JobDetail someJobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(JOB_NAME, GROUP).build();

		scheduler.scheduleJob(someJobDetail, trigger);

	}

	private static Trigger buildSimpleSchedulerTrigger() {
		int INTERVAL_SECONDS = 60;

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, GROUP)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL_SECONDS).repeatForever())
				.build();
		return trigger;
	}

}
