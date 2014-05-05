package com.sandy.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="user")
public class User {

	public User(String userName, String age) {
		this.userName = userName;
		this.age = age;
	}
	
	@XmlElement(name="User Name")
	String userName;
	@XmlElement(name="First Name")
	String age;
}
