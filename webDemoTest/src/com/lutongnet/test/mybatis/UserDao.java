package com.lutongnet.test.mybatis;

import java.util.List;

public interface UserDao
{

    public User load(int id);

    public void add(User user);

    public void update(User user);

    public void delete(int id);

    public User findByName(String userName);

    public List<User> queryAllUser();

    public List<User> list(int pageNow, int pageSize);

    public int getAllCount();

}
