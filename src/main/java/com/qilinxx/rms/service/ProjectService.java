package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Project;

public interface ProjectService {
    /**通过 项目名称name、主持人host、醒目来源from 查重,返回查询重复个数*/
    int findProjectByNameHostFrom(String name,String host,String source);
    /**新建一个项目*/
    void createProject(Project project);
}
