package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Created by dilpreet on 27/8/16.
 */
public class EndPointTest extends AndroidTestCase {

	String jokeStr;
	CountDownLatch signal;


	public void testFetchJoke(){


		EndPointAsyncTask endPointAsyncTask=new EndPointAsyncTask(new EndPointAsyncTask.RequestFinished() {
			@Override
			public void onFinished(String joke) {
			}
		});

		endPointAsyncTask.execute();
		try {
			signal.await(10,TimeUnit.SECONDS);
			jokeStr=endPointAsyncTask.get();
			assertNotNull("Joke fetched successfully",jokeStr);

		}
		catch (Exception e){
			e.printStackTrace();
		}

	}
}
