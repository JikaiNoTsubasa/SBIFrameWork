package fr.triedge.fwk.utils;

public class STimer {
    private long startTime;
    private long stopTime;

    public STimer start(){
        this.startTime = System.currentTimeMillis();
        return this;
    }

    public STimer stop(){
        this.stopTime = System.currentTimeMillis();
        return this;
    }

    public long diffMilli(){
        return this.stopTime - this.startTime;
    }

    public double diffSeconds(){
        return diffMilli()/1000.0;
    }

    public String diffSecondsString(){
        return String.valueOf(diffSeconds());
    }

    /**
     * Stops the timer and calculates the difference. Then start it again for next call.
     * @return The difference as a String with 's' appended. e.g. '1.234s'
     */
    public String flag(){
        this.stop();
        String res = diffSecondsString()+"s";
        this.start();
        return res;
    }
}
