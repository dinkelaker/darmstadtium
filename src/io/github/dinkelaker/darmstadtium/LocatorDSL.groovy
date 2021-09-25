package io.github.dinkelaker.darmstadtium

import de.tud.stg.tigerseye.DSL
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement

class LocatorDSL implements DSL {

    private AndroidDriver<AndroidElement> driverForLocating;

    LocatorDSL(AndroidDriver<AndroidElement> driver) {
        driverForLocating = driver;
    }

    // *** ID ***
    MobileElement byId(String id) {
        return (MobileElement) driverForLocating.findElementById(id);
    }

    List<MobileElement> listById(String id) {
        return (List<MobileElement>) driverForLocating.findElementsById(id);
    }

    // *** Accessibility ID ***
    MobileElement byAccessibilityId(String id) {
        return (MobileElement) driverForLocating.findElementByAccessibilityId(id);
    }

    MobileElement byAccessId(String id) {
        return byAccessibilityId(id);
    }

    List<MobileElement> listByAccessibilityId(String id) {
        return (List<MobileElement>) driverForLocating.findElementsByAccessibilityId(id);
    }

    List<MobileElement> listByAccessId(String id) {
        return listByAccessibilityId(id);
    }

    // *** XPath ***
    MobileElement byXPath(String xpath) {
        return (MobileElement) driverForLocating.findElementByXPath(xpath);
    }

    List<MobileElement> listByXPath(String xpath) {
        return (List<MobileElement>) driverForLocating.findElementsByXPath(xpath);
    }

    MobileElement byText(String text) {
        return byXPath("//android.widget.TextView[@text='$text']")
    }

}
