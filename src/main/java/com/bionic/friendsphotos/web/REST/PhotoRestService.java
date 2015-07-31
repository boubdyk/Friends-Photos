package com.bionic.friendsphotos.web.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/photo")
public class PhotoRestService {
    private PhotoRestService ps = new PhotoRestService();
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
    @Path("/upload_photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public final Response setNewPhoto(
            @FormDataParam("file") final InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetail) {

        String result = "New photo added";
        return Response.status(created).entity(result).build();
    }
}
