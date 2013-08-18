package com.lutongnet.test.mybatis;

import java.util.List;

import org.junit.Test;

public class UserDaoTest
{

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testLoad()
    {
        User u = userDao.load(1);
        if (u != null)
        {
            System.out.println("UserId:" + u.getId() + "  UserName:"
                    + u.getUserName() + "  Password:" + u.getPassword());
        }
        else
        {
            System.out.println("id不存在！！");
        }
    }

    @Test
    public void testAdd()
    {
        User user = new User();
        user.setUserName("admin5");
        user.setPassword("123456");
        userDao.add(user);
    }

    @Test
    public void testUpdate()
    {
        User user = new User();
        user.setId(2);
        user.setUserName("manager");
        user.setPassword("123456");
        userDao.update(user);
    }

    @Test
    public void testQueryAllUser()
    {
        List<User> list = userDao.queryAllUser();
        if (list != null & list.size() > 0)
        {
            for (User u : list)
            {
                System.out.println("UserId:" + u.getId() + "  UserName:"
                        + u.getUserName() + "  Password:" + u.getPassword());
            }
        }
    }

    @Test
    public void testFindByName()
    {
        User u = userDao.findByName("admin");
        if (u != null)
        {
            System.out.println("UserId:" + u.getId() + "  UserName:"
                    + u.getUserName() + "  Password:" + u.getPassword());
        }
        else
        {
            System.out.println("用户名不存在！！");
        }
    }

    @Test
    public void testList()
    {
        List<User> list = userDao.list(1, 4);
        if (list != null & list.size() > 0)
        {
            for (User u : list)
            {
                System.out.println("UserId:" + u.getId() + "  UserName:"
                        + u.getUserName() + "  Password:" + u.getPassword());
            }
        }
    }

    @Test
    public void testGetAllCount()
    {
        System.out.println("All Count : " + userDao.getAllCount());
    }

    @Test
    public void testDelete()
    {
        userDao.delete(3);
    }

}
