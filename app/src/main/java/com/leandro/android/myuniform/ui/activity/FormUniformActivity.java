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
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.database.IDatabase;
import com.leandro.android.myuniform.database.UniformsSQLiteHelper;
import com.leandro.android.myuniform.dto.Uniform;

import java.io.ByteArrayOutputStream;

public class FormUniformActivity extends Activity {

    /**
     * Form Action
     */
    private String action;

    /**
     * Uniform Data
     */
    private Uniform uniform;

    /**
     * Spinner Uniform Day
     */
    private Spinner spnUniformDay;

    /**
     * TextView Uniform Name
     */
    private TextView txtUniformName;

    /**
     * ImageView Uniform Photo
     */
    private ImageView imgUniformPhoto;

    /**
     * Bitmap
     */
    private Bitmap bitmap;

    /**
     * On Create Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        Button btnOk;
        Button btnCancel;
        Intent intent;

        // Set XML View
        setContentView(R.layout.activity_form_uniform);

        // Get Components Instances
        spnUniformDay = (Spinner) findViewById(R.id.spnUniformDay);
        txtUniformName = (TextView) findViewById(R.id.txtUniformName);
        imgUniformPhoto = (ImageView) findViewById(R.id.imgUniformPhoto);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // Set Adapter Day Names
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this, R.array.values_spinner, R.layout.spinner_day_name);
        spnUniformDay.setAdapter(adp);

        // Register Button Ok Listener
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int numberDay;
                byte [] imgUniformByteArray;
                ContentValues contentValues;
                SQLiteDatabase db;
                String nameDay;
                String nameUniform;
                StringBuilder sb;
                UniformsSQLiteHelper sqLiteHelper;

                // Create Database Connection
                sqLiteHelper = new UniformsSQLiteHelper(getBaseContext(), IDatabase.DATABASE_NAME, null, IDatabase.DATABASE_VERSION);

                // Get Writable Database Reference
                db = sqLiteHelper.getWritableDatabase();

                // Get Day and Uniform Information
                numberDay = spnUniformDay.getSelectedItemPosition();
                nameDay = spnUniformDay.getSelectedItem().toString();
                nameUniform = txtUniformName.getText().toString();

                // Convert Image to Byte Array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imgUniformByteArray = stream.toByteArray();

                // Check Action
                if(action.equals("add")) {

                    // Set Content Values to Add
                    contentValues = new ContentValues();
                    contentValues.put("id", numberDay);
                    contentValues.put("uniformDay", nameDay);
                    contentValues.put("uniformName", nameUniform);
                    contentValues.put("uniformPhoto", imgUniformByteArray);

                    // Add New Register
                    db.insert(IDatabase.TABLE_UNIFORMS_NAME, null, contentValues);

                } else if (action.equals("update")) {

                    // Set Content Values to Update
                    contentValues = new ContentValues();
                    contentValues.put("id", numberDay);
                    contentValues.put("uniformDay", nameDay);
                    contentValues.put("uniformName", nameUniform);
                    contentValues.put("uniformPhoto", imgUniformByteArray);

                    // Create Where Condition
                    sb = new StringBuilder();
                    sb.append("id = ");
                    sb.append(String.valueOf(uniform.getId()));

                    // Update Register
                    db.update(IDatabase.TABLE_UNIFORMS_NAME, contentValues, sb.toString(), null);
                }

                // Close Database Instance
                db.close();

                // Close Activity
                finish();
            }
        });

        // Register Button Cancel Listener
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Close Activity
                finish();
            }
        });

        // Register Click over Photo
        imgUniformPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Start Camera Image Capture
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        // Get Intent
        intent = getIntent();

        // Check Intent not is null
        if(intent != null) {

            // Get Action Intent
            action = getIntent().getAction();

            // Get Bundle Intent
            bundle = getIntent().getExtras();

            // Check Bundle not is null
            if (bundle != null) {

                // Get Uniform Data
                uniform = (Uniform) bundle.getSerializable("uniform");

                // Check Uniform not is null
                if(uniform != null) {

                    // Set Spinner Day Name
                    spnUniformDay.setSelection(uniform.getId());

                    // Set Uniform Name
                    txtUniformName.setText(uniform.getUniformName());

                    // Load Image
                    byte [] foto = uniform.getUniformPhoto();

                    // Check tha Image not is null
                    if(foto != null) {

                        // Store Bitmap
                        bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);

                        // Set Photo in ImageView
                        imgUniformPhoto.setImageBitmap(bitmap);
                    }
                }
            }

            // Enable/Disable Uniform Day
            if(action.equals("update")) {

                spnUniformDay.setEnabled(false);

            } else {

                spnUniformDay.setEnabled(true);
            }
        }
    }

    /**
     * Process Activity Result
     * @param requestCode Request Code
     * @param resultCode Result Code
     * @param data Intent Data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Check if user take photo
        if(data != null) {

            // Get Bitmap from Camera
            bitmap = (Bitmap) data.getExtras().get("data");

            // Set Photo in ImageView
            imgUniformPhoto.setImageBitmap(bitmap);
        }
    }
}