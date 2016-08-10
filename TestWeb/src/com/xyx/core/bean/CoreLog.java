package com.xyx.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreLog entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreLog")
@Table(name="core_log"
    ,catalog="platform"
)

public class CoreLog  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Integer id;
     private String username;
     private Integer userid;
     private String accessurl;
     private String modelname;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreLog() {
    }

    
    /** full constructor */
    public CoreLog(String username, Integer userid, String accessurl, String modelname, String createtime) {
        this.username = username;
        this.userid = userid;
        this.accessurl = accessurl;
        this.modelname = modelname;
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
    
    @Column(name="username")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="userid")
    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    @Column(name="accessurl")
    public String getAccessurl() {
        return this.accessurl;
    }
    
    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }
    
    @Column(name="modelname")

    public String getModelname() {
        return this.modelname;
    }
    
    public void setModelname(String modelname) {
        this.modelname = modelname;
    }
    
    @Column(name="createtime")

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   








}