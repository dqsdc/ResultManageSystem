package com.qilinxx.rms.service;

import com.qilinxx.rms.domain.model.Major;

import java.util.List;

public interface MajorService {
    /**通过id查询专业*/
    Major findMajorBymid(Integer mid);
    /**获得所有专业*/
    List<Major> findAllMajor();

    int addMajor(Major major);

    int deleteMajor(int mid);

    int updateMajor(Major major);
}
