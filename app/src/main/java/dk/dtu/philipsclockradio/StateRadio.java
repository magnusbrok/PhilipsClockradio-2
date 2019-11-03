package dk.dtu.philipsclockradio;

public class StateRadio extends StateAdapter {

    public double amFrequency = 90.8;
    public double fmFrequency = 30.7;
    public boolean amChosen;


    public boolean isAmChosen() {
        return amChosen;
    }

    public void setAmChosen(boolean amChosen) {
        this.amChosen = amChosen;
    }



    // FM Gladsaxe (København)	90.8 MHz / 60 kW	93.9 MHz / 60 kW	96.5 MHz / 60 kW


    @Override
    public void onEnterState(ContextClockradio context) {
        amChosen = true;
        context.ui.setDisplayText(amFrequency+"");
    }

    /**
     * Turns the frequancy down.
     * @param context
     */
    @Override
    public void onClick_Hour(ContextClockradio context) {

        if (isAmChosen()) {
            amFrequency -= 0.1;
            context.ui.setDisplayText(amFrequency + "");

        } else {
            fmFrequency -= 0.1;
            context.ui.setDisplayText(fmFrequency + "");
        }

    }


    /**
     * Turns the frequancy up
     * @param context
     */
    @Override
    public void onClick_Min(ContextClockradio context) {

        if (isAmChosen()) {
            amFrequency += 0.1;
            context.ui.setDisplayText(amFrequency + "");
        } else {
            fmFrequency += 0.1;
            context.ui.setDisplayText(fmFrequency + "");
        }
    }


    @Override
    public void onClick_Power(ContextClockradio context) {

        if (isAmChosen()) {
            System.out.println("fm choseen");
            setAmChosen(false);
            context.ui.setDisplayText(fmFrequency+"");

        } else  {
            System.out.println("am chosen");
            setAmChosen(true);
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

