package com.qilinxx.rms.domain.mapper;


import com.qilinxx.rms.domain.model.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NoticeMapper extends Mapper<Notice> {

   int passNoticeByNid(@Param("result_id") String resultId, @Param("type") String type);

   Notice selectLastNotice(@Param("result_id") String resultId, @Param("type") String type);

   Notice latestNotice(@Param("create_id") String createId, @Param("type") String type);

   List<Notice> getAllNoticesOrderByTime(@Param("create_id") String createId, @Param("type") String type);
}