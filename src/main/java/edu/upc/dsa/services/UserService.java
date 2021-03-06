package edu.upc.dsa.services;


import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;


import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users", description = "Endpoint to user Service")
@Path("/users")
public class UserService {

    private UserManager um;

    public UserService() {
        this.um = UserManagerImpl.getInstance();
        if(um.getUsers().size()==0){
            um.addUser(new User("admin","admin@admin","admin"));
        }
    }


    @POST
    @ApiOperation(value = "create a new user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 401, message = "Username is already taken")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
        {
            return Response.status(404).entity(user).build();
        }

        User checking = this.um.addUser(user);
        if (checking != null)
        {
            return Response.status(201).entity(user).build();
        }
        else
        {
            return Response.status(404).entity(user).build();
        }
    }

    @GET
    @ApiOperation(value = "get all Objects in Catalogo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/catalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        List<Item> items = this.um.catalogoTienda();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all FAQs", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/faq")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacts() {

        List<FAQ> faqs = this.um.listafaqs();

        GenericEntity<List<FAQ>> entity = new GenericEntity<List<FAQ>>(faqs) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "log in user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 401, message = "Incorrect username or password")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(LogInParams loginpar) {

        //System.out.println("PARAMETROS "+loginpar.getUsername()+" ===> "+loginpar.getPassword());
            User u = this.um.login(loginpar.getUsername(), loginpar.getPassword());

            if (u!= null) {
                return Response.status(201).entity(u).build();
            }
            else {

                //System.out.println("Usuario o contrase??a incorrectos");
                return Response.status(404).build();

            }
    }



}