package com.way.blog.base.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.entity.Message;

@Repository("messageDao")
@Transactional
public class MessageDao extends HibernateGenericDao<Message, Integer> {

}
