package com.bionic.friendsphotos.web.rest;

import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/users")
public class UserRestService {

    private DevicesService ds = new DevicesService();
    private GroupService gs = new GroupService();

    private final int notCreate = 304;
    private final int created = 201;
    private final int ok = 200;

/*
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
*/
    @POST
    @Path("/first_opening")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response firstOpen(String input) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");

        Map<String, Object> map = ds.getCurrentGroupAndUserName(deviceId);
        if (map == null) {
            return Response.status(notCreate).build();
        } else {
            return Response.status(created).entity(map).build();
        }
    }

    @POST
    @Path("/login_by_fb")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response loginByFB(final String input) {

        JSONObject jsonObject = parse(input);
        String deviceId = (String) jsonObject.get("deviceId");
        String userName = (String) jsonObject.get("userName");
        String description = (String) jsonObject.get("description");
        String fbProfile = (String) jsonObject.get("fbProfile");

        BigInteger fb =  new BigInteger(fbProfile);

        return Response.status(200).entity(fb).build();

//        boolean login =  ds.loginByFacebook(deviceId, fbProfile,
//                                            userName, description);
//        if (login) {
//            return Response.status(created).build();
//        } else {
//            return Response.status(notCreate).build();
//        }
    }
/*
    @GET
    @Path("/search_group_by_fb_friend")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGroupByFBFriend(){

        return Response.status(200).build();
    }
*/



    @POST
    @Path("/connect_to_open_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response connectToOpenGroup(final String input) {

        JSONObject jsonObject = parse(input);
        String deviceId = (String) jsonObject.get("deviceId");
        Long groupId = (Long) jsonObject.get("groupId");

//        boolean ans = ds.connectUserToOpenGroup(deviceId, groupId);
//        if (ans) {
//            return Response.status(ok).build();
//        } else {
//            return Response.status(notCreate).build();
//        }
        return Response.status(200).entity(groupId).build();
    }


    @POST
    @Path("/connect_to_closed_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response connectToClosedGroup(final String input) {

        JSONObject jsonObject = parse(input);
        String deviceId = (String) jsonObject.get("deviceId");
        Long groupId = (Long) jsonObject.get("groupId");
        String password = (String) jsonObject.get("password");

        boolean ans = ds.connectUserToClosedGroup(deviceId, groupId, password);

        if (ans) {
            return Response.status(ok).build();
        } else {
            return Response.status(notCreate).build();
        }
    }

    @GET
    @Path("/get_all_current_group_members")
    @Produces(MediaType.APPLICATION_JSON)
    public final List getAllCurrentGroupMembers(final String input) {

        JSONObject jsonObject = parse(input);
        String deviceId = (String) jsonObject.get("deviceId");

        List list = ds.membersOfUsersCurrentGroup(deviceId);
        return list;
    }

    private JSONObject parse(final String input) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(input);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
