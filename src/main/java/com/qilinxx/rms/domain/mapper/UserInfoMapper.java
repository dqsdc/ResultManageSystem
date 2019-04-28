package com.qilinxx.rms.domain.mapper;

import com.qilinxx.rms.domain.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo>{
    List<UserInfo> findUserByMid(@Param("mid") int mid);
}