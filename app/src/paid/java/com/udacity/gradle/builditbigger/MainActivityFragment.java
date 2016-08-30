package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokespresenter.PresenterActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
	private ProgressDialog progressDialog;
	private Button tellJokeBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


		progressDialog=new ProgressDialog(getContext());
		progressDialog.setMessage(getContext().getString(R.string.progress_message));

		tellJokeBtn=(Button)root.findViewById(R.id.tellJoke);
		tellJokeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				progressDialog.show();

				new EndPointAsyncTask(new EndPointAsyncTask.RequestFinished() {
					@Override
					public void onFinished(String joke) {
						progressDialog.dismiss();
						Intent intent=new Intent(getContext(), PresenterActivity.class);
						intent.putExtra(PresenterActivity.TAG,joke);
						startActivity(intent);
					}
				}).execute();

			}
		});

        return root;
    }
}
