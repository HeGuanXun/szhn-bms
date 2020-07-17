package com.szhn.hegx.bms.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>书籍实体类</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/

public class Book implements Serializable {

    private Integer bid;

    private String bookName;

    private String author;

    private String type;

    /**
     * 发布人/机构
     */
    private String publisher;

    /**
     * 发布时间
     */
    private String publicationDate;

    private BigDecimal price;

    private int status;

    private String remark;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
