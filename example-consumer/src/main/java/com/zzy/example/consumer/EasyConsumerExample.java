package com.zzy.example.consumer;

import com.zzy.example.common.model.User;
import com.zzy.example.common.service.UserService;
import com.zzy.zzyrpc.proxy.ServiceProxyFactory;

/**
 * 简易消费者
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        //todo需要获取UserService的实现类对象
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("zzy");
        //调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
