package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class StateSetAlarm extends StateAdapter {

    Date alarmTime;
    String displayText;


    @Override
    public void onEnterState(ContextClockradio context) {


        if(alarmTime == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 16, 00);
            alarmTime = date.getTime();
        }
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(alarmTime.toString().substring(11,16));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {


        //Gets current timestamp (Date)
        alarmTime.setTime(alarmTime.getTime() + 3600000);

        displayText = alarmTime.toString().substring(11,16);
        context.ui.setDisplayText(displayText);

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

        //Gets current timestamp (Date)
        alarmTime.setTime(alarmTime.getTime() + 60000);

        displayText = alarmTime.toString().substring(11,16);
        context.ui.setDisplayText(displayText);
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.ui.turnOffLED(1);
        context.ui.turnOnLED(2);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.ui.turnOffLED(4);
        context.ui.turnOnLED(5);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        System.out.println("original time: " + context.getTime().toString());
        System.out.println("ALARM: " + alarmTime.toString());
    }
}
