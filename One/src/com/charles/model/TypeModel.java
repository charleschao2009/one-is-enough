package com.charles.model;

public class TypeModel {

	private String volume;
	private String author;

	public TypeModel(String volume, String author) {
		super();
		this.volume = volume;
		this.author = author;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
