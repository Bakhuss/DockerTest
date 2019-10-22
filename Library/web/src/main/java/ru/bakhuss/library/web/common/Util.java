package ru.bakhuss.library.web.common;

import ru.bakhuss.library.web.error.ResponseErrorException;

public class Util {
    public static Long parseLongFromString(String str) {
        Long longFromString;
        try {
            longFromString = Long.parseLong(str);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + str + ")");
        }
        return longFromString;
    }

    public static Integer parseIntegerFromString(String str) {
        Integer integerFromString;
        try {
            integerFromString = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + str + ")");
        }
        return integerFromString;
    }
}
