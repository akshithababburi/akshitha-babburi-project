package com.iit.oops.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BuyNothingExceptionMapper implements ExceptionMapper<BuyNothingException> {
    @Override
    public Response toResponse(BuyNothingException e) {
        return Response.status(e.getCode())
                .entity(new BuyNothingException(302, "Rampam Pam"))
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
