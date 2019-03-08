package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Project;

import java.util.List;

public interface ProjectService {
    /**通过 项目名称name、主持人host、醒目来源from 查重,返回查询重复个数*/
    int findProjectByNameHostFrom(String name,String host,String source);
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
}
