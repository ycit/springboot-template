package com.vastio.basic.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.vastio.basic.entity.response.LogResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogCoreMapper {
    List<LogResponse> selectLogResponses(Pagination page,
                                         @Param("orgId") Integer orgId,
                                         @Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime,
                                         @Param("optionalResult") String optionalResult,
                                         @Param("method") String method);
}
