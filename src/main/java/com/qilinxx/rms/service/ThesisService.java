package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Thesis;

public interface ThesisService {
    /**根据论文作者host、论文名称查重，返回重复个数*/
    int findThesisByHostName(String host,String name);
    /**新建一个论文记录*/
    void createThesis(Thesis thesis);
    /**通过论文tid 删除论文记录*/
    void deleteThesisByTid(String tid);
    /**通过论文tid查找 论文记录*/
    Thesis findThesisByTid(String tid);
    /**更新论文字段*/
    void updateThesis(Thesis thesis);
}
