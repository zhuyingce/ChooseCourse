package com.wust.system.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class StuCou {

  private long id;
  private String name;  //课程名
  private Double credit;
  private String classHour;
  private String courseNature;
  private String examNature;
  private String classroom;   //教室
  private String week;      //上课周次
  private String teacher;   //教师姓名
  private Integer teacherId;
  private Double score;
  private Double gpa;
  private Integer studentId;
  private Integer courseId;

}
