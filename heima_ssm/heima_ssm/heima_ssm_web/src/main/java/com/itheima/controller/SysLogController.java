package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findByPage")
    public String findByPage(Model model, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "rows",defaultValue = "10") Integer rows){

        List<SysLog> sysLogs = sysLogService.findByPage(pageNo, rows);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        model.addAttribute("pageInfo",pageInfo);
        return "syslog-list";
    }
}
