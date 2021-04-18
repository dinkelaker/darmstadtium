package io.github.dinkelaker.darmstadtium;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.offset.ElementOption.element;


import io.github.dinkelaker.darmstadtium.Darmstadtium;

public class DarmstadtiumTest {

    @Test
    public void testSomeLibraryMethod() {
        Darmstadtium classUnderTest = new Darmstadtium();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testEval() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();

            //Tap
            TouchAction t = new TouchAction(driver);
            WebElement expandList=  driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
            t.tap(tapOptions().withElement(element(expandList))).perform();
            driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
            WebElement pn=  driver.findElementByXPath("//android.widget.TextView[@text='People Names']");

            t.longPress(longPressOptions().withElement(element(pn)).withDuration(ofSeconds(2))).release().perform();
            System.out.println(driver.findElementById("android:id/title").isDisplayed());
        }
    }
}