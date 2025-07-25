package com.zzy.zzyrpc.config;

import com.zzy.zzyrpc.serializer.SerializerKeys;
import lombok.Data;

@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name="zzy-rpc";
    /**
     * 版本号
     */
    private String version="1.0";
    /**
     * 服务器主机名
     */
    private String serverHost="localhost";
    /**
     * 服务器端口号
     */
    private Integer serverPort=8080;
    /**
     * 模拟调用
     */
    private boolean mock=false;

    private String serializer= SerializerKeys.JSON;


}
