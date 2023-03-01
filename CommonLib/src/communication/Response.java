package communication;

import java.io.Serializable;

public class Response implements Serializable {

    private Object result;
    private String message;
    private Exception exception;

    public Response() {
    }

    public Response(Object result, String message) {
        this.result = result;
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
