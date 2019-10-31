package dk.dtu.philipsclockradio;

public class StateSetAlarm extends StateAdapter {


    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.setDisplayText("HEJ med DIG");
    }
}
