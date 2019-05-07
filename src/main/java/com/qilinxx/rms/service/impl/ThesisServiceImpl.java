package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.ThesisMapper;
import com.qilinxx.rms.domain.model.Thesis;
import com.qilinxx.rms.domain.model.ThesisExample;
import com.qilinxx.rms.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    ThesisMapper thesisMapper;
    @Override
    public int countThesisByHostName(String host, String name) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andHostEqualTo(host).andNameEqualTo(name);
        return thesisMapper.selectCountByExample(example);
    }

    @Override
    public int countThesisByHostNameExceptTid(String host, String name, String tid) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andHostEqualTo(host).andNameEqualTo(name).andTidNotEqualTo(tid);
        return thesisMapper.selectCountByExample(example);
    }

    @Override
    public void createThesis(Thesis thesis) {
        thesisMapper.insert(thesis);
    }

    @Override
    public void deleteThesisByTid(String tid) {
        thesisMapper.deleteByPrimaryKey(tid);
    }

    @Override
    public Thesis findThesisByTid(String tid) {
        return thesisMapper.selectByPrimaryKey(tid);
    }

    @Override
    public void updateThesis(Thesis thesis) {
        thesisMapper.updateByPrimaryKeySelective(thesis);
    }

    @Override
    public List<Thesis> findThesisByMid(Integer mid) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andMidEqualTo(mid);
        example.setOrderByClause("state ASC ,create_time DESC");
        return thesisMapper.selectByExample(example);
    }

    @Override
    public int countThesisByMid(Integer mid) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andMidEqualTo(mid);
        return thesisMapper.selectCountByExample(example);
    }

    @Override
    public int countThesisByMidState(Integer mid, String state) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andMidEqualTo(mid).andStateEqualTo(state);
        return thesisMapper.selectCountByExample(example);
    }

    @Override
    public Integer setDossierNull(String tid) {
         return    thesisMapper.setDossierNull(tid);
    }

    @Override
    public Integer setIssueNull(String tid) {
        return thesisMapper.setIssueNull(tid);
    }
}
