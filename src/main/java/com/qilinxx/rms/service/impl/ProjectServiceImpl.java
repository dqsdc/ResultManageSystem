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
    public int countProjectByNameHostFrom(String name, String host, String source) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andNameEqualTo(name).andHostEqualTo(host).andProjectSourceEqualTo(source);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByNameHostFromExceptPid(String name, String host, String source, String pid) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andNameEqualTo(name).andHostEqualTo(host).andProjectSourceEqualTo(source).andPidNotEqualTo(pid);
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
        example.setOrderByClause("state ASC ,create_time DESC");
        return projectMapper.selectByExample(example);
    }

    @Override
    public int countProjectByMid(Integer mid) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andMidEqualTo(mid);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByMidState(Integer mid, String state) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andMidEqualTo(mid).andStateEqualTo(state);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByTopic(String topic) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andTopicEqualTo(topic);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByTopicExceptPid(String topic, String pid) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andTopicEqualTo(topic).andPidNotEqualTo(pid);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByTopicHostSettime(String topic, String host, Long setTime) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andTopicEqualTo(topic).andHostEqualTo(host).andSetTimeEqualTo(setTime);
        return projectMapper.selectCountByExample(example);
    }

    @Override
    public int countProjectByTopicHostSettimeExceptPid(String topic, String host, Long setTime, String pid) {
        ProjectExample example=new ProjectExample();
        example.createCriteria().andTopicEqualTo(topic).andHostEqualTo(host).andSetTimeEqualTo(setTime).andPidNotEqualTo(pid);
        return projectMapper.selectCountByExample(example);
    }

}
