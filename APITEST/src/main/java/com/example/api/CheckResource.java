package com.example.api;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/check")
public class CheckResource {

    private static final String WS_URI = "ws://localhost:8080/APITEST/chat";

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response sendMessageToChat(String message) {
        try {
            ChatClient.connect(WS_URI);

            ChatClient.sendMessage(message);

            return Response.ok(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
