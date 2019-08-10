package com.accolite.au;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer userId;
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;
	@Column(name="attempt")
	Integer attempt;
	@Column(name="ts1")
	Long ts1;
	@Column(name="ts2")
	Long ts2;
	@Column(name="ts3")
	Long ts3;
	@Column(name="restrictionLevel")
	Integer restrictionLevel;
	
	@Column(name="flagLevel1")
	Integer flagLevel1;
	
	@Column(name="flagLevel2")
	Integer flagLevel2;
	
	@Column(name="flagLevel3")
	Integer flagLevel3;
	
	public User(Integer userId,String username ,String password, Integer attempt, Long ts1 ,Integer restrictionLevel, Long ts2, Long ts3, Integer flagLevel1,Integer flagLevel2, Integer flagLevel3) {
		super();
		
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.attempt = attempt;
		this.ts1 = ts1;
		this.ts2 = ts2;
		this.ts3 = ts3;
		this.restrictionLevel = restrictionLevel;
		this.flagLevel1 = flagLevel1;
		this.flagLevel2 = flagLevel2;
		this.flagLevel3 = flagLevel3;
	}
	public Integer getFlagLevel1() {
		return flagLevel1;
	}
	public void setFlagLevel1(Integer flagLevel1) {
		this.flagLevel1 = flagLevel1;
	}
	public Integer getFlagLevel2() {
		return flagLevel2;
	}
	public void setFlagLevel2(Integer flagLevel2) {
		this.flagLevel2 = flagLevel2;
	}
	public Integer getFlagLevel3() {
		return flagLevel3;
	}
	public void setFlagLevel3(Integer flagLevel3) {
		this.flagLevel3 = flagLevel3;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAttempt() {
		return attempt;
	}
	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}
	public Long getTs1() {
		return ts1;
	}
	public void setTs1(Long ts1) {
		this.ts1 = ts1;
	}
	public Long getTs2() {
		return ts2;
	}
	public void setTs2(Long ts2) {
		this.ts2 = ts2;
	}
	public Long getTs3() {
		return ts3;
	}
	public void setTs3(Long ts3) {
		this.ts3 = ts3;
	}
	public Integer getRestrictionLevel() {
		return restrictionLevel;
	}
	public void setRestrictionLevel(Integer restrictionLevel) {
		this.restrictionLevel = restrictionLevel;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", attempt=" + attempt
				+ ", ts1=" + ts1 + ", ts2=" + ts2 + ", ts3=" + ts3 + ", restrictionLevel=" + restrictionLevel
				+ ", flagLevel1=" + flagLevel1 + ", flagLevel2=" + flagLevel2 + ", flagLevel3=" + flagLevel3 + "]";
	}


}
