# OSVR-AndroidServerLauncher

Instructions:

Open the project in Android Studio.  (If it complains that it *Failed to find target which has string 'android-22'* or another version, use the provided link to **Open Android SDK Manager** and check the box for the missing version and then press the OK button to download and install it, then restart the project.)  (If it complains about *failed to find Build Tools revision 23.0.0* or another version, use the **Install Build Tools 23.0.0 and sync project** link to get them.)

Run the assembleDebug gradle task.  (If you cannot find assembleDebug, select *Build/Make Project* from the menu, and then *Build/Build APK*.  This places app-debug.apk into the *app/build/outputs/apk* directory.)

Install /app/build/outputs/apk/app-debug.apk with adb:  (The adb program will be in your sdk directory, whose location you can
find by opening *Tools/Android/SDK Manager* and then looking in the *Android SDK Location:* box.  For user taylorr on one computer, it was at *C:\Users\taylorr\AppData\Local\Android\sdk\platform-tools*.  You probably want to add this to your path and then restart Android Studio.)
```
adb install app-debug.apk
```

Copy the OSVR server binaries for OSVR-Android to /data/local/tmp/osvr
```
adb push C:\path\to\OSVR-Android-Build-Install /data/local/tmp/osvr
```

Run the following commands:
```
adb shell
cd /data/local/tmp/osvr/bin
chmod 775 osvr_server
run-as com.osvr.serverlauncher
mkdir files
cp -R /data/local/tmp/osvr/* files
```

Now launch the app, wait a bit, then switch out and launch your OSVR app.
