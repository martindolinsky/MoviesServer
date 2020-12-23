package com.example.demo.entity;

/**
 * @author Martin Dolinsky
 */
public class Movie {
	private String titleEN;
	private String secondTitleEN;

	public Movie(String titleEN, String secondTitleEN) {
		this.titleEN = titleEN;
		this.secondTitleEN = secondTitleEN;
	}

	public Movie() {
	}

	public String getTitleEN() {
		return titleEN;
	}

	public void setTitleEN(String titleEN) {
		this.titleEN = titleEN;
	}

	public String getSecondTitleEN() {
		return secondTitleEN;
	}

	public void setSecondTitleEN(String secondTitleEN) {
		this.secondTitleEN = secondTitleEN;
	}

	@Override
	public String toString() {
		return titleEN + " " + secondTitleEN;
	}
}
