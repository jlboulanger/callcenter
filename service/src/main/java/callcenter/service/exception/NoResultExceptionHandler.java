package callcenter.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.dao.EmptyResultDataAccessException;

public class NoResultExceptionHandler implements ExceptionMapper<EmptyResultDataAccessException> {

    @Override
    public Response toResponse(EmptyResultDataAccessException exception) {
        return Response.status(404).build();
    }

}
