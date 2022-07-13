package flightMicroService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no such entity in database")
public class EntityAlreadyExist extends RuntimeException {
    public EntityAlreadyExist(String errorMessage) {
        super(errorMessage, null, false,false);
    }

    public EntityAlreadyExist() {
        super("Entity not found exception", null,false,false);
    }
}
