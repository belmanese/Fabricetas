package com.fabricetas.util;

/**
 * Created by belman on 09/04/2017.
 */
public class UtilNumber {

    public static Boolean isNullOrZero(Integer number){
        if( number != null && number != 0 )
            return false;
        return true;
    }

}
