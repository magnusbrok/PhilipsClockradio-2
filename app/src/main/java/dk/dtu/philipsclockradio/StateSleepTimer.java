package dk.dtu.philipsclockradio;

public class StateSleepTimer extends StateAdapter {

    int[] sleepIntervals = new int[]{120, 90, 60, 30, 15};
    int timesClicked = 1;


    @Override
    public void onEnterState(ContextClockradio context) {

        context.ui.setDisplayText(sleepIntervals[0]+"");
        context.ui.turnOnLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

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
}
