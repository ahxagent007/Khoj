package com.secretdevelopersltd.dexian.khoj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeActivity extends AppCompatActivity {

    String TAG = "XIAN";


    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6049858234928469/3576405511"); //test ads ca-app-pub-3940256099942544/1033173712 // MY APP ca-app-pub-6049858234928469/3576405511
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i(TAG, "Code to be executed when an ad finishes loading.");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i(TAG, "Code to be executed when an ad request fails.");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i(TAG, "Code to be executed when the ad is displayed.");
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.i(TAG, "Code to be executed when the user clicks on an ad.");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i(TAG, "Code to be executed when the user has left the app.");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                Log.i(TAG, "Code to be executed when the interstitial ad is closed.");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

    }

    public void CLICKED(View view) {

        String category = null;

        switch (view.getId()){

            case R.id.btn_bloodDonor:
                category = "BLOOD";
                break;

            case R.id.btn_doctor:
                category = "DOCTOR";
                break;

            case R.id.btn_hospital:
                category = "HOSPITAL";
                break;

            case R.id.btn_mechanic:
                category = "MECHANIC";
                break;

            case R.id.btn_police:
                category = "POLICE";
                break;

            case R.id.btn_fireService:
                category = "FIRE_SERVICE";
                break;

            case R.id.btn_bank:
                category = "BANK";
                break;

            case R.id.btn_electricWorker:
                category = "ELECTRIC";
                break;

            case R.id.btn_gasWorker:
                category = "GAS";
                break;

            case R.id.btn_veterinarian:
                category = "VETERINARIAN";
                break;

            case R.id.btn_university:
                category = "UNIVERSITY";
                break;

            case R.id.btn_governmentHotLine:
                category = "GOVERNMENT";
                break;

            default:
                category = "DEFAULT";
        }

        Load_popUu_Ads();

        Intent i = new Intent(getApplicationContext(), ViewPersonsActivity.class);
        i.putExtra("CAT",category);
        startActivity(i);



    }

    private void Load_popUu_Ads(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.i(TAG, "The interstitial wasn't loaded yet.");

        }

    }
}
