package dk.dtu.philipsclockradio;

import android.os.Handler;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    private int AL1;
    private int AL2 = 3;

    StateStandby(Date time){
        mTime = time;
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);
            } finally {
                mHandler.postDelayed(mSetTime, 60000);
            }
        }
    };

    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleepTimer());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        if (context.isAmChosen()) {
            context.setState(new StateRadioAM());
        } else {
            context.setState(new StateRadioFM());
        }
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

        AL1++;

        switch (AL1) {
            case 1:
                context.ui.turnOffLED(AL1 +1);
                context.ui.turnOnLED(AL1);
                break;
            case 2:
                context.ui.turnOffLED(AL1 -1);
                context.ui.turnOnLED(AL1);
                break;
            case 3:
                context.ui.turnOffLED(1);
                context.ui.turnOffLED(2);
                AL1 =0;
                break;
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        AL2++;

        switch (AL2) {
            case 4:
                context.ui.turnOffLED(AL2 +1);
                context.ui.turnOnLED(AL2);
                break;
            case 5:
                context.ui.turnOffLED(AL2 -1);
                context.ui.turnOnLED(AL2);
                break;
            case 6:
                context.ui.turnOffLED(4);
                context.ui.turnOffLED(5);
                AL2 =3;
                break;
        }
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateSetAlarm());
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateSetAlarm());
    }
}
