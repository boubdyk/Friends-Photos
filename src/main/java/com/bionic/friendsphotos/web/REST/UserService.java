package com.bionic.friendsphotos.web.REST;

import com.bionic.friendsphotos.service.DevicesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/UsersREST")
public class UserService {

    DevicesService ds;

    //getDeviceId
    @POST
    @Path("/firstOpen")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response firstOpen(@FormParam("deviceId") String deviceID){

        //ds.getCurrentGroupAndUserName(deviceID);

        return Response.status(201).build();
    }

    @POST
    @Path("/loginByFB")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginByFB(
            @FormParam("deviceId") String deviceID,
            @FormParam("fbProfile")BigInteger fbProfile,
            @FormParam("userName") String userName,
            @FormParam("description") String description){

        //ds.loginByFacebook(deviceID, fbProfile, userName, description);

        return Response.status(201).build();
    }

    @GET
    @Path("/searchGroupByFBFriend")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGroupByFBFriend(){

        return Response.status(200).build();
    }

    @GET
    @Path("/searchGroupByNameOrId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGroupByName(@FormParam("searchValue") String searchValue){

//        gs.getAllGroupsByNameOrID(searchValue);

        return Response.status(200).build();
    }

    @GET
    @Path("/searchGroupByGPS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGroupByGPS(@FormParam("latitude")Double latitude,
                                 @FormParam("longitude")Double longitude){

        //        gs.getAllGroupsByGPS(latitude, longitude);

        return Response.status(200).build();
    }

    @POST
    @Path("/connectToOpenGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectToOpenGroup(@FormParam("deviceId") String deviceId,
                                   @FormParam("groupId") Long groupId){
//        ds.connectUserToOpenGroup(deviceId, groupId);
        return Response.status(200).build();
    }


    @POST
    @Path("/connectToClosedGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectToClosedGroup(@FormParam("deviceId")String deviceId,
                                     @FormParam("groupId")Long groupId,
                                     @FormParam("password")String password){

//        ds.connectUserToClosedGroup(deviceId, groupId, password);

        return Response.status(200).build();
    }

    @POST
    @Path("/connectToOpenGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    public void connectToGroup(){}

    @GET
    @Path("/getAllCurrentGroupMembers")
    @Produces(MediaType.APPLICATION_JSON)
    public void getAllCurrentGroupMembers(@FormParam("deviceId")String deviceId){

//        List<> list = ds.membersOfUsersCurrentGroup(deviceId);

//        return list;
    }

}
