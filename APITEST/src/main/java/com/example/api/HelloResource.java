package com.example.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/log")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

  private static final UserLogDao DAO = buildDao();
    private static final Logger log = LoggerFactory.getLogger(HelloResource.class);
////
    private static UserLogDao buildDao() {
   DataSource ds = DataSourceFactory.create();

       Jdbi jdbi = JdbiFactory.create(ds);
 return jdbi.onDemand(UserLogDao.class);
   }


//
//    // Jersey requires this EXACT form
//    public HelloResource() {
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addLog() {
//        DAO.insert(request.userId, request.action);
//        return Response.ok("{\"status\":\"success\"}").build();
//    }

    @GET
    public String log(){return "seee";}

    @GET
    @Path("/{id}")
    public Patient getPatient(@PathParam("id") int id){
    log.info("hello bro");
        return DAO.getPatient(id);

    }

}



//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//
//@Path("/hello")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class HelloResource {
//    private final UserLogDao dao;
//
//    public HelloResource() {
//        var ds = DataSourceFactory.create();     // Hikari + ClickHouse
//        var jdbi = JdbiFactory.create(ds);       // JDBI
//        this.dao = jdbi.onDemand(UserLogDao.class);
//    }
//
//    @POST
//    public Response addLog(UserLogRequest request) {
//        dao.insert(request.userId, request.action);
//        return Response.ok("{\"status\":\"success\"}").build();
//    }
//
//
//
////    private final Jdbi jdbi = DB.createJdbi();
////
////    @GET
////    @Produces(MediaType.APPLICATION_JSON)
////    public String getUser() {
////        String n= jdbi.withHandle(handle ->
////                handle.createQuery("SELECT * FROM patient WHERE id = 1")
////                        .mapToMap()
////                        .one()
////        ).toString();
////        return  n;
////}
////
//}