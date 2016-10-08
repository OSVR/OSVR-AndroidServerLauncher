package com.osvr.serverlauncher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.osvr.android.utils.OSVRFileExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.channels.FileChannel;
import java.util.Map;

public class MainActivity extends Activity {

    private Process process;


    private static final int RQS_GET_JSON = 1;

    final String serverBin = "/data/data/com.osvr.serverlauncher/files/bin/osvr_server";
    final String serverDir = "/data/data/com.osvr.serverlauncher/files/bin";

    Button btnDefaultLaunch, btnJsonLaunch;
    TextView textView;
    ScrollView scrollView;

    protected void doChmod() {
        String[] args = {"chmod", "775", serverBin};
        ProcessBuilder processBuilder = new ProcessBuilder(args)
                .directory(new File(serverDir));
        try {
            Process chmodProcess = processBuilder.start();
            chmodProcess.waitFor();
        } catch (IOException ex) {
            Log.e("com.OSVR", "Error when starting chmod: " + ex.getMessage());
        } catch (InterruptedException ex) {
            Log.e("com.OSVR", "Error when starting process: " + ex.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.hello_world);
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        btnDefaultLaunch = (Button) findViewById(R.id.defaultlaunch);
        btnJsonLaunch = (Button) findViewById(R.id.jsonlaunch);

        btnDefaultLaunch.setOnClickListener(btnDefaultLaunchOnClickListener);
        btnJsonLaunch.setOnClickListener(btnJsonLaunchOnClickListener);

        OSVRFileExtractor.extractFiles(this);
        doChmod();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != process) {
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

    private void startServer(String[] myArgs) {
        ServerTask mTask = new ServerTask();
        mTask.execute(myArgs);

    }

    OnClickListener btnDefaultLaunchOnClickListener =
            new OnClickListener() {

                @Override
                public void onClick(View v) {
                    String[] args = {};
                    startServer(args);
                }

            };


    OnClickListener btnJsonLaunchOnClickListener =
            new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("*/*");


                    startActivityForResult(intent, RQS_GET_JSON);
                }

            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {


            if (requestCode == RQS_GET_JSON) {

                Uri content_describer = data.getData();
                String src = content_describer.getPath();
                Log.d("com.OSVR", src);

                File source = new File(src);


                String appRoot = OSVRFileExtractor.getAppRoot(this);


                File outputFile = new File(appRoot, "bin/temp.json");
                outputFile.getParentFile().mkdirs();

                Log.d("com.OSVR", outputFile.getAbsolutePath());

                //copy files
                try {
                    FileInputStream in = (FileInputStream) getContentResolver().openInputStream(data.getData());
                    FileOutputStream out = new FileOutputStream(outputFile);
                    FileChannel inChannel = in.getChannel();
                    FileChannel outChannel = out.getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    in.close();
                    out.close();

                    String[] args = {"temp.json"};
                    startServer(args);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    class ServerTask extends AsyncTask<String, Void, Void> {
        PipedOutputStream mPOut;
        PipedInputStream mPIn;
        LineNumberReader mReader;

        @Override
        protected void onPreExecute() {
            mPOut = new PipedOutputStream();
            try {
                mPIn = new PipedInputStream(mPOut);
                mReader = new LineNumberReader(new InputStreamReader(mPIn));
            } catch (IOException e) {
                cancel(true);
            }

        }

        public void stop() {
            Process p = process;
            if (p != null) {
                p.destroy();
            }
            cancel(true);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String[] defaultArgs = {serverBin};

                int len1 = defaultArgs.length;
                int len2 = params.length;
                String[] args = new String[len1 + len2];
                System.arraycopy(defaultArgs, 0, args, 0, len1);
                System.arraycopy(params, 0, args, len1, len2);

                ProcessBuilder processBuilder = new ProcessBuilder(args)
                        .directory(new File(serverDir));

                Map<String, String> environment = processBuilder.environment();
                environment.put("LD_LIBRARY_PATH", "/data/data/com.osvr.serverlauncher/files/lib");

                process = processBuilder.start();

                try {
                    InputStream in = process.getInputStream();
                    OutputStream out = process.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int count;

                    // in -> buffer -> mPOut -> mReader -> 1 line of ping information to parse
                    while ((count = in.read(buffer)) != -1) {
                        mPOut.write(buffer, 0, count);
                        publishProgress();
                    }
                    out.close();
                    in.close();
                    mPOut.close();
                    mPIn.close();
                } finally {
                    process.destroy();
                    process = null;
                }
            } catch (IOException e) {
                Log.e("com.OSVR", "Error when starting process: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            try {
                // Is a line ready to read from the "ping" command?
                while (mReader.ready()) {
                    // This just displays the output, you should typically parse it I guess.
                    textView.setText(textView.getText() + "\n" + mReader.readLine());

                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            // This method works but animates the scrolling
                            // which looks weird on first load
                            // scroll_view.fullScroll(View.FOCUS_DOWN);

                            // This method works even better because there are no animations.
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                }
            } catch (IOException t) {
            }
        }
    }
}
