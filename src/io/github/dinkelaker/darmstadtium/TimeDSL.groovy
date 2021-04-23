package io.github.dinkelaker.darmstadtium;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import de.tud.stg.tigerseye.DSL;

import io.github.dinkelaker.darmstadtium.testenv.Timeout;

public class TimeDSL implements DSL {

    private AndroidDriver<AndroidElement> driverForTimeout;
    
    TimeDSL(AndroidDriver<AndroidElement> driver) {
        this.driverForTimeout = driver;
    }

    Timeout timeout(long value, TimeUnit unit) {
        Timeout timeout = new Timeout(value: value, unit: unit)
        driverForTimeout.manage().timeouts().implicitlyWait(value, unit);
        return timeout;
    } 

    Timeout timeoutS(long value) {
        timeout(value, SECONDS);
    }
        
    void sleepMs(long milliseconds) {
        sleep(milliseconds);
    }
    
    void sleepS(long seconds) {
        sleep(seconds * 1000);
    }
   
}
