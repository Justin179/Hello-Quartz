import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.StdSchedulerFactory;



public class QuartzMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {

		try {
			
			System.out.println("ooxx");
			
			JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).build();
			//JobKey jobKey = jobDetail.getKey();
			
			
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
			

			// scheduler.scheduleJob(jobDetail, trigger);

			
//			// TriggerState triggerState = scheduler.getScheduler().getTriggerState(TriggerKey.triggerKey(triggerKey));
//			TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey("CronTrigger"));
//			System.out.println(triggerState);
//			
//			
//			
//			// job已在quartz內執行排程中NORMAL & job還在quartz內未刪除,但目前停止執行PAUSED，刪除排程
//			if(triggerState.equals(TriggerState.NORMAL)
//					|| triggerState.equals(TriggerState.PAUSED)){
//				
//				// 把job刪掉
//				scheduler.deleteJob(jobKey);
//
//			}else if(triggerState.equals(TriggerState.NONE)){
//				//已停止狀態,不做動作
//			}else if(triggerState.equals(TriggerState.ERROR)){
//				
//				
////				var trigerKey =newTriggerKey("trigerKey","trigerGroup");
////				if(scheduler.GetTriggerState(trigerKey)==TriggerState.Error){
////				    scheduler.ResumeTrigger(trigerKey);
////				}
//				
//				// resumeTrigger
//				scheduler.resumeTrigger(TriggerKey.triggerKey("CronTrigger"));
//				
//				// or delete job
//				
//				
//				// 在這邊把job刪掉
//				throw new Exception("排程狀態失敗，請洽系統人員。");
//			}else{
//				// BLOCKED or COMPLETE (job在排程中執行狀態(被鎖定無法重複執行)) & job已在quartz內單次執行中
//				throw new Exception("程式在「執行中(鎖定)」狀態下，無法被停止，請待執行完畢後，再試一次。");
//			}
			
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
