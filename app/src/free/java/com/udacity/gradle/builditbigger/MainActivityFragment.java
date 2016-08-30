package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokespresenter.PresenterActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


	private ProgressDialog progressDialog;
	private Button tellJokeBtn;
	private String jokeStr;
	private InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

		progressDialog=new ProgressDialog(getContext());
		progressDialog.setMessage(getString(R.string.progress_message));

		setUpInterstitialAds();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ABCDEF012345")
                .build();
        mAdView.loadAd(adRequest);
		tellJokeBtn=(Button)root.findViewById(R.id.tellJoke);
		tellJokeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressDialog.show();
				new EndPointAsyncTask(new EndPointAsyncTask.RequestFinished() {
					@Override
					public void onFinished(String joke) {
						progressDialog.dismiss();
						jokeStr=joke;
						mInterstitialAd.show();
					}
				}).execute();

			}
		});


        return root;
    }

	@Override
	public void onResume() {
		super.onResume();
		setUpInterstitialAds();
	}

	private void setUpInterstitialAds(){

		mInterstitialAd = new InterstitialAd(getContext());
		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				Intent intent=new Intent(getContext(), PresenterActivity.class);
				intent.putExtra(PresenterActivity.TAG,jokeStr);
				startActivity(intent);
			}
		});
		requestNewInterstitial();

	}

	private void requestNewInterstitial() {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();

		mInterstitialAd.loadAd(adRequest);
		Log.d("mytag","load add");
	}
}
