package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class StateSetAlarm extends StateAdapter {

    private Date alarm;
    private String displayText;


    @Override
    public void onEnterState(ContextClockradio context) {


        if(alarm == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 16, 00);
            alarm = date.getTime();
        }
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText(alarm.toString().substring(11,16));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {


        //Gets current timestamp (Date)
        alarm.setTime(alarm.getTime() + 3600000);

        displayText = alarm.toString().substring(11,16);
        context.ui.setDisplayText(displayText);

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

        //Gets current timestamp (Date)
        alarm.setTime(alarm.getTime() + 60000);

        displayText = alarm.toString().substring(11,16);
        context.ui.setDisplayText(displayText);
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.ui.turnOffLED(1);
        context.ui.turnOnLED(2);
        context.setAlarmOne(alarm);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.ui.turnOffLED(4);
        context.ui.turnOnLED(5);
        context.setAlarmTwo(alarm);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        System.out.println("\noriginal time: " + context.getTime().toString());

        if (context.getAlarmOne() != null) {
            System.out.println("AL1: " + context.getAlarmOne().toString());
        }
        if (context.getAlarmTwo() != null) {
            System.out.println("AL2: " + context.getAlarmTwo().toString());
        }
    }
}
