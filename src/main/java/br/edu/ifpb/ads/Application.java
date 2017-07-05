package br.edu.ifpb.ads;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.ads.job.SampleJob;

@SpringBootApplication
public class Application {
	
	
	
	@PostConstruct
	public void init() {
		try {
			
			JobDetail job = JobBuilder.newJob(SampleJob.class)
					.withIdentity("dummyJobName", "group1").build();
			
			job.getJobDataMap().put("id", 10l);
			
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("dummyTriggerName", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(5))
					.build();
            
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
