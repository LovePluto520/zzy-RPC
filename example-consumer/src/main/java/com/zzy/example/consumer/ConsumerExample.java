package com.zzy.example.consumer;

import com.zzy.example.common.model.User;
import com.zzy.example.common.service.UserService;
import com.zzy.zzyrpc.proxy.ServiceProxyFactory;


/**
 * 简易服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        //获取代理
        UserService userService= ServiceProxyFactory.getProxy(UserService.class);
        User user=new User();
        user.setName("zzy");
       // System.out.println(user.getName());
        User newUser=userService.getUser(user);
        if (newUser!=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user==null");
        }
        long number=userService.getNumber();
        System.out.println(number);
//        RpcConfig rpc= ConfigUtils.loadConfig(RpcConfig.class,"rpc");
//        System.out.println(rpc);
    }
}
