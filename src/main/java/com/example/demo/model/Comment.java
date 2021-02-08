package com.example.demo.model;

import javax.persistence.*;

/**
 * @author Martin Dolinsky
 */
@Entity(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentID")
	private int id;

	@Column(name = "userID")
	private int userId;

	@Column(name = "date")
	private String date;

	@Column(name = "message")
	private String message;

	@Column(name = "movieID")
	private int movieId;

	@Column(name = "serialID")
	private int serialID;

	public Comment() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getSerialID() {
		return serialID;
	}

	public void setSerialID(int serialID) {
		this.serialID = serialID;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", userId=" + userId +
				", date=" + date +
				", message='" + message + '\'' +
				", movieId=" + movieId +
				", serialID=" + serialID +
				'}';
	}
}
