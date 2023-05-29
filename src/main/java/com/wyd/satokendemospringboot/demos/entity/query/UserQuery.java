package com.wyd.satokendemospringboot.demos.entity.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserQuery implements Serializable {

    @NotEmpty(message = "用户名不能为空", groups = {loginQuery.class})
    private String userName;

    @Length(min = 6, message = "密码请最少输入6位", groups = {loginQuery.class})
    private String password;

    @Length(min = 11, max = 11, message = "手机号请输入11位", groups = {telephoneLoginQuery.class})
    private String telephone;

    @NotEmpty(message = "验证码不能为空", groups = {telephoneLoginQuery.class})
    private String code;

    public interface loginQuery{}

    public interface telephoneLoginQuery{}
}
