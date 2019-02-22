package com.qilinxx.rms.domain.mapper;


import com.qilinxx.rms.domain.model.Project;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ProjectMapper extends Mapper<Project> {
}