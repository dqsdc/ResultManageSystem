package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.ThesisMapper;
import com.qilinxx.rms.domain.model.Thesis;
import com.qilinxx.rms.domain.model.ThesisExample;
import com.qilinxx.rms.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    ThesisMapper thesisMapper;
    @Override
    public int findThesisByHostName(String host, String name) {
        ThesisExample example=new ThesisExample();
        example.createCriteria().andHostEqualTo(host).andNameEqualTo(name);
        return thesisMapper.selectCountByExample(example);
    }

    @Override
    public void createThesis(Thesis thesis) {
        thesisMapper.insert(thesis);
    }
}
