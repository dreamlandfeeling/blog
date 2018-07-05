package com.xin.yxblog.model;

import java.util.Date;

/**
 * 学生表
 */
public class Student {

    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 状态
     */
    private Boolean status;

    private String dName;

    private String dAgeBitdat;

    private String content;

    public Student() {
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDName(){
        return dName;
    }

    public void setDName(String dName){
        this.dName = dName;
    }

    public String getDAgeBitdat(){
        return dAgeBitdat;
    }

    public void setDAgeBitdat(String dAgeBitdat){
        this.dAgeBitdat = dAgeBitdat;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", status=" + status +
                ", dName='" + dName + '\'' +
                ", dAgeBitdat='" + dAgeBitdat + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Student(Integer id, String name, Integer age, Date birthday, Boolean status, String dName, String dAgeBitdat, String content) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.status = status;
        this.dName = dName;
        this.dAgeBitdat = dAgeBitdat;
        this.content = content;
    }
}