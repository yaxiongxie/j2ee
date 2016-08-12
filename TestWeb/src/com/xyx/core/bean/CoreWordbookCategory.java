package com.xyx.core.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xyx.common.tree.TreeBean;


/**
 * CoreWordbookCategory entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreWordbookCategory")
@Table(name="core_wordbook_category"
    ,catalog="platform"
)

public class CoreWordbookCategory extends TreeBean implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String name;
     private String code;
     private Integer parentId;
     private Integer ordernum;
     private Integer personid;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreWordbookCategory() {
    }

    
    /** full constructor */
    public CoreWordbookCategory(String name, String code, Integer parentId, Integer ordernum, Integer personid, String createtime) {
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.ordernum = ordernum;
        this.personid = personid;
        this.createtime = createtime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="name")

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="code")

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="parent_id")

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
    @Column(name="ordernum")

    public Integer getOrdernum() {
        return this.ordernum;
    }
    
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
    
    @Column(name="personid")

    public Integer getPersonid() {
        return this.personid;
    }
    
    public void setPersonid(Integer personid) {
        this.personid = personid;
    }
    
    @Column(name="createtime", length=19)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   








}