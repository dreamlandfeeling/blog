package com.xin.yxblog.model;

import java.util.Date;

/**
 * 评论表
 */
public class Comment {

    private Integer id;

    /**
     * 被评论文章id
     */
    private Integer aid;

    /**
     * 被评论id
     */
    private Integer cid;

    /**
     * 该评论作者
     */
    private String author;

    /**
     * 评论者邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态:0删除1正常2审批
     */
    private Integer status;

    /**
     * 是否是博主  1为是
     */
    private Integer isAdmin;

    private Article article;

    private Date createtime;

    public Comment(){}

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getAid(){
        return aid;
    }

    public void setAid(Integer aid){
        this.aid = aid;
    }

    public Integer getCid(){
        return cid;
    }

    public void setCid(Integer cid){
        this.cid = cid;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getIsAdmin(){
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin){
        this.isAdmin = isAdmin;
    }

    public Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}