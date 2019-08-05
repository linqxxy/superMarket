package com.linqxxy.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//源注解
@Target(ElementType.TYPE)
public @interface CommandMeta {
    String name();//DL
    String desc();//登录
    String group();//入口命令

}
