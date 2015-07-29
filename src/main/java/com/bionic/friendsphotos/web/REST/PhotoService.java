package com.bionic.friendsphotos.web.REST;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/photo_rest")
public class PhotoService {
    private PhotoService ps = new PhotoService();
    private final int created = 201;

    @GET
    @Path("/check_photo")
    @Produces(MediaType.APPLICATION_JSON)
    public final void checkPhoto() {
    }

    @GET
    @Path("/get_photo")
    @Produces(MediaType.APPLICATION_JSON)
    public final void getPhoto() {
    }

    @POST
    @Path("/set_new_photo")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response setNewPhoto() {
        String result = "New photo added";
        return Response.status(created).entity(result).build();
    }
}
