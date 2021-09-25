package io.github.dinkelaker.darmstadtium

import de.tud.stg.tigerseye.DSL
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement

class AutoWiringDSL extends LocatorDSL implements DSL {

    AutoWiringDSL(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    private filter

    Object methodMissing(String name, Object args) {
        def elem = byXPath("//android.widget.TextView[@text='$name']")
        if (elem == null) {
            def nameTokens = args.findAll{it instanceof UnresolvedAutoWiringElement}.collect{UnresolvedAutoWiringElement unresolvedName -> unresolvedName.identifier}
            def completeTokens = [ name, *nameTokens ]
            def completeName = completeTokens.join(" ");
            elem = byXPath("//android.widget.TextView[@text='$completeName']")
            System.out.println("AutoWiring found xpath by composed name '$name'");
        }
        if (elem == null) {
            System.out.println("AutoWiring found unresolved fragment with name '$name'");
            elem = new UnresolvedAutoWiringElement(name);
        }
    }

     Object propertyMissing(String name) {
         System.out.println("AutoWiring tries to find elem by text '$name'");
         def elem = null;
         try {
             elem = byXPath("//android.widget.TextView[@text='$name']");
         } catch (Exception e) {
             System.out.println("AutoWiring could not find elem by text '$name'");
         }
         if (elem == null) {
             System.out.println("AutoWiring tries to find elem by id '$name'");
             elem = byId("android:id/$name")
         } else if (elem == null) {
             System.out.println("AutoWiring found unresolved fragment with name '$name'");
             elem = new UnresolvedAutoWiringElement(name);
         } else {
             System.out.println("AutoWiring found xpath by name '$name'");
         }
         return elem
    }

}
