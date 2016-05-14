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

package com.leandro.android.myuniform.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.adapter.UniformAdapter;
import com.leandro.android.myuniform.database.IDatabase;
import com.leandro.android.myuniform.database.UniformsSQLiteHelper;
import com.leandro.android.myuniform.dto.Uniform;
import com.leandro.android.myuniform.listeners.UniformListener;
import com.leandro.android.myuniform.ui.activity.FormUniformActivity;
import com.leandro.android.myuniform.ui.dialog.DeleteDialog;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

/**
 * Fragment Main Activity
 */
public class FragmentUniformDay extends Fragment {

    /**
     * List Uniforms
     */
    private ArrayList<Uniform> lstUniform;

    /**
     * Uniform Listener
     */
    private UniformListener uniformListener;

    /**
     * ListView Uniform
     */
    private TwoWayView lvwUniform;

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate XML Fragment
        return inflater.inflate(R.layout.fragment_uniform_day, container, false);
    }

    /**
     * On Activity Created
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load Uniforms
        loadUniforms();
    }

    /**
     * Load Uniforms Information
     */
    public void loadUniforms() {

        Cursor cursor;
        SQLiteDatabase db;
        UniformsSQLiteHelper sqLiteHelper;

        // Init List Uniform
        lstUniform = new ArrayList<Uniform>();

        // Create Database Connection
        sqLiteHelper = new UniformsSQLiteHelper(this.getActivity(), IDatabase.DATABASE_NAME, null, IDatabase.DATABASE_VERSION);

        // Get Readable Database Reference
        db = sqLiteHelper.getReadableDatabase();

        // Check if database not is null
        if(db != null) {

            // Execute Select Table Uniforms
            cursor = db.rawQuery(IDatabase.TABLE_UNIFORMS_SELECT, null);

            // Check if data exists
            if(cursor.moveToFirst()) {

                do {

                    // Create Uniform Object
                    Uniform u = new Uniform(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3));

                    // Add Uniform to List
                    lstUniform.add(u);
                }
                while(cursor.moveToNext());
            }

            // Close cursor
            cursor.close();

            // Close Database
            db.close();
        }

        // Creo mi adaptador
        UniformAdapter adp = new UniformAdapter(this, lstUniform);

        // Get List View Instance
        lvwUniform = (TwoWayView) getView().findViewById(R.id.lvwUniform);

        // Set Adapter
        lvwUniform.setAdapter(adp);

        // Register Context Menu
        registerForContextMenu(lvwUniform);

        // Configura el evento de click sobre un elemento
        lvwUniform.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (uniformListener != null) {

                    uniformListener.onSelectUniform((Uniform) lvwUniform.getAdapter().getItem(position));
                }
            }
        });
    }

    /**
     * Set Uniform Listener
     * @param pUniformListener
     */
    public void setUniformListener(UniformListener pUniformListener) {

        uniformListener = pUniformListener;
    }

    /**
     * Llama el metodo cuando regresa al Fragment
     */
    @Override
    public void onResume() {
        super.onResume();

        // Carga la informacion de los contactos
        loadUniforms();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_contacto_item_ctx, menu);
    }

    /**
     * Menu Contextual
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int position;
        Bundle bundle;
        Uniform uniform;
        Intent intent;
        StringBuilder sb;
        AdapterView.AdapterContextMenuInfo menuListView;

        // Get Information Menu List View
        menuListView = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        // Get Uniform Selected
        uniform = lstUniform.get(menuListView.position);

        switch (item.getItemId()){

            // Option Modify
            case R.id.menuCtxUpdate:

                // Create Intent
                intent = new Intent(getActivity(), FormUniformActivity.class);

                // Set Action
                intent.setAction("update");

                // Create Bundle
                bundle = new Bundle();
                bundle.putSerializable("uniform", uniform);

                // Set Bundle into Intent
                intent.putExtras(bundle);

                // Start Activity
                startActivity(intent);
                return true;

            // Option Delete
            case R.id.menuCtxDelete:

                // Create Confirm Dialog
                DeleteDialog dlgSeleccion = new DeleteDialog();

                // Set Uniform to Delete
                dlgSeleccion.setUniform(uniform);

                // Set Fragment
                dlgSeleccion.setTargetFragment(this, 0);

                // Show Confirm Dialog
                dlgSeleccion.show(getFragmentManager(), "deleteDialog");
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}