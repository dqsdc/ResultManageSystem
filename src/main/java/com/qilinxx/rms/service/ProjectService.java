package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Project;

import java.util.List;

public interface ProjectService {
    /**通过 项目名称name、主持人host、醒目来源from 查重,返回查询重复个数*/
    int countProjectByNameHostFrom(String name,String host,String source);
    /**通过 项目名称name、主持人host、醒目来源from 查重,返回查询重复个数     除了指定的pid*/
    int countProjectByNameHostFromExceptPid(String name,String host,String source,String pid);
    /**新建一个项目*/
    void createProject(Project project);
    /**通过项目pid查询项目*/
    Project findProjectByPid(String pid);
    /**通过项目pid 删除项目*/
    void deleteProjectByPid(String pid);
    /**更新项目字段记录*/
    void updateProject(Project project);
    /**通过 专业mid  与审核状态state 查找项目记录*/
    List<Project>   findProjectByMid(Integer mid);
    /**通过 专业id 返回查询到的数量*/
    int countProjectByMid(Integer mid);
    /**通过 专业id、状态  返回查询到的数量*/
    int countProjectByMidState(Integer mid,String state);
    /**通过查询项目题目topic，返回重复个数*/
    int countProjectByTopic(String topic);
    /**通过查询项目题目topic，返回重复个数     除了指定的pid*/
    int countProjectByTopicExceptPid(String topic,String pid);

    int countProjectByTopicHostSettime(String topic,String host,Long setTime);

    int countProjectByTopicHostSettimeExceptPid(String topic,String host,Long setTime,String pid);
}
