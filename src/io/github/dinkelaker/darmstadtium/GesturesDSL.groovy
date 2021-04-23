package io.github.dinkelaker.darmstadtium

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import de.tud.stg.tigerseye.DSL
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement

class GesturesDSL implements DSL {

    private AndroidDriver<AndroidElement> driverForGestures;
    private TouchAction touchAction;

    GesturesDSL(AndroidDriver<AndroidElement> driver) {
        driverForGestures = driver;
        touchAction = new TouchAction(driver);
    }

    void click(elementToClick) {
        elementToClick.click();
    }

    void tap(elementToTab) {
        touchAction.tap(tapOptions().withElement(element(elementToTab))).perform();
    }

    void longPress(elementToPress) {
        touchAction.longPress(longPressOptions().withElement(element(elementToPress)).
                withDuration(ofSeconds(2))).release().perform();
    }
}
