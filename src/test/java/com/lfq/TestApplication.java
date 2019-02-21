package com.lfq;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 预备知识：动态代理
 */
public class TestApplication {

    @Test
    public void main() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-cfg.xml");

        /**
         * 关于builder模式：对于复杂对象构建，逻辑都在构造方法中构建对象不友好，应一步步有秩序创建，降低复杂度。
         * 可参考这篇文章介绍 （深入理解Builder模式https://www.jianshu.com/p/50fed6d2b257）
         *
         *
         * 构建分为两步：
         * 1、通过{@link XMLConfigBuilder}校验、解析xml文件，获取数据构建{@link Configuration}类。（包括mybatis-cfg.xml和*Mapper.xml）
         * 2、使用{@link Configuration}对象创建{@link SqlSessionFactory}。默认实现是{@link DefaultSqlSessionFactory}
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(1L);

        System.out.println(user.toString());

    }

}
