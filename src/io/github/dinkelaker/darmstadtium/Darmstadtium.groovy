package io.github.dinkelaker.darmstadtium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import de.tud.stg.tigerseye.Interpreter;
import de.tud.stg.tigerseye.DSL;

import static java.util.concurrent.TimeUnit.SECONDS;
import io.github.dinkelaker.darmstadtium.testenv.Timeout

class Darmstadtium extends Interpreter implements DSL {
    
    AndroidDriver<AndroidElement> driver;
    
    Darmstadtium() {
        this.driver = createDriver();
    }

    private static  AndroidDriver<AndroidElement> createDriver() throws MalformedURLException {
        AndroidDriver<AndroidElement>  driver;

        File appDir = new File("test/resources");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        return driver;
    }

    boolean someKeyword() {
        return true;
    }
    
    Timeout timeout(long value, TimeUnit unit) {
        Timeout timeout = new Timeout(value: value, unit: unit)
        driver.manage().timeouts().implicitlyWait(value, unit);
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
    
    void parallelize(Closure logic) {
        Thread.start(logic);
    }

}
