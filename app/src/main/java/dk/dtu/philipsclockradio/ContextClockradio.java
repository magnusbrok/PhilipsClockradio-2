package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

//
public class ContextClockradio {
    private State currentState;
    private Date mTime;
    private Date alarmOne;

    public  int A1Type;

    private Date alarmTwo;
    public int A2Type;
    private String mDisplayText;
    private double amFrequency = 90.8; // a random default radio
    private double fmFrequency = 30.7; // a random default radio
    public double[] amPresets = new double[20];
    public double[] fmPresets = new double[20];
    private boolean amChosen = false; // Per default the Radio starts on FM connection.

    public boolean isClockRunning = false;
    public static MainUI ui;

    public ContextClockradio(MainUI context){
        ui = context;

        //Sætter tiden til 12.00, hvis tiden ikke er sat endnu
        if(mTime == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 12, 00);
            mTime = date.getTime();
        }

        //Når app'en starter, så går vi ind i Standby State
        currentState = new StateStandby(mTime);
        currentState.onEnterState(this);
    }

    //setState er når vi skifter State

    void setState(final State newState) {
        currentState.onExitState(this);
        currentState = newState;
        currentState.onEnterState(this);
        System.out.println("Current state: "+ newState.getClass().getSimpleName());
    }
    //Opdaterer kontekst time state og UI

    void setTime(Date time){
        mTime = time;
        if(currentState.getClass().getSimpleName().equals("StateStandby")){
            updateDisplayTime();
        }
    }
    void updateDisplayTime(){
        mDisplayText = mTime.toString().substring(11,16);
        ui.setDisplayText(mDisplayText);
    }

    public Date getTime(){
        return mTime;
    }

    public double getAmFrequency() {
        return amFrequency;
    }

    public void setAmFrequency(double amFrequency) {
        this.amFrequency = amFrequency;
    }

    public double getFmFrequency() {
        return fmFrequency;
    }

    public void setFmFrequency(double fmFrequency) {
        this.fmFrequency = fmFrequency;
    }

    public Date getAlarmOne() {
        return alarmOne;
    }

    public void setAlarmOne(Date alarmOne) {
        this.alarmOne = alarmOne;
    }

    public Date getAlarmTwo() {
        return alarmTwo;
    }

    public void setAlarmTwo(Date alarmTwo) {
        this.alarmTwo = alarmTwo;
    }

    public int getA1Type() {
        return A1Type;
    }

    public void setA1Type(int a1Type) {
        A1Type = a1Type;
    }

    public int getA2Type() {
        return A2Type;
    }

    public void setA2Type(int a2Type) {
        A2Type = a2Type;
    }

    public boolean isAmChosen() {
        return amChosen;
    }

    public void setAmChosen(boolean amChosen) {
        this.amChosen = amChosen;
    }




    //Disse metoder bliver kaldt fra UI tråden
    public void onClick_Hour() {
        currentState.onClick_Hour(this);
    }

    public void onClick_Min() {
        currentState.onClick_Min(this);
    }

    public void onClick_Preset() {
        currentState.onClick_Preset(this);
    }

    public void onClick_Power() {
        currentState.onClick_Power(this);
    }

    public void onClick_Sleep() {
        currentState.onClick_Sleep(this);
    }

    public void onClick_AL1() {
        currentState.onClick_AL1(this);
    }

    public void onClick_AL2() {
        currentState.onClick_AL2(this);
    }

    public void onClick_Snooze() {
        currentState.onClick_Snooze(this);
    }

    public void onLongClick_Hour(){
        currentState.onLongClick_Hour(this);
    }

    public void onLongClick_Min(){
        currentState.onLongClick_Min(this);
    }

    public void onLongClick_Preset(){
        currentState.onLongClick_Preset(this);
    }

    public void onLongClick_Power(){
        currentState.onLongClick_Power(this);
    }

    public void onLongClick_Sleep(){
        currentState.onLongClick_Sleep(this);
    }

    public void onLongClick_AL1(){
        currentState.onLongClick_AL1(this);
    }

    public void onLongClick_AL2(){
        currentState.onLongClick_AL2(this);
    }

    public void onLongClick_Snooze(){
        currentState.onLongClick_Snooze(this);
    }
}