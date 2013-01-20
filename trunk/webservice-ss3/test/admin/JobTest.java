package admin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.way.blog.manager.admin.entity.Job;
import com.way.blog.manager.admin.service.impl.JobServiceImpl;
import com.way.blog.util.MyFormatDate;

import base.BaseTest;

public class JobTest extends BaseTest {
	
	private JobServiceImpl jobService;
	
	private String[] jobs = new String[]{"学生","政府机关/干部","邮电通信","计算机","网络","商业/贸易","银行/金融/证券/保险/投资","税务","咨询","社会服务",
			"旅游/饭店","健康/医疗服务","房地产","交通运输","法律/司法","文化/娱乐/体育","媒介/广告","科研/教育","农业/渔业/林业/畜牧业","矿业/制作业","自由职业","其它"};
	
	public void init(){
		jobService = (JobServiceImpl) this.context.getBean("jobServiceImpl");
	}
	
	@Test
	public void addJob(){
		this.init();
		List<Job> jobList = new ArrayList<Job>();
		Job job = null;
		for(int i=0; i<jobs.length; i++){
			job = new Job();
			job.setCreateTime(MyFormatDate.getNowDate());
			job.setJobName(jobs[i]);
			job.setUsername("fenzaiway");
			job.setJobType("系统默认");
			jobList.add(job);
		}
		jobService.saveAll(jobList);
	}
	
	@Test
	public void load(){
		this.init();
		for(Job job : jobService.loadAll()){
			System.out.print(job.getJobName()+"\t");
		}
	}
}
