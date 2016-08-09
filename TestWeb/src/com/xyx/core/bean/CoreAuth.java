package com.xyx.core.bean;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreAuth entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreAuth")
@Table(name="core_auth"
    ,catalog="platform"
)

public class CoreAuth  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String functionName;
     private String functionUrl;
     private String groupname;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreAuth() {
    }

    
    /** full constructor */
    public CoreAuth(String functionName, String functionUrl, String groupname, String createtime) {
        this.functionName = functionName;
        this.functionUrl = functionUrl;
        this.groupname = groupname;
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
    
    @Column(name="function_name")

    public String getFunctionName() {
        return this.functionName;
    }
    
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
    @Column(name="function_url")

    public String getFunctionUrl() {
        return this.functionUrl;
    }
    
    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }
    
    @Column(name="groupname")

    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    
    @Column(name="createtime", length=19)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   








}