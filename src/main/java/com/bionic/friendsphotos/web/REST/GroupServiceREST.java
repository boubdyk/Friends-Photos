package com.bionic.friendsphotos.web.REST;


import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Vlad on 23.07.2015.
 */

@Path("/group_rest")
public class GroupServiceREST {

    private GroupService gs = new GroupService();
    private DevicesService ds = new DevicesService();

    private final int notCreate = 304;
    private final int created = 201;

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
