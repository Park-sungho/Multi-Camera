/**
 * This program is free software:
 * Added by park Sung-ho */
package com.multicamera.db;
public class DBConstants {
    public static final int VERSION = 1;
    
    public static final String DB_FILE_NAME = "MultiCamera.db";
    public static final String TABLE_NAME = "multi_cam";
    
    public class DBTable {
        public static final String IDX = "idx";
        public static final String TITLE = "title";
        public static final String REG_DATE = "reg_date";
        public static final String URL = "url";
        public static final String CONTENT_NAME = "content_name";
        public static final String MODIFY_DATE = "modify_date";
    }
}
