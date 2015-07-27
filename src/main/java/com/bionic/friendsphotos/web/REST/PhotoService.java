package com.bionic.friendsphotos.web.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/PhotoREST")
public class PhotoService {

    @GET
    @Path("/checkPhoto")
    @Produces(MediaType.APPLICATION_JSON)
    public void checkPhoto(){
    }

    @GET
    @Path("/getPhoto")
    @Produces(MediaType.APPLICATION_JSON)
    public void getPhoto(){}

    @POST
    @Path("/setNewPhoto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setNewPhoto(){
        String result = "New photo added";
        return Response.status(201).entity(result).build();
    }
}
