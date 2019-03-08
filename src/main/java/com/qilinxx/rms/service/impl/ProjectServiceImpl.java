package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.ProjectMapper;
import com.qilinxx.rms.domain.model.Project;
import com.qilinxx.rms.domain.model.ProjectExample;
import com.qilinxx.rms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public void deleteProjectByPid(String pid) {
        projectMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> findProjectByMid(Integer mid) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andMidEqualTo(mid);
        example.setOrderByClause("'state' ASC,'create_time' DESC");
        return projectMapper.selectByExample(example);
    }

}
