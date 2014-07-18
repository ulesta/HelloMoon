package com.atleusdigital.android.hellomoon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import android.view.SurfaceHolder;

public class Player implements OnPreparedListener {
	
	/* m: member variable */
	private MediaPlayer mPlayer;
	
	private boolean isPaused;
	private boolean isPlaying;
	
	/* keep exactly one MediaPlayer around and keep it around only
	 * as long as it is playing something 
	 */
	public void stop() {
		if (mPlayer != null) {
			isPlaying = false;
			mPlayer.reset();
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void pause() {
		if( mPlayer != null ) {
			if (isPaused) {
				mPlayer.start();
				isPaused = false;
				isPlaying = true;
			} else {
				mPlayer.pause();
				isPaused = true;
				isPlaying = false;
			}
		} 
	}
	
	@SuppressLint("NewApi")
	public void play(Context c, SurfaceHolder sh, int seek) {
		if (!isPaused) {
			stop();
			mPlayer = MediaPlayer.create(c, R.raw.pikachu);
			mPlayer.setDisplay(sh);
			mPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
			mPlayer.seekTo(seek);
			mPlayer.setOnCompletionListener(new mCompletionListener());
		}
		mPlayer.start();
		isPlaying = true;
		isPaused = false;
	}
	
	@SuppressLint("NewApi")
	public void displayPaused(Context c, SurfaceHolder sh, int seek) {
		if (!isPaused) {
			stop();
			mPlayer = MediaPlayer.create(c, R.raw.pikachu);
			mPlayer.setDisplay(sh);
			mPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
			mPlayer.seekTo(seek);
			mPlayer.setOnCompletionListener(new mCompletionListener());
		}
		isPlaying = false;
		isPaused = true;
	}
	
	
	public class mCompletionListener implements OnCompletionListener {

		@Override
		public void onCompletion(MediaPlayer mp) {
			stop();
		}
		
	}
	
	@Override
	public void onPrepared(MediaPlayer arg0) {
		mPlayer.start();
	}
	
	public int getSeekPosition() {
		if (mPlayer != null) {
			return mPlayer.getCurrentPosition();
		}
		return 0;
	}
	
	public Boolean getIsPlaying() {
		return this.isPlaying;
	}
	
}
