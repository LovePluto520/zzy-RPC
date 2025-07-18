package com.zzy.zzyrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zzy.zzyrpc.model.RpcRequest;
import com.zzy.zzyrpc.model.RpcResponse;
import com.zzy.zzyrpc.serializer.JdkSerializer;
import com.zzy.zzyrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用代理
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //指定序列化器
        Serializer serializer=new JdkSerializer();
        //构造请求
        RpcRequest rpcRequest=RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try{
            //序列化
            byte[] bobyBytes= serializer.serialize(rpcRequest);
            //发送请求
            try(HttpResponse httpResponse= HttpRequest.post("http://localhost:8080")
                    .body(bobyBytes)
                    .execute()){
                System.out.println("HTTP响应状态码：" + httpResponse.getStatus());
                byte[] result= httpResponse.bodyBytes();
                System.out.println("响应字节长度：" + (result == null ? 0 : result.length));
                //反序列化
                RpcResponse rpcResponse=serializer.deserialize(result,RpcResponse.class);
                return  rpcResponse.getData();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
