package com.entity;
import java.util.*;
import javax.persistence.*;

@Entity 
@Table(name = "myUser")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String userName;
@OneToMany(mappedBy = "user")
private Set<Address> addresses;
@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
@Temporal(TemporalType.TIMESTAMP)
private Date insertTime;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Set<Address> getAddresses() {
	return addresses;
}
public void setAddresses(Set<Address> addresses) {
	this.addresses = addresses;
}
public Date getInsertTime() {
	return insertTime;
}
public void setInsertTime(Date insertTime) {
	this.insertTime = insertTime;
}

}
