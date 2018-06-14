package dev.DualKeys.SIF;

public class GameTimeManager {
    private int days;
    private short hours, minutes;
    private long lastTime;
    private boolean isPaused;

    private final short SUNDOWNHOUR = 20;
    private final short SUNUPHOUR = 5;

    public GameTimeManager(int days, short hours, short minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.lastTime = System.currentTimeMillis();
    }

    public void update() {
        if (isPaused) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - lastTime;

        if (elapsed >= 1000)
        {
            lastTime = currentTime;
            minutes += (short) (elapsed / 1000);
        }

        if (minutes >= 60)
        {
            hours++;
            minutes = (short) (minutes % 60);
        }

        if (hours >= 24)
        {
            days++;
            hours = (short) (days % 24);
        }
    }

    public void pauseGameTime()
    {
        isPaused = true;
    }

    public void unPauseGameTime()
    {
        this.lastTime = System.currentTimeMillis();

        isPaused = false;
    }
    public GameTimeInfo getGameTimeInfo() {
        return new GameTimeInfo(days, hours, minutes, SUNDOWNHOUR, SUNUPHOUR, isPaused);
    }
}
