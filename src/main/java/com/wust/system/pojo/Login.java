package com.wust.system.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@Data
public class Login {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "身份不能为空")
    private String type;
}
