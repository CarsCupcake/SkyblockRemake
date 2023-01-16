package me.CarsCupcake.SkyblockRemake.utils;

public class Time {
    private int hour;
    private int minute;
    private String ampm = "am";

    public Time() {
        this.hour = 0;
        this.minute = 0;
    }

    public void incrementTime() {
        this.minute += 10;
        if (this.minute == 60) {
            this.hour++;
            this.minute = 0;
        }
        if (this.hour > 12) {
            this.hour = 1;
            this.minute = 0;
            if(this.ampm.equals("am")){
                this.ampm = "pm";
            }else{
                this.ampm = "am";
            }
        }
    }

    public String getTime() {
        String hourStr = String.format("%02d", this.hour);
        String minuteStr = String.format("%02d", this.minute);
        return hourStr + ":" + minuteStr + ampm;
    }

    public void startTime() {
        Time t = new Time();
        while (true) {
            t.incrementTime();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
