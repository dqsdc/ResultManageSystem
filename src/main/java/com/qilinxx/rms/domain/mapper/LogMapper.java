package com.qilinxx.rms.domain.mapper;

import com.qilinxx.rms.domain.model.Log;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LogMapper extends Mapper<Log> {
    List<Log> selectAdminLogin();
    Log getLastAdminLog();
}