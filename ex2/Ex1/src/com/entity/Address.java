package com.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String detail;
@ManyToOne
private User user;
@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
@Temporal(TemporalType.TIMESTAMP)
private Date insertTime;
// 省略getter/setter方法
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Date getInsertTime() {
	return insertTime;
}
public void setInsertTime(Date insertTime) {
	this.insertTime = insertTime;
}

}
