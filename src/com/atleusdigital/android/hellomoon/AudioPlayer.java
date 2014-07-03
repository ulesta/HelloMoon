package com.atleusdigital.android.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class AudioPlayer {
	
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
		mPlayer.pause();
		isPaused = true;
	}
	
	public void play(Context c) {
		if (!isPaused) {
			stop();
			mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
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
	
}
