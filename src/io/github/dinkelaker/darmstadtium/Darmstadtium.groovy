package io.github.dinkelaker.darmstadtium

import de.tud.stg.tigerseye.LinearizingCombiner;

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
import de.tud.stg.tigerseye.LinearizingCombiner;


class Darmstadtium extends Interpreter implements DSL {

    AndroidDriver<AndroidElement> driver;

    Darmstadtium() {
        driver = createDriver();
        bodyDelegate = new LinearizingCombiner(
                [
                        //AutoWiringDSL must be first, such that its propertyMissing is used by LinearizingCombiner
                        new AutoWiringDSL(driver),
                        this,
                        new TimeDSL(driver),
                        new ConcurrencyDSL(),
                        new GesturesDSL(driver),
                ] as Set<DSL>);
        if (driver == null) throw new NullPointerException("Appium driver not found. Make sure that the Appium service is running.")
    }

    private static AndroidDriver<AndroidElement> createDriver() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver;

        File appDir = new File("test/resources");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
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

    Object methodMissing(String name, Object args) {
        return bodyDelegate.methodMissing(name, args)
    }

    Object propertyMissing(String name) {
        return bodyDelegate.propertyMissing(name)
    }
}
