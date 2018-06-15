package dev.DualKeys.SIF.sound;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundThread extends Thread {

    private static Clip soundClip;
    private AudioInputStream audioStream;

    public SoundThread() {
        try {
            audioStream = AudioSystem.getAudioInputStream(getClass().getResource(AudioMap.getAudioTable().get("mainTheme")));
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void updateSound(String soundName) {
        try {
            audioStream = AudioSystem.getAudioInputStream(getClass().getResource(AudioMap.getAudioTable().get(soundName)));
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void setSound(float volume, int loop) {
        try {
            soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
        soundClip.start();
        //soundClip.setLoopPoints(0, 203);
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }

    public void () {
        soundClip.stop();
    }

    public void run() {
        setSound(-20f, MAX_PRIORITY);
    }
}