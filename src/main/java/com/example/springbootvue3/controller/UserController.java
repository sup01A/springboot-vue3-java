package com.example.springbootvue3.controller;

import com.example.springbootvue3.entity.Result;
import com.example.springbootvue3.entity.User;
import com.example.springbootvue3.info.MsgInfo;
import com.example.springbootvue3.service.impl.UserServiceImpl;
import com.example.springbootvue3.utils.JwtUtil;
import com.example.springbootvue3.utils.Md5Util;
import com.example.springbootvue3.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public <T>Result<T> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                                 @Pattern(regexp = "^\\S{5,16}$")  String password){
        User byUsername = userService.findByUsername(username);
        if (byUsername == null){
            if (userService.register(username,password)){
                return Result.success();
            }else {
                return Result.error(MsgInfo.Normal);
            }
        }else {
            return Result.error(MsgInfo.UserName_Occupied);
        }
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password){
        User byUsername = userService.findByUsername(username);
        if (byUsername == null){
            return Result.error(MsgInfo.Wrong_UserName);
        }
        String password1 = byUsername.getPassword();
        boolean b = Md5Util.checkPassword(password, password1);
        if (b){
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id",byUsername.getId());
            claims.put("username",byUsername.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }else {
            return Result.error(MsgInfo.Wrong_PassWord);
        }
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> o = ThreadLocalUtil.get();
        int id = (int)o.get("id");
        User byUserId = userService.findByUserId(id);
        return Result.success(byUserId);
    }
    @PutMapping("/update")
    public <T>Result<T> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    /*
    用户更新头像
     */
    @PatchMapping("/updateAvatar")
    public <T>Result<T>  updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    /*
    用户更新密码
     */
    @PatchMapping("/updatePwd")
    public <T>Result<T>  updatePwd(@RequestBody Map<String,String> params){
//        获取参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
//        验证参数，若其中一个没有值，返回参数不足错误
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error(MsgInfo.Args_Error);
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        int id = (int)map.get("id");
        User byUserId = userService.findByUserId(id);
//        旧密码和原来的密码对比
        boolean b = Md5Util.checkPassword(oldPwd, byUserId.getPassword());
//        不正确，返回密码错误
        if (!b){
            return Result.error(MsgInfo.Wrong_PassWord);
        }
//        如果重复密码错误，则返回重复密码错误
        if (!rePwd.equals(newPwd)){
            return Result.error(MsgInfo.WRONG_RePassWord);
        }
//        更新用户密码
        userService.updatePwd(newPwd,id);
        return Result.success();
    }

}
