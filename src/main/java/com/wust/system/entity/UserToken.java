package com.wust.system.entity;

import lombok.Data;

@Data
public class UserToken {

  private Integer id;
  private String token;
  private Integer userId;

}
