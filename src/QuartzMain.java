import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzMain {

	/**
	 * @param args
	 * @throws SchedulerException 
	 */
	public static void main(String[] args) throws SchedulerException {

		JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).build();
		
		// simple trigger
		// Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		
		// cron trigger (will be fired every 1 minute)
		// Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
		// 		.withSchedule(CronScheduleBuilder.cronSchedule("	0 0/1 * 1/1 * ? *")).build();
		
		// cron trigger (每五秒執行一次)
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		
		
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
		
		
		

	}

}
