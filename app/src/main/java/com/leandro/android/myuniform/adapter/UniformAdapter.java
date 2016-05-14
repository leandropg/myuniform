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

package com.leandro.android.myuniform.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.dto.Uniform;

import java.util.ArrayList;

/**
 * Uniforms Adapter
 */
public class UniformAdapter extends ArrayAdapter<Uniform> {

    /**
     * Uniforms
     */
    private ArrayList<Uniform> lstUniforms;

    /**
     * Activity
     */
    private Activity activity;

    /**
     * Constructor
     */
    public UniformAdapter(Fragment pFragment, ArrayList<Uniform> pLstUniforms) {

        super(pFragment.getActivity(), R.layout.item_uniform, pLstUniforms);

        // Store Instances
        activity = pFragment.getActivity();
        lstUniforms = pLstUniforms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int numberDay;
        LayoutInflater inflater;
        String firstLetter;
        TextView lblDayName;
        Uniform uniform;
        View itemUniform;

        // Inflate Item Uniform
        inflater = LayoutInflater.from(getContext());
        itemUniform = inflater.inflate(R.layout.item_uniform, null);

        // Get Components Item
        lblDayName = (TextView) itemUniform.findViewById(R.id.lblDayName);

        // Get Uniform Information
        uniform = lstUniforms.get(position);

        // Get Fist Letter Day Name
        firstLetter = uniform.getDayName().substring(0,1);

        // Set Firt Letter Day
        lblDayName.setText(firstLetter);

        // Set Color Day
        switch (uniform.getId()) {

            // Monday
            case 0:
                lblDayName.setBackgroundResource(R.drawable.border_circle_monday);
                break;

            // Tuesday
            case 1:
                lblDayName.setBackgroundResource(R.drawable.border_circle_tuesday);
                break;

            // Wednesday
            case 2:
                lblDayName.setBackgroundResource(R.drawable.border_circle_wednesday);
                break;

            // Thursday
            case 3:
                lblDayName.setBackgroundResource(R.drawable.border_circle_thursday);
                break;

            // Friday
            case 4:
                lblDayName.setBackgroundResource(R.drawable.border_circle_friday);
                break;

            // Saturday
            case 5:
                lblDayName.setBackgroundResource(R.drawable.border_circle_saturday);
                break;

            // Sunday
            case 6:
                lblDayName.setBackgroundResource(R.drawable.border_circle_sunday);
                break;
        }
        return itemUniform;
    }
}