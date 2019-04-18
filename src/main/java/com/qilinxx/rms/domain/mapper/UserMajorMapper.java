package com.qilinxx.rms.domain.mapper;

import com.qilinxx.rms.domain.model.UserMajor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMajorMapper extends Mapper<UserMajor>{
    List<UserMajor> selectAllByUid(@Param("uid") int uid);
    int deleteByIdAndPower(@Param("uid") int uid,@Param("power") String power);
}