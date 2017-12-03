package sa.gov.hajj.meetings.helpers;


import android.content.Context;
import android.media.MediaPlayer;


public class MediaHelperHelper {
    private MediaPlayer mediaPlayer;

    public MediaHelperHelper(Context context, int path) {
        mediaPlayer = MediaPlayer.create(context, path);
        mediaPlayer.setLooping(true);
    }


    public void stop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        mediaPlayer.start();
    }
}
