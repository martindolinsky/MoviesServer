package com.example.demo.model;

/**
 * @author Martin Dolinsky
 */
public class TvSeries {
	private int serialID;
	private String titleEN;
	private String titleSK;
	private int year;
	private String director;
	private String actors;
	private String description;
	private String genre;
	private String secondTitleEN;
	private String secondTitleSK;
	private String src;
	private String srcImg;
	private String dataTitle;

	public TvSeries
			(int serialID, String titleEN, String titleSK, int year, String director, String actors, String description,
			 String genre, String secondTitleEN, String secondTitleSK, String src, String srcImg, String dataTitle) {
		this.serialID = serialID;
		this.titleEN = titleEN;
		this.titleSK = titleSK;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.description = description;
		this.genre = genre;
		this.secondTitleEN = secondTitleEN;
		this.secondTitleSK = secondTitleSK;
		this.src = src;
		this.srcImg = srcImg;
		this.dataTitle = dataTitle;
	}

	public TvSeries(
			String titleEN, String titleSK, int year, String director, String actors, String description, String genre,
			String secondTitleEN, String secondTitleSK, String src, String srcImg, String dataTitle) {
		this.titleEN = titleEN;
		this.titleSK = titleSK;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.description = description;
		this.genre = genre;
		this.secondTitleEN = secondTitleEN;
		this.secondTitleSK = secondTitleSK;
		this.src = src;
		this.srcImg = srcImg;
		this.dataTitle = dataTitle;
	}

	public TvSeries() {
	}

	public int getSerialID() {
		return serialID;
	}

	public void setSerialID(int serialID) {
		this.serialID = serialID;
	}

	public String getTitleEN() {
		return titleEN;
	}

	public void setTitleEN(String titleEN) {
		this.titleEN = titleEN;
	}

	public String getTitleSK() {
		return titleSK;
	}

	public void setTitleSK(String titleSK) {
		this.titleSK = titleSK;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSecondTitleEN() {
		return secondTitleEN;
	}

	public void setSecondTitleEN(String secondTitleEN) {
		this.secondTitleEN = secondTitleEN;
	}

	public String getSecondTitleSK() {
		return secondTitleSK;
	}

	public void setSecondTitleSK(String secondTitleSK) {
		this.secondTitleSK = secondTitleSK;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getSrcImg() {
		return srcImg;
	}

	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}

	public String getDataTitle() {
		return dataTitle;
	}

	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}

	@Override
	public String toString() {
		return titleEN + " " + secondTitleEN;
	}
}
