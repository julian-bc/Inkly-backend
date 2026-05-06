package top.inkly.shared.infrastructure.input.handler;

import java.util.HashMap;
import java.util.Map;

public class ErrorHelperBuilder {
    public static Map<String, String> doDetails(Exception ex) {
        Map<String, String> details = new HashMap<>();
        details.put("Exception Name: ", ex.getClass().getName());
        details.put("Exception Cause: ", ex.getCause() != null ? ex.getCause().getMessage() : "Desconocida");

        return details;
    }

}
