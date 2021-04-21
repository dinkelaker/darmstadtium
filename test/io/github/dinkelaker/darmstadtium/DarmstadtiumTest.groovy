package io.github.dinkelaker.darmstadtium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse

import org.junit.Test;

import java.net.MalformedURLException;
import static java.util.concurrent.TimeUnit.SECONDS;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.offset.ElementOption.element;


import io.github.dinkelaker.darmstadtium.Darmstadtium;

class DarmstadtiumTest {
    
    static DEFAULT_TIMEOUT_S = 2;

    @Test
    void testSomeKeywordIsWorking() {
        Darmstadtium classUnderTest = new Darmstadtium();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someKeyword());
    }

    @Test
    void testEvalIsWorking() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            assertTrue(someKeyword())
        }
    }

    @Test
    void testTimeoutKeyword() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            timeout(DEFAULT_TIMEOUT_S, SECONDS)
            assertTrue(someKeyword())
        }
    }
    
    @Test
    void testWithinTimeoutIsOK() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            //set timeout to 1 sec
            timeout DEFAULT_TIMEOUT_S, SECONDS
            
            def flag = false;
            parallelize {
                sleepS DEFAULT_TIMEOUT_S * 2
                flag = true
            }
            
            sleepS DEFAULT_TIMEOUT_S * 3
            assertTrue(flag)
        }
    }
    
    @Test
    void testExceedingTimeoutFails() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            //set timeout to 10 secs
            timeout DEFAULT_TIMEOUT_S, SECONDS
            
            def flag = false;
            parallelize {
                sleepS DEFAULT_TIMEOUT_S * 3
                flag = true
            }
            
            sleepS DEFAULT_TIMEOUT_S * 2
            assertFalse(flag)
        }
    }
    
    @Test
    void testTimeout() {
        Darmstadtium interpreter = new Darmstadtium();
        interpreter.eval {
            timeout(DEFAULT_TIMEOUT_S, SECONDS)
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