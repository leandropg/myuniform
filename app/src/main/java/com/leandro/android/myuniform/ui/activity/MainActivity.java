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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.dto.Uniform;
import com.leandro.android.myuniform.listeners.UniformListener;
import com.leandro.android.myuniform.ui.dialog.AboutDialog;
import com.leandro.android.myuniform.ui.fragments.FragmentUniformDay;
import com.leandro.android.myuniform.ui.fragments.FragmentUniformDetail;

/**
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * On Create Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Fragment Uniform Day Instance
        FragmentUniformDay fragmentUniformDay = (FragmentUniformDay) getFragmentManager().findFragmentById(R.id.frgUniformDay);
        fragmentUniformDay.setUniformListener(new UniformListener() {

            /**
             * On Select Uniform
             * @param uniform
             */
            @Override
            public void onSelectUniform(Uniform uniform) {

                // Get Fragment Uniform Detail
                FragmentUniformDetail fragmentUniformDetail = (FragmentUniformDetail) getFragmentManager().findFragmentById(R.id.frgUniformDetail);
                fragmentUniformDetail.showUniformDetail(uniform);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Set Menu Main Activity
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_add:

                // Create Intent
                Intent intent = new Intent(this, FormUniformActivity.class);

                // Set Action Add
                intent.setAction("add");

                // Start Form Uniform Activity
                startActivity(intent);
                return true;

            case R.id.menu_about:

                // Show Dialog About
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getSupportFragmentManager(), "aboutDialog");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}