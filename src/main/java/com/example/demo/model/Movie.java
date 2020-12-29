package com.example.demo.model;

import javax.persistence.*;

/**
 * @author Martin Dolinsky
 */
@Entity(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieID")
	private int movieID;
	@Column(name = "titleEN")
	private String titleEN;
	@Column(name = "titleSK")
	private String titleSK;
	@Column(name = "genre")
	private String genre;
	@Column(name = "year")
	private int year;
	@Column(name = "director")
	private String director;
	@Column(name = "actors")
	private String actors;
	@Column(name = "description")
	private String description;
	@Column(name = "secondTitleEN")
	private String secondTitleEN;
	@Column(name = "secondTitleSK")
	private String secondTitleSK;
	@Column(name = "src")
	private String src;
	@Column(name = "srcImg")
	private String srcImg;
	@Column(name = "length")
	private int length;
	@Column(name = "imdbSrc")
	private String imdbSrc;
	@Column(name = "dataTitle")
	private String dataTitle;

	public Movie
			(int movieID, String titleEN, String titleSK, String genre, int year, String director, String actors,
			 String description, String secondTitleEN, String secondTitleSK, String src, String srcImg, int length,
			 String imdbSrc, String dataTitle) {
		this.movieID = movieID;
		this.titleEN = titleEN;
		this.titleSK = titleSK;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.description = description;
		this.secondTitleEN = secondTitleEN;
		this.secondTitleSK = secondTitleSK;
		this.src = src;
		this.srcImg = srcImg;
		this.length = length;
		this.imdbSrc = imdbSrc;
		this.dataTitle = dataTitle;
	}

	public Movie(String titleEN, String titleSK, String genre, int year, String director, String actors, String description, String secondTitleEN, String secondTitleSK, String src, String srcImg, int length, String imdbSrc, String dataTitle) {
		this.titleEN = titleEN;
		this.titleSK = titleSK;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.actors = actors;
		this.description = description;
		this.secondTitleEN = secondTitleEN;
		this.secondTitleSK = secondTitleSK;
		this.src = src;
		this.srcImg = srcImg;
		this.length = length;
		this.imdbSrc = imdbSrc;
		this.dataTitle = dataTitle;
	}

	public Movie() {
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getImdbSrc() {
		return imdbSrc;
	}

	public void setImdbSrc(String imdbSrc) {
		this.imdbSrc = imdbSrc;
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
