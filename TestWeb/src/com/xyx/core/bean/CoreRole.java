package com.xyx.core.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreRole entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreRole")
@Table(name="core_role"
    ,catalog="platform"
)

public class CoreRole  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7618826611204395131L;
	private Integer id;
     private String name;
     private Integer pcount;
     private String names;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreRole() {
    }

    
    /** full constructor */
    public CoreRole(String name, Integer pcount, String names, String createtime) {
        this.name = name;
        this.pcount = pcount;
        this.names = names;
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
    
    @Column(name="pcount")

    public Integer getPcount() {
        return this.pcount;
    }
    
    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }
    
    @Column(name="names")

    public String getNames() {
        return this.names;
    }
    
    public void setNames(String names) {
        this.names = names;
    }
    
    @Column(name="createtime", length=19)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   








}