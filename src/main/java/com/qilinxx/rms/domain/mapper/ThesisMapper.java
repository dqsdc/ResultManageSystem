package com.qilinxx.rms.domain.mapper;


import com.qilinxx.rms.domain.model.Thesis;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ThesisMapper extends Mapper<Thesis>{
    Integer setDossierNull(@Param("tid") String tid);

    Integer setIssueNull(@Param("tid") String tid);
}