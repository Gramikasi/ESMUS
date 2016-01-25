package com.example.alejandro.esmus.vista;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

/**
 * Created by Miren on 11/01/2016.
 */

    public class AudioPlayer   implements MediaController.MediaPlayerControl, MediaPlayer.OnPreparedListener {

        private View view;//vista
        private MediaPlayer player;//reproduccion etc
        private MediaController controller; //botones

        public AudioPlayer(View view){
            this.view = view;
            player = new MediaPlayer();
            player.setOnPreparedListener(this);
            controller = new MediaController(view.getContext()){
                @Override
                public boolean dispatchKeyEvent(KeyEvent event){
                    //cuando el usuaruio pulse el boton de detras acabamos la reproduccion
                    //en la clase original no se hace esto seguiria el media reproducciondose back
                    if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        release();
                        //  onExit.run();
                    }
                    return super.dispatchKeyEvent(event);

                }
            };
        }

        public void release(){
            if(player != null){
                player.stop();
                player.release();
                player = null;
            }
        }
        public void setAudioUri(Uri uri) throws IOException {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(view.getContext(), uri);
            player.prepare();
            player.start();
        }

        @Override
        public void onPrepared(MediaPlayer np){
            controller.setMediaPlayer(this);
            controller.setAnchorView(view);
          //  controller.show(0);// nunca se ocultan los botones
        }
        @Override
        public void start(){

            player.start();
        }

        @Override
        public void pause()
        {
            player.pause();
        }


        @Override
        public int getCurrentPosition() {

            return player.getCurrentPosition();
        }
        @Override
        public int getDuration() {

            return player.getDuration();
        }

        @Override
        public void seekTo(int pos) {
            player.seekTo(pos);
        }


        @Override
        public boolean canSeekForward() {

            return false;
        }

        @Override
        public int getAudioSessionId() {

            return player.getAudioSessionId();
        }


        @Override
        public boolean isPlaying() {

            return player.isPlaying();
        }

        @Override
        public int getBufferPercentage() {

            return (player.getCurrentPosition()*100)/player.getDuration();
        }

        @Override
        public boolean canPause() {

            return true;
        }

        @Override
        public boolean canSeekBackward() {

            return false;
        }


}
