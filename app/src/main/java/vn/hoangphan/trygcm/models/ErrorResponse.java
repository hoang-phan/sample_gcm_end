package vn.hoangphan.trygcm.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Hoang Phan on 9/15/2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private String errors;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errors='" + errors + '\'' +
                '}';
    }
}
