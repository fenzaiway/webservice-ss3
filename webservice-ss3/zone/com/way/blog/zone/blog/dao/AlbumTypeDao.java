package com.way.blog.zone.blog.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.zone.entity.AlbumType;

@Repository("albumTypeDao")
@Transactional
public class AlbumTypeDao extends HibernateGenericDao<AlbumType, Integer> {

}
