package com.xyx.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreRoleAuth entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreRoleAuth")
@Table(name="core_role_auth"
    ,catalog="platform"
)

public class CoreRoleAuth  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Integer roleId;
     private Integer authId;


    // Constructors

    /** default constructor */
    public CoreRoleAuth() {
    }

    
    /** full constructor */
    public CoreRoleAuth(Integer roleId, Integer authId) {
        this.roleId = roleId;
        this.authId = authId;
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
    
    @Column(name="role_id")

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    @Column(name="auth_id")

    public Integer getAuthId() {
        return this.authId;
    }
    
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
   








}