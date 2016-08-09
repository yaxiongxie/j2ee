package com.xyx.core.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CoreAttachment entity. @author MyEclipse Persistence Tools
 */
@Entity(name="CoreAttachment")
@Table(name="core_attachment"
    ,catalog="platform"
)

public class CoreAttachment  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String filename;
     private Integer filesize;
     private String filetype;
     private byte[] filedata;
     private String tablename;
     private Integer relationid;
     private String createtime;


    // Constructors

    /** default constructor */
    public CoreAttachment() {
    }

    
    /** full constructor */
    public CoreAttachment(String filename, Integer filesize, String filetype, byte[] filedata, String tablename, Integer relationid, String createtime) {
        this.filename = filename;
        this.filesize = filesize;
        this.filetype = filetype;
        this.filedata = filedata;
        this.tablename = tablename;
        this.relationid = relationid;
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
    
    @Column(name="filename")

    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    @Column(name="filesize")

    public Integer getFilesize() {
        return this.filesize;
    }
    
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }
    
    @Column(name="filetype")

    public String getFiletype() {
        return this.filetype;
    }
    
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
    
    @Column(name="filedata")

    public byte[] getFiledata() {
        return this.filedata;
    }
    
    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
    
    @Column(name="tablename")

    public String getTablename() {
        return this.tablename;
    }
    
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    @Column(name="relationid")

    public Integer getRelationid() {
        return this.relationid;
    }
    
    public void setRelationid(Integer relationid) {
        this.relationid = relationid;
    }
    
    @Column(name="createtime", length=19)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
   








}