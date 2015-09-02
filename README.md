# OSVR-AndroidServerLauncher

Instructions:

Open the project in Android Studio.

Run the assembleDebug gradle task.

Install \app\build\outputs\apk/\app-debug.apk with adb (adb install app-debug.apk)

Copy the OSVR server binaries for OSVR-Android to /data/local/tmp/osvr:
adb push C:\path\to\OSVR-Android-Build-Install /data/local/tmp/osvr

Run the following commands:
adb shell
cd /data/local/tmp/osvr/bin
chmod 775 osvr_server
run-as com.osvr.serverlauncher
mkdir files
cp -R /data/local/tmp/osvr/* files

Now launch the app, wait a bit, then switch out and launch your OSVR app.
