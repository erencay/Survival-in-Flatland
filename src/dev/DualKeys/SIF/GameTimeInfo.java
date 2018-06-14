package dev.DualKeys.SIF;

public class GameTimeInfo {
    public int Days;
    public short Hour;
    public short Minute;
    public boolean IsPaused;
    private short sunDown;
    private short sunUp;


    public long elapsedTime(GameTimeInfo newest, GameTimeInfo oldest) {
        return newest.elapsedMinutes() - oldest.elapsedMinutes();
    }

    public GameTimeInfo(int days, short hour, short minute, short sunDown, short sunUp, boolean isPaused) {
        IsPaused = isPaused;
        Days = days;
        Hour = hour;
        Minute = minute;
        this.sunDown = sunDown;
        this.sunUp = sunUp;
    }

    public boolean isNightTime() {
        return Hour >= sunDown || Hour < sunUp;
    }

    public boolean isDayTime() {
        return !isNightTime();
    }

    public long elapsedMinutes() {
        return (((Days / 24) + Hour) * 60) + Minute;
    }
}
