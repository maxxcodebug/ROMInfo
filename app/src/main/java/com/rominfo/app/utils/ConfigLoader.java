package com.rominfo.app.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.rominfo.app.data.RomConfig;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigLoader {

    private static RomConfig cachedConfig = null;

    public static RomConfig load(Context context) {
        if (cachedConfig != null) return cachedConfig;
        try {
            InputStream is = context.getAssets().open("rom_config.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            cachedConfig = new Gson().fromJson(json, RomConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            cachedConfig = new RomConfig();
        }
        return cachedConfig;
    }

    // Read live system props as fallback
    public static String getSystemProp(String key, String fallback) {
        try {
            Process p = Runtime.getRuntime().exec("getprop " + key);
            byte[] buf = p.getInputStream().readAllBytes();
            String val = new String(buf).trim();
            return val.isEmpty() ? fallback : val;
        } catch (Exception e) {
            return fallback;
        }
    }
}
