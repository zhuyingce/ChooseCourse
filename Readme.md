/*
    token:
          1.退出登录
            第一次登录时将token存入数据库，后面登录直接修改token
            退出系统时，删除该行，拦截器中添加判断请求者携带的token存不存在，
            如果存在说明是正在使用系统，交给jwt判断token
            如果不存在说明是退出状态，抛出异常返回token无效

          2.多设备登录
            登录业务中判断该用户名在数据库中是否存在token，如果存在直接就用这个token


          3.多设备同时登陆要确保使用的是统一token
*/

登录
    请求
        username,password,type
        
    响应
        token,name,number
        
注册
    请求
        username,password,email,type,
        name,sex,number,nation,departmentId,majorId,grade
    
    响应
    
个人信息
    请求
    
 
添加课程
    name,classroom,className,week
    
    
    
我教的课 
    name,credit,classHour,classroom,className,week,quantity,删除,详细信息
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    