package io.github.dinkelaker.darmstadtium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import de.tud.stg.tigerseye.DSL;
import de.tud.stg.tigerseye.Interpreter;
import de.tud.stg.tigerseye.BBCombiner;


class Darmstadtium extends Interpreter implements DSL {
    
    AndroidDriver<AndroidElement> driver;
    
    Darmstadtium() {
        driver = createDriver();
        bodyDelegate = new BBCombiner([this, 
            new TimeDSL(driver), 
            new ConcurrencyDSL(),
            new GesturesDSL(driver),
            new LocatorDSL(driver)] as Set);        
    }

    private static  AndroidDriver<AndroidElement> createDriver() throws MalformedURLException {
        AndroidDriver<AndroidElement>  driver;

        File appDir = new File("test/resources");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        
        def retryCount = 0;
        while (driver == null && retryCount < 3) {
            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (any) {
                retryCount++;
                Thread.sleep(1000); 
            }
        }
        return driver;
    }
    
    boolean someKeyword() {
        return true;
    }

}
