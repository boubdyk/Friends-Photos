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
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/group")
public class GroupRestService {
    /**
     * Creating object GroupService for accessing to it's methods.
     */
    private GroupService gs = new GroupService();
    /**
     * Creating object DeviceService for accessing to it's methods.
     */
    private DevicesService ds = new DevicesService();
    /**
     * Variable for declaring answer from server with intuitive name.
     */
    private final int notCreate = 304;
    /**
     * Variable for declaring answer from server with intuitive name.
     */
    private final int created = 201;

    /**
     * @param input  json file which must contain:
     *              <ul>
     *                  <li>String groupName</li>
     *                  <li>Byte groupType</li>
     *                  <li>String password</li>
     *                  <li>String creatorId</li>
     *                  <li>Double coordinateX</li>
     *                  <li>Double coordinateY</li>
     *             </ul>
     * @return ID of new group
     */
    @POST
    @Path("/create_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response createOpenGroup(final String input) {

        JSONObject jsonObject = parse(input);

        String groupName = (String) jsonObject.get("groupName");
        Byte groupType = (Byte) jsonObject.get("groupType");
        String password = (String) jsonObject.get("password");
        String creatorId = (String) jsonObject.get("creatorId");
        Double coordinateX = (Double) jsonObject.get("coordinateX");
        Double coordinateY = (Double) jsonObject.get("coordinateY");

        Long identifierOfGroup =  gs.createGroup(groupName, groupType, password,
                                                    creatorId, coordinateX, coordinateY);
        if (identifierOfGroup == null) {
            return Response.status(notCreate).build();
        } else {
            String result = "\"identifierOfGroup\":" + "\"" + identifierOfGroup + "\"";
            return Response.status(created).entity(result).build();
        }
    }
/*

    @POST
    @Path("/create_moderate_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModerateGroup(Group group){
        String result = "Moderate Group is created with ID: " + group.getId();
        return Response.status(201).entity(result).build();
    }
*/

    /**
     *
     * @param input   json file which must contain:
     *                <ul>
     *                      <li>String deviceId</li>
     *                      <li>String newGroupName</li>
     *                </ul>
     * @return answer from server:
     *                <ul>
     *                    <li>304 if not changed name of group</li>
     *                    <li>201 if name of group changed</li>
     *                </ul>
     */

    @POST
    @Path("/change_group_name")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response changeGroupName(final String input) {

        JSONObject jsonObject = parse(input);
        String deviceId = (String) jsonObject.get("deviceId");
        String newGroupName = (String) jsonObject.get("newGroupName");

        boolean ans = ds.changeGroupName(deviceId, newGroupName);
        if (ans) {
            return Response.status(created).build();
        } else {
            return Response.status(notCreate).build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public final List searchGroupByName(@QueryParam("searchValue") String searchValue) {

//        JSONObject jsonObject = parse(input);
//        String searchValue = (String) jsonObject.get("searchValue");
//        Double latitude = (Double) jsonObject.get("latitude");
//        Double longitude = (Double) jsonObject.get("longitude");

        List list = gs.getAllGroupsByNameOrID(searchValue);
        if (list == null) {
            List list1 = new LinkedList<String>();
            list1.add("Group not found!");
            return  list1;
        } else {
            return list;
        }
//        if (searchValue != null) {
//            return searchValue;
//        } else if (latitude != null && longitude != null) {
//            return gs.getAllGroupsByGPS(latitude, longitude);
//        } else {
//            return null;
//        }

    }

    /**
     *
     * @param input json file which must contain:
     *                <ul>
     *                      <li>String adminId</li>
     *                      <li>String idDeviceToRemove</li>
     *                </ul>
     * @return answer from server:
     *                <ul>
     *                      <li>304 if members of group not deleted</li>
     *                      <li>201 if deleting successful </li>
     *                </ul>
     */

    @DELETE
    @Path("/delete_members_group")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response deleteMembersInGroup(final String input) {

        JSONObject jsonObject = parse(input);
        String adminId = (String) jsonObject.get("adminId");
        String idDeviceToRemove = (String) jsonObject.get("idDeviceToRemove");

        boolean ans = ds.removeMembersFromCurrentGroup(adminId, idDeviceToRemove);
        if (ans) {
            return Response.status(created).build();
        } else {
            return Response.status(notCreate).build();
        }
    }

    /**
     * @param input json file which need to parse
     * @return JSONObject
     */
    private JSONObject parse(final String input) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(input);
            return (JSONObject) obj;
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
