package com.bionic.friendsphotos.web.REST;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/users_rest")
public class UserService {

    DevicesService ds = new DevicesService();

    @POST
    @Path("/change_name_by_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeNameById(String input) throws ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");
//
//        System.out.println(map.get(0));
//
//        if(map == null){
//            return Response.status(304).build();
//        } else {
        //String result = "Device saved: " + deviceId;
        Device device = ds.findById(deviceId);
        device.setName("Adolf");
        ds.updateDevice(device);

        return Response.status(201).build();
//        }
    }

    @POST
    @Path("/first_opening")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response firstOpen(String input) throws ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");
//        Number age = (Number) jsonObject.get("age");

        Map<String, Object> map = ds.getCurrentGroupAndUserName(deviceId);


//
//        System.out.println(map.get(0));
//
//        if(map == null){
//            return Response.status(304).build();
//        } else {
            //String result = "Device saved: " + deviceId;
            return Response.status(201).entity(map).build();
//        }
    }
/*
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
 */
}
