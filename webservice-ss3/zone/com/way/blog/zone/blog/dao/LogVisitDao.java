package com.way.blog.zone.blog.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.zone.entity.LogVisit;

@Repository("logVisitDao")
@Transactional
public class LogVisitDao extends HibernateGenericDao<LogVisit, Integer> {

}
