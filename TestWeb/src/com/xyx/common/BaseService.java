package com.xyx.common;

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

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class BaseService extends HibernateDaoSupport{
	
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    } 
	
	/**
     * <����ʵ��>
     * <�����ʵ��>
     * @param t ʵ�����
     * @see com.itv.launcher.util.IBaseDao#save(java.lang.Object)
     */
    public void save(Object t) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        this.getSession().save(t);
        this.getSession().flush();
    }
     
    /**
     * <������߸���ʵ��>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(Object t) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        this.getSession().saveOrUpdate(t);
        this.getSession().flush();
    }
     
    /**
     * <load>
     * <����ʵ���load����>
     * @param <T>
     * @param <T>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     * @see com.itv.launcher.util.IBaseDao#load(java.io.Serializable)
     */
    @SuppressWarnings("unchecked")
	public <T> T load(Class<T> t,int id) {
        T load = (T) this.getSession().load(t, id);
        return load;
    }
     
    /**
     * <get>
     * <���ҵ�get����>
     * @param <T>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     * @see com.itv.launcher.util.IBaseDao#get(java.io.Serializable)
     */
    @SuppressWarnings("unchecked")
	public <T> T get(Class<T> t,int id) {
        T load = (T) this.getSession().get(t, id);
        return load;
    }
     
    /**
     * <contains>
     * @param t ʵ��
     * @return �Ƿ��
     * @see com.itv.launcher.util.IBaseDao#contains(java.lang.Object)
     */
    public boolean contains(Object object) {
        return this.getSession().contains(object);
    }
 
    /**
     * <delete>
     * <ɾ����е�t���>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#delete(java.lang.Object)
     */
    public void delete(Object object) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        this.getSession().delete(object);
        this.getSession().flush();
    }
     
    /**
     * <���IDɾ�����>
     * @param <T>
     * @param Id ʵ��id
     * @return �Ƿ�ɾ��ɹ�
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
     * <ɾ������>
     * @param <T>
     * @param entities ʵ���Collection����
     * @see com.itv.launcher.util.IBaseDao#deleteAll(java.util.Collection)
     */
    public <T> void deleteAll(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
    }
 
    /**
     * <ִ��Hql���>
     * @param hqlString hql
     * @param values ������������
     * @see com.itv.launcher.util.IBaseDao#queryHql(java.lang.String, java.lang.Object[])
     */
    public void queryHql(String hqlString, Object... values) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
        this.getSession().flush();
    }
 
    /**
     * <ִ��Sql���>
     * @param sqlString sql
     * @param values ������������
     * @see com.itv.launcher.util.IBaseDao#querySql(java.lang.String, java.lang.Object[])
     */
    public void querySql(String sqlString, Object... values) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
        this.getSession().flush();
    }
 
    /**
     * <���HQL������Ψһʵ��>
     * @param <T>
     * @param hqlString HQL���
     * @param values ���������Object����
     * @return ��ѯʵ��
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
     * <���SQL������Ψһʵ��>
     * @param <T>
     * @param sqlString SQL���
     * @param values ���������Object����
     * @return ��ѯʵ��
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
     * <���HQL��䣬�õ���Ӧ��list>
     * @param <T>
     * @param hqlString HQL���
     * @param values ���������Object����
     * @return ��ѯ���ʵ���List����
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
     * <���SQL��䣬�õ���Ӧ��list>
     * @param <T>
     * @param sqlString HQL���
     * @param values ���������Object����
     * @return ��ѯ���ʵ���List����
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
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#refresh(java.lang.Object)
     */
    public <T> void refresh(T t) {
        this.getSession().refresh(t);
    }
 
    /**
     * <update>
     * @param <T>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#update(java.lang.Object)
     */
    public <T> void update(T t) {
    	this.getSession().setFlushMode(FlushMode.AUTO);
        this.getSession().update(t);
        this.getSession().flush();
    }
     
    /**
     * <���HQL�õ���¼��>
     * @param hql HQL���
     * @param values ���������Object����
     * @return ��¼����
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
     * <HQL��ҳ��ѯ>
     * @param hql HQL���
     * @param countHql ��ѯ��¼�����HQL���
     * @param pageNo ��һҳ
     * @param pageSize һҳ������
     * @param values ����Object�������
     * @return PageResults�ķ�װ�࣬�������ҳ�����Ϣ�Լ���ѯ�����List����
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
            retValue.setTotalCount(results.getRowNumber() + 1);// �����ܼ�¼��
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
        //��Ҫ����������ܵõ�CurrentSession
        return this.getSessionFactory().getCurrentSession();
    }
     
    /**
     * 
     * ����ÿ���������
     * 
     * @param ps
     * @param pos ?ռλ�������0��ʼ
     * @param data
     * @throws SQLException
     * @see [�ࡢ��#��������#��Ա]
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
            // δ֪����
            ps.setObject(pos + 1, data);
        }
         
    }

}
