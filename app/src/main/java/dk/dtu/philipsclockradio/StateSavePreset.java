package dk.dtu.philipsclockradio;

import java.util.Arrays;

public class StateSavePreset extends StateAdapter {

    private int presetNumber = 1;
    private boolean amChosen;

    StateSavePreset(boolean amChosen){
        this.amChosen = amChosen;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(presetNumber+"");
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();

        if (amChosen) {
            context.amPresets[presetNumber - 1] = context.getAmFrequency();
        } else {
            context.fmPresets[presetNumber-1] = context.getFmFrequency();
        }




        // Just used to test if the presets were saved correctly.
        System.out.println("AM PRESETS:" + Arrays.toString(context.amPresets));
        System.out.println("FM PRESETS:" + Arrays.toString(context.fmPresets));
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StateRadio());
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
