package com.iit.oops.util;

import com.iit.oops.exception.BuyNothingException;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;

public class ResponseUtil {

    public Response createErrorResponse(BuyNothingException exception, Response.Status code) {
        return Response
                .status(code)
                .entity(exception.toString())
                .build();
    }

    public Response createSuccessResponse(Object object, Response.Status status, String location) {
        if (object == null) {
            return Response
                    .status(status)
                    .build();
        }
        if (StringUtils.isNotBlank(location)) {
            return Response
                    .status(status)
                    .header("Location", location)
                    .entity(object)
                    .build();
        } else {
            return Response
                    .status(status)
                    .entity(object)
                    .build();
        }


    }
}
