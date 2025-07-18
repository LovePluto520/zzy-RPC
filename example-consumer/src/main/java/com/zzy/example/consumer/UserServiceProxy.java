package com.zzy.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zzy.example.common.model.User;
import com.zzy.example.common.service.UserService;
import com.zzy.zzyrpc.model.RpcRequest;
import com.zzy.zzyrpc.model.RpcResponse;
import com.zzy.zzyrpc.serializer.JdkSerializer;
import com.zzy.zzyrpc.serializer.Serializer;

import java.io.IOException;

public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        //指定序列化器
        Serializer serializer=new JdkSerializer();

        //发请求
        RpcRequest rpcRequest=RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try{
            byte[] bobyBytes= serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse= HttpRequest.post("http://localhost:8080")
                    .body(bobyBytes)
                    .execute()){
                result= httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse=serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
