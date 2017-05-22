package com.fabricetas.util;

/**
 * Created by belman on 09/04/2017.
 */
public class UtilString {

    public static Boolean isNullOrEmpty(String string) {
        if(string.isEmpty() || string == null)
            return true;
        return false;
    }

}
