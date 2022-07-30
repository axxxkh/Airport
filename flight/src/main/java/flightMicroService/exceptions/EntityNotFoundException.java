package flightMicroService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no such entity in database")
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMessage) {
        super(errorMessage, null, false,false);
    }

    public EntityNotFoundException() {
        super("Entity not found exception", null,false,false);
    }


}
