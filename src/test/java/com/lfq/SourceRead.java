package com.lfq;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Properties;

/**
 * mybatis源码解析
 *
 * 欢迎关注公众号：码客在线
 *
 * @author 吕一明
 * @version 0.0.1
 * @since 2019-02-18
 */
public class SourceRead {

    /**
     * @see SqlSessionFactoryBuilder#build(InputStream, String, Properties)
     * 1、调用SqlSessionFactoryBuilder来构建SqlSessionFactory对象
     * 1.1、
     *
     * @see XMLConfigBuilder
     */
    public void xmlParser() {}

}
