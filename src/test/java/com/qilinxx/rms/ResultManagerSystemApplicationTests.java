package com.qilinxx.rms;

import com.qilinxx.rms.service.DocumentService;
import com.qilinxx.rms.service.ProjectService;
import com.qilinxx.rms.service.RewardService;
import com.qilinxx.rms.service.ThesisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultManagerSystemApplicationTests {
	@Autowired
	ProjectService projectService;
	@Autowired
	RewardService rewardService;
	@Autowired
	DocumentService documentService;
	@Autowired
	ThesisService thesisService;
	@Test
	public void testProjectService() {
		//int projectByNameHostFrom = projectService.findProjectByNameHostFrom("", "", "");
		//System.out.println("重复个数:"+projectByNameHostFrom);
		int rewardNum = rewardService.findRewardByNamePeopleGetTime("", "", 123);
		System.out.println("重复个数："+rewardNum);
	}
	@Test
	public void testDocumentService(){
		thesisService.deleteThesisByTid("dfdf359244524c9284c958b2e282a927");

	}

}
