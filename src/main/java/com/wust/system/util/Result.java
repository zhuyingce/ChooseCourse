package com.wust.system.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * ~program: ChooseCourse
 * ~description: 返回结果
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(String message, Object data) {

        return new Result(200, message, data);
    }

    public static Result success(String message) {

        return new Result(200, message, null);
    }

    public static Result error(String message,Object data) {

        return new Result(500, message, data);
    }

    public static Result error(String message) {

        return new Result(500, message, null);
    }
}
