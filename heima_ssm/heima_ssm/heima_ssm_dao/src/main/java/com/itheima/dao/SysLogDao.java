package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    @Insert("insert into SYSLOG values(sys_guid(),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void add(SysLog sysLog);

    @Select("select * from syslog")
    List<SysLog> findAll();

}
