package com.bionic.friendsphotos.web.REST;

import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.GroupService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/group_rest")
public class GroupServiceREST {

    GroupService gs;

    @POST
    @Path("/create_open_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOpenGroup(Group group){

        String result = "Open Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/create_closed_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClosedGroup(Group group){

        String result = "Closed Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/create_moderate_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModerateGroup(Group group){
        String result = "Moderate Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }

    @POST
    @Path("/change_group_name")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeGroupName(){

//        gs = new GroupService();
//        gs.changeGroupName(deviceId, newGroupName);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/delete_members_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMembersInGroup(){

//        gs = new GroupService();
//        gs.removeMembersFromCurrentGroup(adminId, idDeviceToRemove):

        return Response.status(201).build();
    }
}
