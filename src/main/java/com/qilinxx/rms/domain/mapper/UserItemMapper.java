package com.qilinxx.rms.domain.mapper;


import com.qilinxx.rms.domain.model.UserItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserItemMapper extends Mapper<UserItem> {
    List<UserItem> selectAllByItemType(@Param("type") String type);
}