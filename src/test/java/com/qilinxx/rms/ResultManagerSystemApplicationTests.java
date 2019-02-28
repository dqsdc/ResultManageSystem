package com.qilinxx.rms;

import com.qilinxx.rms.service.ProjectService;
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
	@Test
	public void testProjectService() {
		int projectByNameHostFrom = projectService.findProjectByNameHostFrom("", "", "");
		System.out.println("重复个数:"+projectByNameHostFrom);
	}

}
