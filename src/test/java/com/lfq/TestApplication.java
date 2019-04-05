package com.lfq;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 预备知识：动态代理
 *
 * @中文注释 吕一明
 * @公众号 码客在线
 */
public class TestApplication {

    @Test
    public void main() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-cfg.xml");

        /**
         * 关于builder模式：对于复杂对象构建，逻辑都在构造方法中构建对象不友好，应一步步有秩序创建，降低复杂度。
         * Builder模式本质上是一种特殊的工厂模式,按照流水线方式调用,然后最后检查产品是否合格,流水线之间可以任意组合,达到了高度的灵活性.
         * 可参考这篇文章介绍 （深入理解Builder模式https://www.jianshu.com/p/50fed6d2b257）
         *
         *
         * 构建分为两步：
         * 1、通过{@link XMLConfigBuilder}校验、解析xml文件，获取数据构建{@link Configuration}类。（包括mybatis-cfg.xml和*Mapper.xml）
         * 2、使用{@link Configuration}对象创建{@link SqlSessionFactory}。默认实现是{@link DefaultSqlSessionFactory}
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        /**
         * 代表和数据库的一次会话，向用户提供了操作数据库的方法。
         * 但和数据库操作有关的职责都会委托给Executor
         *
         * 默认实现 {@link org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSession()}
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /**
         *
         * 默认实现 {@link DefaultSqlSession#getMapper(java.lang.Class)}
         */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(1L);

        System.out.println(user.toString());

    }

}
