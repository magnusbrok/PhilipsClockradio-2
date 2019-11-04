package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

public class StateSleepTimer extends StateAdapter {

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 5000;

    int[] sleepIntervals = new int[]{120, 90, 60, 30, 15};
    int timesClicked = 1;


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
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                context.setState(new StateStandby(context.getTime()));
            }
        }.start();
        mTimerRunning = true;
    }

    public void stopTimer() {
        mCountDownTimer.cancel();
    }
}
