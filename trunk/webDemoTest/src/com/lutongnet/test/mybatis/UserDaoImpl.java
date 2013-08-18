package com.lutongnet.test.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements UserDao
{

    public User load(int id)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        User user = (User) session.selectOne("selectById_user", id);
        session.close();
        return user;
    }

    public void add(User user)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        session.insert("inser_user", user);
        session.commit();
        session.close();
    }

    public void update(User user)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        session.update("update_user", user);
        session.commit();
        session.close();
    }

    public void delete(int id)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        session.delete("delete_user", id);
        session.close();
    }

    public User findByName(String userName)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        User user = (User) session.selectOne("selectByName_user", userName);
        session.close();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> queryAllUser()
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        List<User> list = session.selectList("selectAll_user");
        session.close();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<User> list(int pageNow, int pageSize)
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageNow", pageNow);
        params.put("pageSize", pageSize);
        List<User> list = session.selectList("selectList_user", params);
        session.close();
        return list;
    }

    public int getAllCount()
    {
        SqlSession session = SessionFactoryUtil.getSqlSessionFactory()
                .openSession();
        int count = (Integer) session.selectOne("selectCount_user");
        session.close();
        return count;
    }

}
