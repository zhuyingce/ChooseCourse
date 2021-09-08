package com.wust.system.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Course {

  private long id;
  private String name;
  private Double credit;
  private String classHour;
  private String courseNature;
  private String examNature;

}
