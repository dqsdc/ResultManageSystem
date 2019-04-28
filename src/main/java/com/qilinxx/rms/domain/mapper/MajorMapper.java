package com.qilinxx.rms.domain.mapper;

import com.qilinxx.rms.domain.model.Major;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MajorMapper extends Mapper<Major>{
    List<Major> findAllMajorBySort();
}