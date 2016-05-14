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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leandro.android.myuniform.R;
import com.leandro.android.myuniform.dto.Uniform;

/**
 * Fragment Uniform Detail
 */
public class FragmentUniformDetail extends Fragment {

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_uniform_detail, container, false);
    }

    /**
     * Show Uniform Detail
     * @param pUniform Uniform
     */
    public void showUniformDetail(Uniform pUniform) {

        ImageView imgUniformPhoto;
        TextView lblUniformName;
        TextView lblUniformDay;

        // Get Components Instances
        imgUniformPhoto = (ImageView)getView().findViewById(R.id.imgUniformPhoto);
        lblUniformName = (TextView)getView().findViewById(R.id.lblUniformName);
        lblUniformDay = (TextView)getView().findViewById(R.id.lblUniformDay);

        // Set Uniform Name
        lblUniformName.setText(pUniform.getUniformName());
        lblUniformDay.setText(pUniform.getDayName());

        // Load Uniform Photo
        Bitmap bm = BitmapFactory.decodeByteArray(pUniform.getUniformPhoto(), 0, pUniform.getUniformPhoto().length);
        imgUniformPhoto.setImageBitmap(bm);
    }
}