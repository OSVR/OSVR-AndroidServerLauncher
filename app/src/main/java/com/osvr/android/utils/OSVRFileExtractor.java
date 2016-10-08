/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2015 Sensics, Inc. and contributors.
 * Original author: Koushik Dutta for the androidmono project. Modified from original.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.osvr.android.utils;

import android.content.ContextWrapper;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OSVRFileExtractor {
    private final static String ZIP_FILTER = "assets";
    private final static int BUFSIZE = 100000;
    private final static String LOGTAG = "OSVRFileExtractor";

    private static void Log(String string) {
        Log.v(LOGTAG, string);
    }

    private static void copyStreams(InputStream is, FileOutputStream fos) {
        BufferedOutputStream os = null;
        try {
            byte data[] = new byte[BUFSIZE];
            int count;
            os = new BufferedOutputStream(fos, BUFSIZE);
            while ((count = is.read(data, 0, BUFSIZE)) != -1) {
                os.write(data, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            Log("Exception while copying: " + e);
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e2) {
                Log("Exception while closing the stream: " + e2);
            }
        }
    }

    public static String getAppRoot(ContextWrapper context) {
        return "/data/data/" + context.getPackageName() + "/files";
    }

    /**
     * Extract osvr files from the apk zip file (from assets dir) to the installed directory, if not
     * already extracted. Call this prior to instantiating the server.
     * @param context Typically an Activity instance.
     */
    public static void extractFiles(ContextWrapper context) {
        extractAssets(context, false);
    }

    /**
     * Extract assets from the apk zip file to the installed app directory.
     * Copies plugins, the config file, and displays.
     * @param context Typically an Activity instance.
     * @param worldReadable deprecated. Always use false.
     */
    private static void extractAssets(ContextWrapper context, boolean worldReadable) {
        try {
            Runtime runtime = Runtime.getRuntime();
            String appRoot = getAppRoot(context);
            File zipFile = new File(context.getPackageCodePath());
            long zipLastModified = zipFile.lastModified();
            ZipFile zip = new ZipFile(context.getPackageCodePath());
            Vector<ZipEntry> files = osvrFilesFromZip(zip);
            int zipFilterLength = ZIP_FILTER.length();

            Enumeration entries = files.elements();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String path = entry.getName().substring(zipFilterLength);
                File outputFile = new File(appRoot, path);
                outputFile.getParentFile().mkdirs();

                if (outputFile.exists() && entry.getSize() == outputFile.length() && zipLastModified < outputFile.lastModified()) {
                    Log(outputFile.getName() + " already extracted.");
                } else {
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    Log("Copied " + entry + " to " + appRoot + "/" + path);
                    copyStreams(zip.getInputStream(entry), fos);
                    String curPath = outputFile.getAbsolutePath();
                    if (worldReadable) {
                        do {
                            runtime.exec("chmod 755 " + curPath);
                            curPath = new File(curPath).getParent();
                        } while (!curPath.equals(appRoot));
                    }
                }
            }
        } catch (IOException e) {
            Log("Error: " + e.getMessage());
        }
    }

    /**
     * Check if a file is actually an OSVR file. For the server, just
     * copy everything.
     * @param filePath The full path of the file.
     * @return true, if the path is to an OSVR file, false otherwise
     */
    private static boolean isOSVRFile(String filePath) {
        return true;
    }

    /**
     * Returns a list of all OSVR files from the apk zip file.
     */
    private static Vector<ZipEntry> osvrFilesFromZip(ZipFile zip) {
        Vector<ZipEntry> list = new Vector<ZipEntry>();
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String entryName = entry.getName();
            if(entryName.startsWith(ZIP_FILTER) && isOSVRFile(entryName)) {
                list.add(entry);
            }
        }
        return list;
    }
}