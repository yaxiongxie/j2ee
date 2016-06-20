package com.xyx.common.orm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class BaseDao extends HibernateDaoSupport{
	
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    } 
	
	/**
     * <保存实体>
     * <完整保存实体>
     * @param t 实体参数
     * @see com.itv.launcher.util.IBaseDao#save(java.lang.Object)
     */
    public void save(Object t) {
        this.getSession().save(t);
    }
     
    /**
     * <保存或者更新实体>
     * @param t 实体
     * @see com.itv.launcher.util.IBaseDao#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(Object t) {
        this.getSession().saveOrUpdate(t);
    }
     
    /**
     * <load>
     * <加载实体的load方法>
     * @param <T>
     * @param <T>
     * @param id 实体的id
     * @return 查询出来的实体
     * @see com.itv.launcher.util.IBaseDao#load(java.io.Serializable)
     */
    public <T> T load(Class<T> t,int id) {
        T load = (T) this.getSession().load(t, id);
        return load;
    }
     
    /**
     * <get>
     * <查找的get方法>
     * @param <T>
     * @param id 实体的id
     * @return 查询出来的实体
     * @see com.itv.launcher.util.IBaseDao#get(java.io.Serializable)
     */
    public <T> T get(Class<T> t,int id) {
        T load = (T) this.getSession().get(t, id);
        return load;
    }
     
    /**
     * <contains>
     * @param t 实体
     * @return 是否包含
     * @see com.itv.launcher.util.IBaseDao#contains(java.lang.Object)
     */
    public boolean contains(Object object) {
        return this.getSession().contains(object);
    }
 
    /**
     * <delete>
     * <删除表中的t数据>
     * @param t 实体
     * @see com.itv.launcher.util.IBaseDao#delete(java.lang.Object)
     */
    public void delete(Object object) {
        this.getSession().delete(object);
    }
     
    /**
     * <根据ID删除数据>
     * @param <T>
     * @param Id 实体id
     * @return 是否删除成功
     * @see com.itv.launcher.util.IBaseDao#deleteById(java.io.Serializable)
     */
    public <T> boolean deleteById(Class<T> tt,int Id) {
         T t = get(tt,Id);
         if(t == null){
             return false;
         }
         delete(t);
        return true;
    }
 
    /**
     * <删除所有>
     * @param <T>
     * @param entities 实体的Collection集合
     * @see com.itv.launcher.util.IBaseDao#deleteAll(java.util.Collection)
     */
    public <T> void deleteAll(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
    }
 
    /**
     * <执行Hql语句>
     * @param hqlString hql
     * @param values 不定参数数组
     * @see com.itv.launcher.util.IBaseDao#queryHql(java.lang.String, java.lang.Object[])
     */
    public void queryHql(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
 
    /**
     * <执行Sql语句>
     * @param sqlString sql
     * @param values 不定参数数组
     * @see com.itv.launcher.util.IBaseDao#querySql(java.lang.String, java.lang.Object[])
     */
    public void querySql(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
 
    /**
     * <根据HQL语句查找唯一实体>
     * @param <T>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     * @see com.itv.launcher.util.IBaseDao#getByHQL(java.lang.String, java.lang.Object[])
     */
	public Object getByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.uniqueResult();
    }
 
    /**
     * <根据SQL语句查找唯一实体>
     * @param <T>
     * @param sqlString SQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     * @see com.itv.launcher.util.IBaseDao#getBySQL(java.lang.String, java.lang.Object[])
     */
    public Object getBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.uniqueResult();
    }
 
    /**
     * <根据HQL语句，得到对应的list>
     * @param <T>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
     */
    @SuppressWarnings("rawtypes")
	public List getListByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
 
    /**
     * <根据SQL语句，得到对应的list>
     * @param <T>
     * @param sqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String, java.lang.Object[])
     */
    @SuppressWarnings("rawtypes")
	public List getListBySQL(String sqlString, Object... values ) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
     
    /**
     * <refresh>
     * @param <T>
     * @param t 实体
     * @see com.itv.launcher.util.IBaseDao#refresh(java.lang.Object)
     */
    public <T> void refresh(T t) {
        this.getSession().refresh(t);
    }
 
    /**
     * <update>
     * @param <T>
     * @param t 实体
     * @see com.itv.launcher.util.IBaseDao#update(java.lang.Object)
     */
    public <T> void update(T t) {
        this.getSession().update(t);
    }
     
    /**
     * <根据HQL得到记录数>
     * @param hql HQL语句
     * @param values 不定参数的Object数组
     * @return 记录总数
     * @see com.itv.launcher.util.IBaseDao#countByHql(java.lang.String, java.lang.Object[])
     */
    public Long countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
    }
 
    /**
     * <HQL分页查询>
     * @param hql HQL语句
     * @param countHql 查询记录条数的HQL语句
     * @param pageNo 下一页
     * @param pageSize 一页总条数
     * @param values 不定Object数组参数
     * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
     * @see com.itv.launcher.util.IBaseDao#findPageByFetchedHql(java.lang.String, java.lang.String, int, int, java.lang.Object[])
     */
    @SuppressWarnings("rawtypes")
	public Page findPageByFetchedHql(String hql, String countHql,
            int pageNo, int pageSize, Object... values) {
        Page retValue = new Page();
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        int currentPage = pageNo > 1 ? pageNo : 1;
        retValue.setCurrentPage(currentPage);
        retValue.setPageSize(pageSize);
        if (countHql == null)
        {
            ScrollableResults results = query.scroll();
            results.last();
            retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
        }
        else
        {
            Long count = countByHql(countHql, values);
            retValue.setTotalCount(count.intValue());
        }
        retValue.resetPageNo();
        List itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        if (itemList == null)
        {
            itemList = new ArrayList();
        }
        retValue.setList(itemList);
         
        return retValue;
    }
    
    /**
     * 
     * @return session
     */
    public Session getSession() {
        //需要开启事物，才能得到CurrentSession
        return this.getSessionFactory().getCurrentSession();
    }
     
    /**
     * 
     * 设置每行批处理参数
     * 
     * @param ps
     * @param pos ?占位符索引，从0开始
     * @param data
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	private void setParameter(PreparedStatement ps, int pos, Object data)
        throws SQLException
    {
        if (data == null)
        {
            ps.setNull(pos + 1, Types.VARCHAR);
            return;
        }
        Class dataCls = data.getClass();
        if (String.class.equals(dataCls))
        {
            ps.setString(pos + 1, (String)data);
        }
        else if (boolean.class.equals(dataCls))
        {
            ps.setBoolean(pos + 1, ((Boolean)data));
        }
        else if (int.class.equals(dataCls))
        {
            ps.setInt(pos + 1, (Integer)data);
        }
        else if (double.class.equals(dataCls))
        {
            ps.setDouble(pos + 1, (Double)data);
        }
        else if (Date.class.equals(dataCls))
        {
            Date val = (Date)data;
            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        }
        else if (BigDecimal.class.equals(dataCls))
        {
            ps.setBigDecimal(pos + 1, (BigDecimal)data);
        }
        else
        {
            // 未知类型
            ps.setObject(pos + 1, data);
        }
         
    }

}
