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
	
	/* keep exactly one MediaPlayer around and keep it around only
	 * as long as it is playing something 
	 */
	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void pause() {
		if( mPlayer != null ) {
			mPlayer.pause();
			isPaused = true;
		} 
	}
	
	@SuppressLint("NewApi")
	public void play(Context c, SurfaceHolder sh) {
		if (!isPaused) {
			stop();
			mPlayer = MediaPlayer.create(c, R.raw.pikachu);
			//mPlayer.setOnPreparedListener(this);
			mPlayer.setDisplay(sh);
			mPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
			mPlayer.setOnCompletionListener(new mCompletionListener());
		}
		mPlayer.start();
		isPaused = false;
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
	
}
