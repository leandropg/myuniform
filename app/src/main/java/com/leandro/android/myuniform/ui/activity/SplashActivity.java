/**
 * My Uniform
 * Leandro Perez Guatibonza / leandropg AT gmail DOT com
 * Copyright (C) 2016
 *
 * This file is part of My Uniform
 *
 * My Uniform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * My Uniform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with My Uniform. If not, see <http://www.gnu.org/licenses/>.
 */

package com.leandro.android.myuniform.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.leandro.android.myuniform.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash Activity
 */
public class SplashActivity extends Activity {

    /**
     * Logger Instance
     */
    public final static String logger = SplashActivity.class.getSimpleName();

    /**
     * Delay Milliseconds
     */
    public final static int DELAY_MS = 4000;

    /**
     * Label Version
     */
    private TextView lblVersion;

    /**
     * OnCreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set Content View
        setContentView(R.layout.activity_splash);

        try {

            // Get Instance Label Version
            lblVersion = (TextView) findViewById(R.id.lblVersion);

            // Get App Version
            PackageInfo packageInfo = getBaseContext().getPackageManager().getPackageInfo(getBaseContext().getPackageName(), 0);

            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.activity_splash_version));
            sb.append(" ");
            sb.append(packageInfo.versionName);

            // Set App Version Name
            lblVersion.setText(sb.toString());

            // Wait N seconds and Launch Main Activity
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                /**
                 * Run Method
                 */
                @Override
                public void run() {

                    // Start Main Activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Finish Activity
                    finish();

                }
            }, DELAY_MS);

        } catch (PackageManager.NameNotFoundException e) {

            Log.e(logger, "Error obtain Version Application", e);
        }
    }
}