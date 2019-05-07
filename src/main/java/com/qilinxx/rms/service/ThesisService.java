package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Thesis;

import java.util.List;

public interface ThesisService {
    /**根据论文作者host、论文名称查重，返回重复个数*/
    int countThesisByHostName(String host,String name);
    /**根据论文作者host、论文名称查重，返回重复个数*/
    int countThesisByHostNameExceptTid(String host,String name,String tid);
    /**新建一个论文记录*/
    void createThesis(Thesis thesis);
    /**通过论文tid 删除论文记录*/
    void deleteThesisByTid(String tid);
    /**通过论文tid查找 论文记录*/
    Thesis findThesisByTid(String tid);
    /**更新论文字段*/
    void updateThesis(Thesis thesis);
    /**通过专业查询论文*/
    List<Thesis> findThesisByMid(Integer mid);
    /**通过查询mid 返回查询数量*/
    int countThesisByMid(Integer mid);
    /**通过查询mid 、和状态  返回查询数量*/
    int countThesisByMidState(Integer mid,String state);

    Integer setDossierNull(String tid);

    Integer setIssueNull(String tid);
}
