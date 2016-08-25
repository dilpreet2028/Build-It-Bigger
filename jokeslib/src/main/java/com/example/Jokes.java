package com.example;

import java.util.Random;

public class Jokes {
	private static String[] jokes={"Do you know what makes a big difference?\n" +
			"In morning when it is 7:00 and 7:05!","There is two kinds of suicide..\n" +
			"One is take a rope and hand on fa..Second have marriage and hand on whole life..",
			"No matters, how good work, noble cause you do... people always remember those who dies after borrowing some dollars!!"};

	public static String getJoke(){
		Random random=new Random();
		return jokes[random.nextInt(jokes.length)];
	}
}
