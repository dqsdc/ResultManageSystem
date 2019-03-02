package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.ProjectMapper;
import com.qilinxx.rms.domain.model.Project;
import com.qilinxx.rms.domain.model.ProjectExample;
import com.qilinxx.rms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    @Override
    public int findProjectByNameHostFrom(String name, String host, String source) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andNameEqualTo(name).andHostEqualTo(host).andProjectSourceEqualTo(source);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public void createProject(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public Project findProjectByPid(String pid) {
        return projectMapper.selectByPrimaryKey(pid);
    }

}
