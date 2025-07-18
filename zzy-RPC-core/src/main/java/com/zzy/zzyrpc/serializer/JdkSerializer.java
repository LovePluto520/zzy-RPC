package com.zzy.zzyrpc.serializer;

import java.io.*;

public class JdkSerializer implements Serializer{
    /**
     * 序列化
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        // 创建内存输出流，用于临时存储序列化后的字节数据
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        // 创建对象输出流，将Java对象转换为字节流格式
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
        // 执行序列化：将对象转换为字节流并写入输出流
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        // 创建内存输入流，读取字节数组数据
        ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
        // 创建对象输入流，用于将字节流转换为Java对象
        ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
        try{
            // 执行反序列化：读取对象并强制类型转换
            return (T) objectInputStream.readObject();
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }finally {
            objectInputStream.close();
        }

    }
}
