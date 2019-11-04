package dk.dtu.philipsclockradio;

import java.util.Arrays;

public class StateSaveAMPreset extends StateAdapter {

    private int presetNumber = 1;
    private double frequency;

    StateSaveAMPreset(double frequency){
        this.frequency = frequency;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(presetNumber+"");
    }

    @Override
    public void onExitState(ContextClockradio context) {

        context.ui.turnOffTextBlink();
        context.amPresets[presetNumber-1] = frequency;

        // Just used to test if the presets were saved correctly.
        System.out.println("AM PRESETS:" + Arrays.toString(context.amPresets));
        System.out.println("FM PRESETS:" + Arrays.toString(context.fmPresets));
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StateRadioAM());
    }

    /**
     * Used to switch between each presetnumber.
     * @param context
     */
    @Override
    public void onClick_Preset(ContextClockradio context) {
        if (presetNumber <= 20) {
            presetNumber++;
        }
        if (presetNumber == 21) {
            presetNumber = 1;
        }
        context.ui.setDisplayText(presetNumber+"");
    }
}
