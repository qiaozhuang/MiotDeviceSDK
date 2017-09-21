package com.miot.android.sdk.app;

import android.app.Application;

import com.miot.android.sdk.MiotSDKInitializer;

/**
 * Created by Administrator on 2017/9/19 0019.
 */
public class PubApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();


		try {
			MiotSDKInitializer.getInstance().init(this,"00:0E:A3:BD:64:31");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
