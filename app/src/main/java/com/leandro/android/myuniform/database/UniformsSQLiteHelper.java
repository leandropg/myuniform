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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Uniforms Database SQLite Helper
 */
public class UniformsSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Default Constructor
     * @param pContext Context
     * @param pDatabaseName Database Name
     * @param pCursorFactory CursorFactory
     * @param pDatabaseVersion Version Database
     */
    public UniformsSQLiteHelper(Context pContext, String pDatabaseName, SQLiteDatabase.CursorFactory pCursorFactory, int pDatabaseVersion) {
        super(pContext, pDatabaseName, pCursorFactory, pDatabaseVersion);
    }

    /**
     * Create Database
     * @param database Database Instance
     */
    @Override
    public void onCreate(SQLiteDatabase database) {

        // Drop Uniforms Table
        database.execSQL(IDatabase.TABLE_UNIFORMS_DROP);

        // Create Uniforms Table
        database.execSQL(IDatabase.TABLE_UNIFORMS_CREATE);
    }

    /**
     * Upgrade Database
     * @param database Database Instance
     * @param oldVersionDB Old Version Database
     * @param newVersionDB New Version Database
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersionDB, int newVersionDB) {

    }
}