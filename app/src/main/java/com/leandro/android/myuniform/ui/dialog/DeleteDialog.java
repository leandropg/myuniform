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

package com.leandro.android.myuniform.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.database.IDatabase;
import com.leandro.android.myuniform.database.UniformsSQLiteHelper;
import com.leandro.android.myuniform.dto.Uniform;

/**
 * Delete Dialog
 */
public class DeleteDialog extends DialogFragment {

    /**
     * Uniform Entity
     */
    private Uniform uniform;

    /**
     * On Create Dialog
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder;
        TextView dlgTitulo;
        TextView dlgMensaje;

        // Obtain Builder Alert Dialog
        builder = new AlertDialog.Builder(getActivity());

        // Obtain Inflater Layout Instance
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Obtiene la vista del XML
        View viewMiDialogo = inflater.inflate(R.layout.dialog_delete, null);

        // Set Components Values
        dlgTitulo = (TextView) viewMiDialogo.findViewById(R.id.dlgTitle);
        dlgTitulo.setText(getString(R.string.dialog_delete_title));
        dlgMensaje = (TextView) viewMiDialogo.findViewById(R.id.dlgMessage);
        dlgMensaje.setText(getString(R.string.dialog_delete_message));

        // Set View to Dialog
        builder.setView(viewMiDialogo);

        // Set behavior Positive Button
        builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                StringBuilder sb;
                SQLiteDatabase db;
                UniformsSQLiteHelper sqLiteHelper;

                // Create Database Connection
                sqLiteHelper = new UniformsSQLiteHelper(getActivity().getBaseContext(), IDatabase.DATABASE_NAME, null, IDatabase.DATABASE_VERSION);

                // Get Writable Database Reference
                db = sqLiteHelper.getWritableDatabase();

                // Create Where Condition
                sb = new StringBuilder();
                sb.append("id = ");
                sb.append(String.valueOf(uniform.getId()));

                // Delete Uniform
                db.delete(IDatabase.TABLE_UNIFORMS_NAME, sb.toString(), null);

                // Close Database Instance
                db.close();
            }
        });

        // Set behavior Cancel Button
        builder.setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                // Close Dialog
                dialog.cancel();
            }
        });

        // Return Dialog
        return builder.create();
    }

    /**
     * Set Uniform Data
     * @param pUniform Uniform
     */
    public void setUniform(Uniform pUniform) {

        uniform = pUniform;
    }
}