package com.xyx.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CorePerson entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CorePerson")
@Table(name="core_person"
    ,catalog="platform"
)

public class CorePerson  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String username;
     private String realname;
     private String code;
     private Integer sex;
     private String birthday;
     private String positionName;
     private String telephone;
     private String email;
     private String password;
     private String createtime;
     private Integer departmentId;
     private Integer status;


    // Constructors

    /** default constructor */
    public CorePerson() {
    }

    
    /** full constructor */
    public CorePerson(String username, String realname, String code, Integer sex, String birthday, String positionName, String telephone, String email, String password, String createtime, Integer departmentId, Integer status) {
        this.username = username;
        this.realname = realname;
        this.code = code;
        this.sex = sex;
        this.birthday = birthday;
        this.positionName = positionName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.createtime = createtime;
        this.departmentId = departmentId;
        this.status = status;
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
    
    @Column(name="realname")

    public String getRealname() {
        return this.realname;
    }
    
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    @Column(name="code")

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="sex")

    public Integer getSex() {
        return this.sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    
    @Column(name="birthday")

    public String getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="position_name")

    public String getPositionName() {
        return this.positionName;
    }
    
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    @Column(name="telephone")

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="email")

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="password")

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="createtime", length=19)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    @Column(name="department_id")

    public Integer getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}