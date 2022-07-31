package com.atguigu.Excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class User {
    //设置表头名称
    @ExcelProperty(value = "编号",index = 0)
    private int id;
    //设置表头名称
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
}
