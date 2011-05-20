package org.motechproject.mobile.omp.manager.gupshup;

public class SMSGupshupResponse {

    private String result;
    private String recipient;
    private String message;
    private String response;

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String PIPE = "\\|";

    public SMSGupshupResponse(String gatewayResponse) {
        response = gatewayResponse;
        buildResponse(response.split(PIPE));
    }

    private void buildResponse(String[] responseParts) {
        result = responseParts[0].trim();
        recipient = responseParts[1].trim();
        message = responseParts[2].trim();
    }

    public boolean isSuccess() {
        return SUCCESS.equalsIgnoreCase(result);
    }

    public String recipient() {
        return recipient;
    }

    public boolean isError() {
        return ERROR.equalsIgnoreCase(result);
    }

    @Override
    public String toString() {
        return response;
    }
}
