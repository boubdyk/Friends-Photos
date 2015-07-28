package com.bionic.friendsphotos.web.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/photo_rest")
public class PhotoService {

    @GET
    @Path("/check_photo")
    @Produces(MediaType.APPLICATION_JSON)
    public void checkPhoto(){
    }

    @GET
    @Path("/get_photo")
    @Produces(MediaType.APPLICATION_JSON)
    public void getPhoto(){}

    @POST
    @Path("/set_new_photo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setNewPhoto(){
        String result = "New photo added";
        return Response.status(201).entity(result).build();
    }
}
