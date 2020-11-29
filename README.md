# WWW Service Design
A course project for Aalto University course Design of WWW Services. 
---

### 1. Pre-requisites
Install Android Studio 4.1.1 or later.

### 2. Setup

To set up the project:
1. Create a new directory called "Education".
1. <strong>Git clone</strong> files to the local directory "Education".
2. Choose  <strong>"Import Project (Gradle, Eclipse ADT, etc.)</strong>
3. Press   <strong>"Run App"</strong>. This should start the Gradle build and the virtual emulator
4. Gradle builder should produce the following message on build output

BUILD SUCCESSFUL in xx s
xx actionable tasks: xx executed

Build Analyzer results available

5. Launching 'app' on device should result in a success message. You should now be able to see either the mobile phone front app screen or the Education Application Login. 

Note! Sometimes the emulator behaves in bizarre ways and might require pressing the emulators "power" button on/off.


### Android Studio Emulator Errors

The Android Studio Emulator is dependent on virtualization configurations on your computer. This app was run on Windows 10 Pro 64-bit Operating System. 

In case of emulator not starting up, but build succeeding, please check the following threads: 
https://stackoverflow.com/questions/10022580/android-emulator-shows-nothing-except-black-screen-and-adb-devices-shows-device
https://stackoverflow.com/questions/16717064/emulator-in-android-studio-doesnt-start

Please also check if your Intel HAXM installation is updated and Hardware Acceleration enabled. 
https://docs.microsoft.com/en-us/xamarin/android/get-started/installation/android-emulator/troubleshooting?pivots=windows
https://github.com/intel/haxm/wiki/Installation-Instructions-on-Windows



## DEMO ## 

There is a platform that lets you demonstrate your native mobile build on browser. The free trial has severe limits (concurrent users 1 and 100 session minutes / month) but it works. 

The demo can be found here: https://appetize.io/app/8jmmhzktaq6ut51uejtvtnkf64?device=pixel4xl&scale=75&orientation=portrait&osVersion=11.0


