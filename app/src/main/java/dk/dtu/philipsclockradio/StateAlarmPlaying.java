package dk.dtu.philipsclockradio;

import android.os.CountDownTimer;

import java.util.Date;

/**
 * Currently this state is never started. That is because i dont know how to do the check for when the
 * alarm goes off. There is also missing some indefication as to which type of alarm goes off ergo AL1 or AL2.
 */
public class StateAlarmPlaying extends StateAdapter {

    private CountDownTimer idleTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 5000;
    private boolean alarmRinging = true;
    private Date currentTime;
    private Date alarm;

    StateAlarmPlaying(Date alarmRinging) {
        this.alarm = alarmRinging;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        currentTime = context.getTime();
        context.ui.turnOnTextBlink();
    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {
        alarmRinging = false;
        startTimer(context);

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if (currentTime == context.getAlarmOne()) {
            context.setState(new StateStandby(context.getTime()));
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if (currentTime == context.getAlarmTwo()) {
            context.setState(new StateStandby(context.getTime()));
        }

    }

    @Override
    public void onExitState(ContextClockradio context) {
        alarmRinging = false;
        context.ui.turnOffTextBlink();
    }




    public void startTimer(final ContextClockradio context) {
        idleTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                alarmRinging = true;
                stopTimer();
            }

        }.start();
    }

    public void stopTimer() {
        idleTimer.cancel();
    }
}

