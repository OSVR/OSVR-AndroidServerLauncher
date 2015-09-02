package com.osvr.serverlauncher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainActivity extends Activity {

    private Process process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String[] args = {"/data/data/com.osvr.serverlauncher/files/bin/osvr_server"};
            //String[] args = {"/system/bin/ls", "/data/local/tmp/osvr/bin"};
            ProcessBuilder processBuilder = new ProcessBuilder(args)
                    .directory(new File("/data/data/com.osvr.serverlauncher/files/bin"));

            Map<String, String> environment = processBuilder.environment();
            environment.put("LD_LIBRARY_PATH", "/data/data/com.osvr.serverlauncher/files/lib");

            process = processBuilder.start();

        } catch(IOException ex) {
            Log.e("com.OSVR", "Error when starting process: " + ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != process) {
            process.destroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
