package com.example.dilpreet.myapplication.backend;

/** The object model for the data we are sending through endpoints */
public class MyJokeBean {

    private String joke;

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}
}