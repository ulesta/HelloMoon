package com.atleusdigital.android.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {

	private final String TAG = "HelloMoonFragment";
	private Button mPlayButton;
	private Button mStopButton;
	private Button mPauseButton;
	private SurfaceHolder mSurfaceHolder;
	
	private Player mPlayer = new Player();
	
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
				mPlayer.play(getActivity(), mSurfaceHolder);
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
