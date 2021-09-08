import com.wust.system.util.Result;

import java.util.HashMap;
import java.util.Map;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-14
 */
public class TestMain {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9." +
                "eyJzdWIiOiIxIiwiaWF0IjoxNjI5MDgxMjAyLCJleHAiOjE2MjkxNjc2MDJ9." +
                "wIXnUM37jxRp1qiAJKtZBzamOSwGzplKjj1IZKmio5M3GjZyyr_qkX02xtu7BloCwUHksWlN9pNNEgQSRtOLmQ";

        String temp = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9." +
                "eyJzdWIiOiIxIiwiaWF0IjoxNjI5MDgxMjAyLCJleHAiOjE2MjkxNjc2MDJ9." +
                "wIXnUM37jxRp1qiAJKtZBzamOSwGzplKjj1IZKmio5M3GjZyyr_qkX02xtu7BloCwUHksWlN9pNNEgQSRtOLmQ";

        System.out.println(token.equals(temp));
    }

}
