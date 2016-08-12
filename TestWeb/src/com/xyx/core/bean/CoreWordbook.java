package com.xyx.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreWordbook entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreWordbook")
@Table(name="core_wordbook"
    ,catalog="platform"
)

public class CoreWordbook  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String name;
     private String code;
     private Integer ordernum;
     private Integer categoryId;
     private Integer pid;
     private String personname;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreWordbook() {
    }

    
    /** full constructor */
    public CoreWordbook(String name, String code, Integer ordernum, Integer categoryId, Integer pid, String createtime) {
        this.name = name;
        this.code = code;
        this.ordernum = ordernum;
        this.categoryId = categoryId;
        this.pid = pid;
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
    
    @Column(name="ordernum")

    public Integer getOrdernum() {
        return this.ordernum;
    }
    
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
    
    @Column(name="category_id")

    public Integer getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    @Column(name="pid")

    public Integer getPid() {
        return this.pid;
    }
    
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    @Column(name="createtime")

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


	public String getPersonname() {
		return personname;
	}


	public void setPersonname(String personname) {
		this.personname = personname;
	}
   








}