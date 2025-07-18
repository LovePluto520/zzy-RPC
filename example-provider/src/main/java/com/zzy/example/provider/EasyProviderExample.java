package com.zzy.example.provider;

import com.zzy.example.common.service.UserService;
import com.zzy.zzyrpc.registry.LocalRegistry;
import com.zzy.zzyrpc.server.HttpServer;
import com.zzy.zzyrpc.server.VertxHttpServer;

/**
 * 简易服务提供者
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        //启动web服务
        HttpServer httpServer=new VertxHttpServer();
        httpServer.doStart(8080);

    }
}
