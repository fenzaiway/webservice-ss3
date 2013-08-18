package com.lutongnet.test.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactoryUtil
{

    private static final String RESOURCE = "Configuration.xml";
    private static SqlSessionFactory sqlSessionFactory = null;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

    static
    {
        Reader reader = null;
        try
        {
            reader = Resources.getResourceAsReader(RESOURCE);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Get resource error:" + RESOURCE, e);
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * Function : ���SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory()
    {
        return sqlSessionFactory;
    }

    /**
     * Function : ���´���SqlSessionFactory
     */
    public static void rebuildSqlSessionFactory()
    {
        Reader reader = null;
        try
        {
            reader = Resources.getResourceAsReader(RESOURCE);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Get resource error:" + RESOURCE, e);
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * Function : ��ȡsqlSession
     */
    public static SqlSession getSession()
    {
        SqlSession session = threadLocal.get();

        if (session != null)
        {
            if (sqlSessionFactory == null)
            {
                getSqlSessionFactory();
            }
            // ���sqlSessionFactory��Ϊ�����ȡsqlSession�����򷵻�null
            session = (sqlSessionFactory != null) ? sqlSessionFactory
                    .openSession() : null;
        }

        return session;
    }

    /**
     * Function : �ر�sqlSession
     */
    public static void closeSession()
    {
        SqlSession session = threadLocal.get();
        threadLocal.set(null);
        if (session != null)
        {
            session.close();
        }
    }
}
