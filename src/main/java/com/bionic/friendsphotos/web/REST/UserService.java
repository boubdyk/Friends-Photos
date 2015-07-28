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
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/users_rest")
public class UserService {

    DevicesService ds = new DevicesService();
    GroupService gs = new GroupService();
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
    public Response firstOpen(String input) throws ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");

        Map<String, Object> map = ds.getCurrentGroupAndUserName(deviceId);


        if(map == null){
            return Response.status(304).build();
        } else {
            String result = "Device saved: " + deviceId;
            return Response.status(201).entity(map).build();
        }
    }
    @POST
    @Path("/login_by_fb")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginByFB(String input) throws ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");
        String userName = (String) jsonObject.get("userName");
        String description = (String) jsonObject.get("description");
        BigInteger fbProfile = (BigInteger) jsonObject.get("fbProfile");

        boolean login =  ds.loginByFacebook(deviceId, fbProfile, userName, description);

        if(login) {
            return Response.status(201).build();
        } else {
            return Response.status(304).build();
        }
    }
/*
    @GET
    @Path("/search_group_by_fb_friend")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGroupByFBFriend(){

        return Response.status(200).build();
    }
*/

    @GET
    @Path("/search_group_by_name_or_id")
    @Produces(MediaType.APPLICATION_JSON)
    public List searchGroupByName(String input) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String searchValue = (String) jsonObject.get("searchValue");

        return gs.getAllGroupsByNameOrID(searchValue);
    }

    @GET
    @Path("/search_group_by_gps")
    @Produces(MediaType.APPLICATION_JSON)
    public List searchGroupByGPS(String input) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        Double latitude = (Double) jsonObject.get("latitude");
        Double longitude = (Double) jsonObject.get("longitude");

        return gs.getAllGroupsByGPS(latitude, longitude);
    }

    @POST
    @Path("/connect_to_open_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectToOpenGroup(String input) throws ParseException{

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");
        Long groupId = (Long) jsonObject.get("groupId");

        boolean ans = ds.connectUserToOpenGroup(deviceId, groupId);

        if(ans) {
            return Response.status(200).build();
        } else {
            return Response.status(304).build();
        }
    }


    @POST
    @Path("/connect_to_closed_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectToClosedGroup(String input) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");
        Long groupId = (Long) jsonObject.get("groupId");
        String password = (String) jsonObject.get("password");

        boolean ans = ds.connectUserToClosedGroup(deviceId, groupId, password);

        if(ans) {
            return Response.status(200).build();
        } else {
            return Response.status(304).build();
        }
    }
   /*
    @POST
    @Path("/connect_to_open_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public void connectToGroup(){

    }
  */

    @GET
    @Path("/get_all_current_group_members")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllCurrentGroupMembers(String input) throws ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        JSONObject jsonObject = (JSONObject) obj;

        String deviceId = (String) jsonObject.get("deviceId");

        List list = ds.membersOfUsersCurrentGroup(deviceId);

        return list;
    }
}
