package com.java.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "company")
@Proxy(lazy = false)
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "com_name", nullable = false)
	private String com_name;

	@Column(name = "com_email", nullable = false, unique = true)
	private String com_email;

	@Column(name = "com_pass", nullable = false)
	@JsonIgnore
	private String com_pass;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "picture_url")
	private String picture_url;

	@Column(name = "google_id", unique = true)
	private String google_id;
	@JsonIgnore
	@Column(name = "random_key")
	private String random_key;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_email() {
		return com_email;
	}

	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}

	public String getCom_pass() {
		return com_pass;
	}

	public void setCom_pass(String com_pass) {
		this.com_pass = com_pass;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Company(byte[] image) {
		super();
		this.image = image;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public String getRandom_key() {
		return random_key;
	}

	public void setRandom_key(String random_key) {
		this.random_key = random_key;
	}

	public Company() {
		super();
	}

	public Company(int id, String com_name, String com_email, String com_pass, byte[] image, String picture_url,
			String google_id, String random_key) {
		super();
		this.id = id;
		this.com_name = com_name;
		this.com_email = com_email;
		this.com_pass = com_pass;
		this.image = image;
		this.picture_url = picture_url;
		this.google_id = google_id;
		this.random_key = random_key;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", com_name=" + com_name + ", com_email=" + com_email + ", com_pass=" + com_pass
				+ ", image=" + Arrays.toString(image) + "]";
	}

}
