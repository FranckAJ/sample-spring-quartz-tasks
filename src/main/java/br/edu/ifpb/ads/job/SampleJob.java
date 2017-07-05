package br.edu.ifpb.ads.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleJob implements org.quartz.Job{
	
	Logger logger = LoggerFactory.getLogger(SampleJob.class);
	
	public SampleJob() {

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		Long id = dataMap.getLong("id");
		logger.info("Job running..." + id);
	}
}
