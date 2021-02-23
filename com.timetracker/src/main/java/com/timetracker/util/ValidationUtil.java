package com.timetracker.util;

import com.timetracker.db.entity.Category;
import com.timetracker.db.entity.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationUtil {
    private static final String ERROR_MAP_NAME = "ERRORS";

    private ValidationUtil(){}

    /**
     * General method validation.
     */
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

    /**
     * Fullness check.
     */
    public static boolean isNotEmptyValidation(String value, String fieldName, String errorMsgKey, HttpServletRequest request) {
        boolean isValid = value != null && !value.isEmpty();
        validate(isValid, fieldName, errorMsgKey, request);
        return isValid;
    }

    /**
     * This method that limits words to a certain size.
     */
    public static boolean isNotTooShortValidation(String value, int size, String fieldName, String errorMsgKey,
                                                  HttpServletRequest request) {
        boolean isValid = value.length() >= size;
        validate(isValid, fieldName, errorMsgKey, request);
        return isValid;
    }

    public static boolean isDuplicateCategories(List<Category> list, String value, String fieldName, String errorMsgKey,
                                      HttpServletRequest req){
        boolean isValid = false;
        for (Category category : list) {
            if(category.getName().equals(value)){
                isValid = true;
                break;
            }
        }
        validate(!isValid, fieldName, errorMsgKey, req);
        return isValid;
    }

    public static boolean isDuplicateTasks(List<Task> list, String value, String fieldName, String errorMsgKey,
                                      HttpServletRequest req){
        boolean isValid = false;
        for (Task task : list) {
            if(task.getName().equals(value)){
                isValid = true;
                break;
            }
        }
        validate(!isValid, fieldName, errorMsgKey, req);
        return isValid;
    }
}
