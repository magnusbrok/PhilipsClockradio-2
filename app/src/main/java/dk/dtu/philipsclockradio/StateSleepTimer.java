package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

public class StateSleepTimer extends StateAdapter {

    private CountDownTimer idleTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 5000;

    private int[] sleepIntervals = new int[]{120, 90, 60, 30, 15};
    private int timesClicked = 1;

    @Override
    public void onEnterState(ContextClockradio context) {
        startTimer(context);
        context.ui.setDisplayText(sleepIntervals[0]+"");
        context.ui.turnOnLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        stopTimer();
        startTimer(context);

        if (timesClicked <= 4) {

            context.ui.turnOnLED(3);
            context.ui.setDisplayText(sleepIntervals[timesClicked]+"");
            timesClicked++;
        } else {
            context.ui.setDisplayText("OFF");
            context.ui.turnOffLED(3);
            timesClicked=0;
        }
    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    public void startTimer(final ContextClockradio context) {
        idleTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                context.setState(new StateStandby(context.getTime()));
            }
        }.start();
    }

    public void stopTimer() {
        idleTimer.cancel();
    }
}
