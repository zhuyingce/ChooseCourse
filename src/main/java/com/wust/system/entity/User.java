package com.wust.system.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

  private Integer id;
  @NotBlank(message = "用户名不能为空")
  private String username;
  @NotBlank(message = "密码不能为空")
  private String password;
  @NotBlank(message = "邮箱不能为空")
  private String email;
  @NotBlank(message = "身份不能为空")
  private String type;
  @NotBlank(message = "姓名不能为空")
  private String name;
  @NotBlank(message = "性别不能为空")
  private String sex;
  @NotBlank(message = "编号不能为空")
  private String number;
  @NotBlank(message = "民族不能为空")
  private String nation;
  private String grade;
  private String position;
  @NotNull(message = "学院不能为空")
  private Integer departmentId;
  private Integer majorId;
  private String departmentName;
  private String majorName;
}
