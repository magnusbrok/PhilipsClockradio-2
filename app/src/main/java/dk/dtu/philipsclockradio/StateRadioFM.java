package dk.dtu.philipsclockradio;

public class StateRadioFM extends StateAdapter {


    private double fmFrequency;

    // FM Gladsaxe (København)	90.8 MHz / 60 kW	93.9 MHz / 60 kW	96.5 MHz / 60 kW

    @Override
    public void onEnterState(ContextClockradio context) {
        fmFrequency = context.getFmFrequency();

        context.ui.toggleRadioPlaying();
        context.ui.setDisplayText(fmFrequency +" FM");
    }

    /**
     * Turns the frequancy down.
     * @param context
     */
    @Override
    public void onClick_Hour(ContextClockradio context) {


            fmFrequency -= 0.1;
            context.ui.setDisplayText(fmFrequency + " FM");
    }

    /**
     * Turns the frequancy up
     * @param context
     */
    @Override
    public void onClick_Min(ContextClockradio context) {

            fmFrequency += 0.1;
            context.ui.setDisplayText(fmFrequency + " FM");
    }

    /**
     * Used to switch between FM and AM.
     * @param context
     */
    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateRadioAM());
    }

    /**
     * Goes back to Standby mode and collects the time from context.
     * @param context the context
     */
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.setFmFrequency(fmFrequency);
        context.setAmChosen(false);
        context.ui.toggleRadioPlaying();
    }

    /**
     * used to save stations.
     * @param context
     */
    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StateSaveFMPreset(fmFrequency));
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        //context.ui.turnOffTextBlink();
    }
}
