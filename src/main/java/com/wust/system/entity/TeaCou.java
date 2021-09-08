package com.wust.system.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeaCou {

  private Integer id;
  private Integer teacherId;
  private Integer courseId;
  @NotBlank(message = "课程名称不能为空")
  private String name;
  private Double credit;
  private String classHour;
  private String courseNature;
  private String examNature;
  @NotBlank(message = "教室不能为空")
  private String classroom;   //教室
  @NotBlank(message = "教学班不能为空")
  private String className;   //教学班
  @NotBlank(message = "上课周次不能为空")
  private String week;      //上课周次
  private String teacher;
  private Integer stuNum;   //学生人数
}
