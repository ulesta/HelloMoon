package com.atleusdigital.android.hellomoon;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {

	private final String TAG = "HelloMoonFragment";
	private final String STATE_SEEK = "seekPosition";
	private final String STATE_PLAYING = "isPlaying";
	
	private Button mPlayButton;
	private Button mStopButton;
	private Button mPauseButton;
	private SurfaceHolder mSurfaceHolder;
	
	private Player mPlayer = new Player();
	
	private int seekPos;
	private Boolean isPlaying;
	
	private Bundle mBundle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* this call allows the fragment to be retained through configuration changes such as screen orientation
		 * 
		 * Fragment is retained and passed onto the new activity, new fragment manager, the fragment's view is recreated.
		 * This way, no interruption occurs to MediaPlayer upon screen rotation
		 */
		//setRetainInstance(true);
		mBundle = savedInstanceState;
	}
	
	

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save fragment details
		savedInstanceState.putInt(STATE_SEEK, mPlayer.getSeekPosition());
		savedInstanceState.putBoolean(STATE_PLAYING, mPlayer.getIsPlaying());
		super.onSaveInstanceState(savedInstanceState);
	}



	// Override onCreateView to inflate view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_hello_moon, container, false);
		
		SurfaceView mSurfaceView = (SurfaceView) v.findViewById(R.id.hellomoon_surfaceView);
		mSurfaceHolder = mSurfaceView.getHolder();
		
		
		mPlayButton = (Button) v.findViewById(R.id.hellomoon_playButton);
		mStopButton = (Button) v.findViewById(R.id.hellomoon_stopButton);
		mPauseButton = (Button) v.findViewById(R.id.hellomoon_pauseButton);
		
		mOnClickListener mListener = new mOnClickListener();
		mPlayButton.setOnClickListener(mListener);
		mStopButton.setOnClickListener(mListener);
		mPauseButton.setOnClickListener(mListener);
		
		return v;
	}
	


	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		// Use a handler to delay the call of playing the video since, the fragment takes some time to inflate
		if (mBundle != null) {
			seekPos = mBundle.getInt(STATE_SEEK);
			isPlaying = mBundle.getBoolean(STATE_PLAYING);
		}
		if (seekPos > 0 && isPlaying) {
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mPlayer.play(getActivity(), mSurfaceHolder, seekPos);
				}
			}, 250);
		} else if (seekPos > 0) {
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mPlayer.displayPaused(getActivity(), mSurfaceHolder, seekPos);
				}
			}, 250);
		}
		super.onResume();
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
		mPlayer.stop();
	}
	
	public class mOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.hellomoon_playButton:
				mPlayer.play(getActivity(), mSurfaceHolder, 0);
				break;
			case R.id.hellomoon_stopButton:
				mPlayer.stop();
				break;
			case R.id.hellomoon_pauseButton:
				mPlayer.pause();
				break;
			default:
				break;
			}
		}
		
	}
	

}
