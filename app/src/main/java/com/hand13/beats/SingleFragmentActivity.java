package com.hand13.beats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hd110 on 2017/7/11.
 */

public  abstract class SingleFragmentActivity extends AppCompatActivity {
	protected abstract Fragment createFragment();
	public void onCreate(Bundle args){
		super.onCreate(args);
		setContentView(R.layout.main_activity);
		FragmentManager fragmentManager=getSupportFragmentManager();
		Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_contain);
		if(fragment==null){
			fragment=createFragment();
			fragmentManager.beginTransaction().add(R.id.fragment_contain,fragment).commit();
		}
	}
}
