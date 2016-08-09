package com.xyx.document.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Document entity. @author MyEclipse Persistence Tools
 */
@Entity(name="Document")
@Table(name="document"
    ,catalog="platform"
)

public class Document  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String doctitle;
     private String doctype;
     private Integer docsize;
     private String doccontent;
     private Integer personid;
     private String createtime;
     private Integer isopen;
     private Integer categoryid;


    // Constructors

    /** default constructor */
    public Document() {
    }

    
    /** full constructor */
    public Document(String doctitle, String doctype, Integer docsize, String doccontent, Integer personid, String createtime, Integer isopen, Integer categoryid) {
        this.doctitle = doctitle;
        this.doctype = doctype;
        this.docsize = docsize;
        this.doccontent = doccontent;
        this.personid = personid;
        this.createtime = createtime;
        this.isopen = isopen;
        this.categoryid = categoryid;
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
    
    @Column(name="doctitle")

    public String getDoctitle() {
        return this.doctitle;
    }
    
    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle;
    }
    
    @Column(name="doctype")

    public String getDoctype() {
        return this.doctype;
    }
    
    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }
    
    @Column(name="docsize")

    public Integer getDocsize() {
        return this.docsize;
    }
    
    public void setDocsize(Integer docsize) {
        this.docsize = docsize;
    }
    
    @Column(name="doccontent")

    public String getDoccontent() {
        return this.doccontent;
    }
    
    public void setDoccontent(String doccontent) {
        this.doccontent = doccontent;
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
    
    @Column(name="isopen")

    public Integer getIsopen() {
        return this.isopen;
    }
    
    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }
    
    @Column(name="categoryid")

    public Integer getCategoryid() {
        return this.categoryid;
    }
    
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }
   








}