package com.huce.doantotnghiep.utility.response;


public class ResponseFactory {

    public static Response getSuccessResponse(String message, Object entity) {
        return new Response(message, Response.OK_CODE, Response.OK_STATUS, entity);
    }

    public static Response getSuccessResponse(String message) {
        return new Response(message, Response.OK_CODE, Response.OK_STATUS, ResultResponse.empty());
    }

    @Deprecated
    public static Response getServerErrorResponse(String message, Object entities) {
        return new Response(message, Response.INTERNAL_ERROR_CODE, Response.ERROR_STATUS, entities);
    }


    public static Response getClientErrorResponse(String message) {
        return new Response(message, Response.CLIENT_ERROR_CODE, Response.ERROR_STATUS, ResultResponse.empty());
    }
}
