package com.qilinxx.rms.domain.mapper;

import com.qilinxx.rms.domain.model.UserMajor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMajorMapper extends Mapper<UserMajor>{
    List<UserMajor> selectAllByUid(@Param("uid") String uid);
    int deleteByIdAndPower(@Param("uid") String uid,@Param("power") String power);
}