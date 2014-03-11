package com.mywiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


@Entity
@Table(name = "t_user")
@Indexed(index = "indexes/User")
@XmlRootElement
//@Analyzer(impl=SimpleAnalyzer.class)
public class User implements Serializable{
	
	@DocumentId
	private Long uuid;
	@Field(name="email",store= Store.YES,index=Index.YES,analyzer=@Analyzer(impl=SimpleAnalyzer.class))
	private String email;
	private String password;
	@Field(name="nickname",store= Store.YES,index=Index.YES,analyzer=@Analyzer(impl=SimpleAnalyzer.class))
	private String nickname;
	@Field(name="firstName",store= Store.YES,index=Index.YES,analyzer=@Analyzer(impl=SimpleAnalyzer.class))
	private String firstName;
	@Field(name="lastName",store= Store.YES,index=Index.YES,analyzer=@Analyzer(impl=SimpleAnalyzer.class))
    private String lastName;
    private String phoneNumber;
    @Field(name="description",store= Store.YES,index=Index.YES,analyzer=@Analyzer(impl=SimpleAnalyzer.class))
    private String description;
	private String authorities;
	private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(name = "first_name", length = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "last_name", length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	@Column(name = "account_enabled")
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name = "account_expired", nullable = false)
	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	@Column(name = "account_locked", nullable = false)
	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	@Column(name = "credentials_expired", nullable = false)
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
}
