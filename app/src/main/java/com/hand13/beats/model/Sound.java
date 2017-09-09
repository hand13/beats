package com.hand13.beats.model;

import android.content.Intent;

/**
 * Created by hd110 on 2017/7/11.
 */

public class Sound {
	private Integer soundId;
	private String assetPath;
	private String name;
	public Sound(String assetPath){
		this.assetPath=assetPath;
		String[] components=assetPath.split("/");
		String fileName=components[components.length-1];
		name=fileName.replace(".wav","");
	}
	public String getAssetPath(){
		return assetPath;
	}
	public String getName(){
		return name;
	}

	public Integer getSoundId() {
		return soundId;
	}

	public void setSoundId(Integer soundId) {
		this.soundId = soundId;
	}
}
