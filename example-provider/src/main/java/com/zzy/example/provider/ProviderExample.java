package com.zzy.example.provider;

import com.zzy.example.common.service.UserService;
import com.zzy.zzyrpc.RpcApplication;
import com.zzy.zzyrpc.registry.LocalRegistry;
import com.zzy.zzyrpc.server.HttpServer;
import com.zzy.zzyrpc.server.VertxHttpServer;

public class ProviderExample {
    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();
        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        //启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
