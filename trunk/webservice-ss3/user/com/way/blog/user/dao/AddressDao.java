package com.way.blog.user.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.user.entity.Address;

@Repository("addressDao")
@Transactional
public class AddressDao extends HibernateGenericDao<Address, Integer> {

}
