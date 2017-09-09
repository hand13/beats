package com.hand13.beats;

import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hand13.beats.model.BeatBox;
import com.hand13.beats.model.Sound;

import java.util.List;

/**
 * Created by hd110 on 2017/7/11.
 */

public class BeatBoxFragment extends Fragment {
	private BeatBox beatBox;
	public static BeatBoxFragment newInstance(){
		return new BeatBoxFragment();
	}
	@Override
	public void onCreate(Bundle args){
		super.onCreate(args);
		setRetainInstance(true);
		beatBox=new BeatBox(getActivity());
	}
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle args){
		View v=layoutInflater.inflate(R.layout.fragment_beat_box,viewGroup,false);
		RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.fragment_beat_box_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
		recyclerView.setAdapter(new SoundAdapter(beatBox.getSounds()));
		return v;
	}
	private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		private Button button;
		private Sound sound;
		public SoundHolder(LayoutInflater layoutInflater,ViewGroup viewGroup){
			super(layoutInflater.inflate(R.layout.list_item_sound,viewGroup,false));
			button=(Button)itemView.findViewById(R.id.list_item_sound_button);
			button.setOnClickListener(this);
		}
		public void bindSound(Sound sound){
			this.sound=sound;
			button.setText(sound.getName());
		}
		@Override
		public void onClick(View v){
			beatBox.play(sound);
		}
	}
	private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
		private List<Sound> sounds;
		public SoundAdapter(List<Sound> sounds){
			this.sounds=sounds;
		}
		@Override
		public SoundHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
			LayoutInflater inflater=LayoutInflater.from(getActivity());
			return new SoundHolder(inflater,viewGroup);
		}
		@Override
		public void onBindViewHolder(SoundHolder soundHolder,int position){
			Sound sound=sounds.get(position);
			soundHolder.bindSound(sound);
		}
		@Override
		public int getItemCount(){
			return sounds.size();
		}
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		beatBox.release();
	}
}
