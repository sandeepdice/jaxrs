package com.sandy.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="user")
public class User {

	public User(String userName, String age) {
		this.userName = userName;
		this.age = age;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	String userName;
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@XmlElement
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	String age;
}
