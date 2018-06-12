package dev.DualKeys.SIF.sound;

import java.util.HashMap;

public class AudioMap {

    private static HashMap<String, String> audioTable;
    private SoundThread soundThread;

    public AudioMap() {
        audioTable = new HashMap<String, String>();
        audioTable.put("mainTheme", "/res/Music/maintheme.wav");
        soundThread = new SoundThread();
        soundThread.run();
    }

    public static HashMap<String, String> getAudioTable() {
        return audioTable;
    }
}