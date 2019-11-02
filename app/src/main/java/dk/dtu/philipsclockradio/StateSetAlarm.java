package dk.dtu.philipsclockradio;

public class StateSetAlarm extends StateAdapter {

    public double amFrequency = 90.8;
    public double fmFrequency = 30.7;
    public boolean amChosen = true;



    // FM Gladsaxe (København)	90.8 MHz / 60 kW	93.9 MHz / 60 kW	96.5 MHz / 60 kW


    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText(amFrequency+"");
    }

    /**
     * Turns the frequancy down.
     * @param context
     */
    @Override
    public void onClick_Hour(ContextClockradio context) {

        if (amChosen = true) {
            amFrequency -= 0.1;
            context.ui.setDisplayText(amFrequency + "");
        } else {
            amFrequency -= 0.1;
            context.ui.setDisplayText(amFrequency + "");
        }

    }


    /**
     * Turns the frequancy up
     * @param context
     */
    @Override
    public void onClick_Min(ContextClockradio context) {

        if (amChosen = true) {
            amFrequency -= 0.1;
            context.ui.setDisplayText(amFrequency + "");
        } else {
            amFrequency -= 0.1;
            context.ui.setDisplayText(amFrequency + "");
        }
    }


    @Override
    public void onClick_Power(ContextClockradio context) {

        if (amChosen = true) {
            amChosen = false;
            context.ui.setDisplayText(fmFrequency+"");
        } else if (amChosen = false) {
            amChosen = true;
            context.ui.setDisplayText(amFrequency+"");
        }


    }

    /**
     * Goes back to Standby mode and collects the time from context.
     * @param context the context
     */
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }
}
