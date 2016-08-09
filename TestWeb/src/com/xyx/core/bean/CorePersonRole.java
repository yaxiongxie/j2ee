package com.xyx.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CorePersonRole entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CorePersonRole")
@Table(name="core_person_role"
    ,catalog="platform"
)

public class CorePersonRole  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Integer roleId;
     private Integer personId;


    // Constructors

    /** default constructor */
    public CorePersonRole() {
    }

    
    /** full constructor */
    public CorePersonRole(Integer roleId, Integer personId) {
        this.roleId = roleId;
        this.personId = personId;
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
    
    @Column(name="person_id")

    public Integer getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
   








}