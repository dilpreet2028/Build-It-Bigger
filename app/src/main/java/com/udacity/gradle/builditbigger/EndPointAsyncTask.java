package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.example.dilpreet.myapplication.backend.jokesApi.JokesApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by dilpreet on 26/8/16.
 */
public class EndPointAsyncTask extends AsyncTask<Void,Void,String> {
	JokesApi jokesApi;

	RequestFinished requestFinished;
	public EndPointAsyncTask(RequestFinished requestFinished) {
		super();
		this.requestFinished=requestFinished;
	}

	@Override
	protected String doInBackground(Void... params) {



		JokesApi.Builder builder=new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
				new AndroidJsonFactory(),null)
				.setRootUrl("http://10.0.2.2:8080/_ah/api/")
				.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
					@Override
					public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
						request.setDisableGZipContent(true);
					}
				});

		jokesApi=builder.build();

		try{
			return jokesApi.fetchJoke().execute().getJoke();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		if(s!=null)
			requestFinished.onFinished(s);
	}

	public interface RequestFinished{
		public void onFinished(String joke);
	}
}
