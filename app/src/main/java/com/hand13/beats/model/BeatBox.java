package com.hand13.beats.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hd110 on 2017/7/11.
 */

public class BeatBox {
	private static final int MAX_SOUNDS=5;
	private List<Sound> sounds=new ArrayList<>();
	private static final String TAG="BeatBox";
	private static final String SOUND_FOLDER="sample_sounds";
	private AssetManager assetManager;
	private SoundPool soundPool;
	public BeatBox(Context context){
		assetManager=context.getAssets();
		soundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
		loadSounds();
	}
	public void release(){
		soundPool.release();
	}
	public void play(Sound sound){
		Integer soundId=sound.getSoundId();
		if(soundId==null){
			return;
		}
		soundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
	}
	private void loadSounds(){
		String[] soundNames;
		try{
			soundNames=assetManager.list(SOUND_FOLDER);
			Log.i(TAG,"Found "+ soundNames.length+" sounds");
		}
		catch (IOException ioe){
			Log.e(TAG,"could not load asset");
			return;
		}
		for(String filename:soundNames){
			try {
				String assetPath = SOUND_FOLDER + "/" + filename;
				Sound sound = new Sound(assetPath);
				load(sound);
				sounds.add(sound);
			}
			catch (IOException ioe){
				Log.e(TAG,"COULD NOT LOAD SOUND "+filename,ioe);
			}
		}
	}
	private void load(Sound sound)throws IOException{
		AssetFileDescriptor af=assetManager.openFd(sound.getAssetPath());
		int soundId=soundPool.load(af,1);
		sound.setSoundId(soundId);
	}
	public List<Sound> getSounds(){
		return sounds;
	}
}
