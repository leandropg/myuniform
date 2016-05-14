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

package com.leandro.android.myuniform.dto;

import java.io.Serializable;

/**
 * Uniform Entity
 */
public class Uniform implements Serializable {

    /**
     * Id Uniform
     */
    private int id;

    /**
     * Day Name
     */
    private String dayName;

    /**
     * Uniform Name
     */
    private String uniformName;

    /**
     * Uniform Photo
     */
    private byte [] uniformPhoto;

    /**
     * Default Constructor
     */
    public Uniform() {

    }

    /**
     * Constructor
     * @param pId Id
     * @param pDayName Day Name
     * @param pUniformName Uniform Name
     * @param pUniformPhoto Uniform Photo
     */
    public Uniform(int pId, String pDayName, String pUniformName, byte[] pUniformPhoto) {

        id = pId;
        dayName = pDayName;
        uniformName = pUniformName;
        uniformPhoto = pUniformPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getUniformName() {
        return uniformName;
    }

    public void setUniformName(String uniformName) {
        this.uniformName = uniformName;
    }

    public byte[] getUniformPhoto() {
        return uniformPhoto;
    }

    public void setUniformPhoto(byte[] uniformPhoto) {
        this.uniformPhoto = uniformPhoto;
    }
}
