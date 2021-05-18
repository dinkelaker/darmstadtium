# The darmstadtium test framework

Darmstadtium is a test framework for mobile, web, and desktop from a single codebase.

Under the hood, Darmstadtium uses Appium as an open-source for cross-platform test automation tool for native, hybrid, and mobile web and desktop apps. 
We support simulators (iOS), emulators (Android), and real devices (iOS, Android, Windows, Mac).

In Darmstadtium, domain-specific languages are developed and composed using the [TigersEye](https://github.com/dinkelaker/tigerseye) framework.
By reflecting languages' syntax, semantics and pragmatics as objects once described in [Dinkelaker 2011](https://tuprints.ulb.tu-darmstadt.de/2813/1/dinkelaker-thesis-final.pdf), Darmstadtium follows the devide and conquer princinple to design language fragements for the various facettes of single codebase for test scripts.




## Supported Platforms

Darmstadtium supports the same platforms as Appium, like iOS, Android, and Windows.




## Why Darmstadtium?

Darmstadtium allows you to write test code for apps on various application platforms from one single codebase. 


## Getting Started

Install node.js, Android studio, and Appium.

Compile project:

    > gradlew clean build
    
Staring Android emulator:

    > adb start-server

List available devices for emulation (or if none present install an AVD with Android Studio):

    > emulator -list-avds

Select one name for the list, e.g. 'Pixel_2_API_29', 
and connect the emulator to the virtual device via its name:

    > emulator -avd Pixel_2_API_29 -netdelay none -netspeed full

Start Appium server:

    > appium

Then execute DarmstadtiumTest.groovy:

    > gradlew test
   
## IDE

Recommended IDE is IntelliJ's IDEA Community Edition.

The project support also Eclipse, but the gradle and groovy plugins on that IDE platform are very unstable. 

Compile project:

    > gradlew cleanEclipse eclipse

## Publish project 

To install the component group for customizations of the core library to be used in dependent projects execute:

    > gradlew clean publishToMavenLocal
