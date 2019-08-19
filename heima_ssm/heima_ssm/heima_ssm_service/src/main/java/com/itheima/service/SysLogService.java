package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {
    /**
     * 添加日志信息
     * @param sysLog
     */
    void add(SysLog sysLog);

    List<SysLog> findByPage(Integer pageNo,Integer rows);
}
