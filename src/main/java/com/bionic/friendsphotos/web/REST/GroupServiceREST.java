package com.bionic.friendsphotos.web.REST;

import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.GroupService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/GroupREST")
public class GroupServiceREST {

    GroupService gs;

    @POST
    @Path("/createOpenGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOpenGroup(Group group){

        String result = "Open Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/createClosedGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClosedGroup(Group group){

        String result = "Closed Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/createModerateGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModerateGroup(Group group){
        String result = "Moderate Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/changeGroupName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeGroupName(@FormParam("deviceId")String deviceId,
                                    @FormParam("newGroupName") String newGroupName){

        gs = new GroupService();
//        gs.changeGroupName(deviceId, newGroupName);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteMembersGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMembersInGroup(@FormParam("adminId")String adminId,
                                         @FormParam("idDeviceToRemove")String idDeviceToRemove){

        gs = new GroupService();
//        gs.removeMembersFromCurrentGroup(adminId, idDeviceToRemove):

        return Response.status(201).build();
    }
}
