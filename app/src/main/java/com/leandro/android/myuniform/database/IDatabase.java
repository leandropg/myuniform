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

package com.leandro.android.myuniform.database;

/**
 * Uniforms Database Definitions
 */
public interface IDatabase {

    /**
     * Database Name
     */
    public static final String DATABASE_NAME = "UniformDB";

    /**
     * Database Version
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Table Uniforms
     */
    public static final String TABLE_UNIFORMS_NAME = "Uniforms";

    /**
     * SQL Create Uniforms Table
     */
    public static final String TABLE_UNIFORMS_CREATE = "CREATE TABLE " + TABLE_UNIFORMS_NAME + " (id INTEGER PRIMARY KEY, uniformDay TEXT NOT NULL, uniformName TEXT NOT NULL, uniformPhoto BLOB NULL)";

    /**
     * SQL Drop Uniforms Table
     */
    public static final String TABLE_UNIFORMS_DROP = "DROP TABLE IF EXISTS " + TABLE_UNIFORMS_NAME;

    /**
     * SQL Select Uniforms Table
     */
    public static final String TABLE_UNIFORMS_SELECT = "SELECT * FROM " + TABLE_UNIFORMS_NAME;
}