package com.immortal.awitd.wearme;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	   private static MediaPlayer mp = null;

	   /** Stop old song and start new one */
	   public static void play(Context context, int resource) {
	      stop(context);
	         mp = MediaPlayer.create(context, resource);
	         mp.setLooping(true);
	         mp.setVolume(0.5f, 0.5f);
	         mp.start();
	   }
	   /** Stop the music */
	   public static void stop(Context context) { 
	      if (mp != null ) {
	         mp.stop();
	         mp.release();
	         mp = null;
	      }
	   }
	   public static void pause(Context context) { 
		      if (mp != null ) {
		         mp.pause();
		      }
		   }
	   
	   public static boolean isPlay(){
		   	return mp.isPlaying();
	   }
}
