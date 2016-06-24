package com.xyx.core.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CorePerson entity. @author MyEclipse Persistence Tools
 */
@Entity
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
     private Integer ismarray;
     private String birthday;
     private String homeAddress;
     private String recentAddress;
     private String professionName;
     private String schoolName;
     private String positionName;
     private String pid;
     private String telephone;
     private String email;
     private String password;
     private String remark;
     private Timestamp createtime;
     private Integer departmentId;
     private Integer status;


    // Constructors

    /** default constructor */
    public CorePerson() {
    }

    
    /** full constructor */
    public CorePerson(String username, String realname, String code, Integer sex, Integer ismarray, String birthday, String homeAddress, String recentAddress, String professionName, String schoolName, String positionName, String pid, String telephone, String email, String password, String remark, Timestamp createtime, Integer departmentId, Integer status) {
        this.username = username;
        this.realname = realname;
        this.code = code;
        this.sex = sex;
        this.ismarray = ismarray;
        this.birthday = birthday;
        this.homeAddress = homeAddress;
        this.recentAddress = recentAddress;
        this.professionName = professionName;
        this.schoolName = schoolName;
        this.positionName = positionName;
        this.pid = pid;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.remark = remark;
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
    
    @Column(name="ismarray")

    public Integer getIsmarray() {
        return this.ismarray;
    }
    
    public void setIsmarray(Integer ismarray) {
        this.ismarray = ismarray;
    }
    
    @Column(name="birthday")

    public String getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="home_address")

    public String getHomeAddress() {
        return this.homeAddress;
    }
    
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    @Column(name="recent_address")

    public String getRecentAddress() {
        return this.recentAddress;
    }
    
    public void setRecentAddress(String recentAddress) {
        this.recentAddress = recentAddress;
    }
    
    @Column(name="profession_name")

    public String getProfessionName() {
        return this.professionName;
    }
    
    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }
    
    @Column(name="school_name")

    public String getSchoolName() {
        return this.schoolName;
    }
    
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    @Column(name="position_name")

    public String getPositionName() {
        return this.positionName;
    }
    
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    @Column(name="pid")

    public String getPid() {
        return this.pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
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
    
    @Column(name="remark")

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="createtime", length=19)

    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
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