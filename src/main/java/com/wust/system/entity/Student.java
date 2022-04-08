package com.wust.system.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class Student {

  @NotNull(message = "学生id不为空")
  private Integer id;
  private String name;
  private String sex;
  private String number;
  private String nation;
  private Integer departmentId;
  private String department;    //学院名
  private String grade;
  private Integer majorId;
  private String major;         //专业名
  @PositiveOrZero(message = "分数大于等于0")
  @Max(value = 100,message = "分数小于等于100")
  private Double score;         //分数
  @PositiveOrZero(message = "绩点大于等于0")
  @Max(value = 4,message = "绩点小于等于4")
  private Double gpa;           //绩点
  private String courseName;    //课程名称
  private Integer userId;
  @NotNull(message = "课程id不为空")
  private Integer courseId;

}
