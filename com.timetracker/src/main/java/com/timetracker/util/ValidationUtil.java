package com.timetracker.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ValidationUtil {
    private static final String ERROR_MAP_NAME = "ERRORS";

    private ValidationUtil(){}

    public static void validate(boolean isValid, String fieldName, String errorMsgKey, HttpServletRequest request) {
        Object errorAttribute = request.getSession().getAttribute(ERROR_MAP_NAME);
        if (errorAttribute == null) {
            errorAttribute = new HashMap<String, String>();
        }
        Map<String, String> errorMap = (Map<String, String>) errorAttribute;
        if (isValid) {
            String errorMsg = errorMap.get(fieldName);
            if (errorMsgKey.equals(errorMsg)) {
                errorMap.remove(fieldName);
            }
        } else {
            errorMap.put(fieldName, errorMsgKey);
        }
        request.getSession().setAttribute(ERROR_MAP_NAME, errorMap);
    }

    public static boolean isNotEmptyValidation(String value, String fieldName, String errorMsgKey, HttpServletRequest request) {
        boolean isValid = value != null && !value.isEmpty();
        validate(isValid, fieldName, errorMsgKey, request);
        return isValid;
    }

    public static boolean isNotTooShortValidation(String value, int size, String fieldName, String errorMsgKey, HttpServletRequest request) {
        boolean isValid = value.length() >= size;
        validate(isValid, fieldName, errorMsgKey, request);
        return isValid;
    }
}
