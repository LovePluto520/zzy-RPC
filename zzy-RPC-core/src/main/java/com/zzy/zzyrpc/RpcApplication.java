package com.zzy.zzyrpc;

import com.zzy.zzyrpc.config.RpcConfig;
import com.zzy.zzyrpc.constant.RpcConstant;
import com.zzy.zzyrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig){
        rpcConfig=newRpcConfig;
       // log.info("rpc init,config={}",newRpcConfig.toString());

    }

    public static void init(){
        RpcConfig newRpcConfig;
        try{
            newRpcConfig= ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        }catch (Exception e){
            //配置加载失败，使用默认值
            newRpcConfig=new RpcConfig();

        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     * @return
     */
    public static RpcConfig getRpcConfig(){
        if (rpcConfig==null){
            synchronized (RpcApplication.class){
                if (rpcConfig==null){
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
