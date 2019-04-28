package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.MajorMapper;
import com.qilinxx.rms.domain.model.Major;
import com.qilinxx.rms.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    MajorMapper majorMapper;

    @Override
    public Major findMajorBymid(Integer mid) {
        return majorMapper.selectByPrimaryKey(mid);
    }

    @Override
    public List<Major> findAllMajor() {
        return majorMapper.findAllMajorBySort();
    }

    @Override
    public int addMajor(Major major) {
        return majorMapper.insert(major);
    }

    @Override
    public int deleteMajor(int mid) {
        return majorMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int updateMajor(Major major) {
        return majorMapper.updateByPrimaryKeySelective(major);
    }
}
