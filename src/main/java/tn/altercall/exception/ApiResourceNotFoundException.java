package tn.altercall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND /* , reason = " Resource Not Found" */)
public class ApiResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String apiMsg;

    public ApiResourceNotFoundException(String apiMsg) {
        super(apiMsg);
    }

    public String getApiMsg() {
        return apiMsg;
    }

    public void setApiMsg(String apiMsg) {
        this.apiMsg = apiMsg;
    }

}
