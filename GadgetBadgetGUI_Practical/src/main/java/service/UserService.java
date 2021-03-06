package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.UserServlet;

public class UserService {

	
	UserServlet rp_obj = new UserServlet();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPosts()
	{
		return rp_obj.read_Post();
	}
	
	
//	Insert user Service 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPost(
			@FormParam("username") String username,
			@FormParam("email") String email,
			@FormParam("dob") String dob,
			@FormParam("password") String password){
			String output= rp_obj.createPost(username, email, dob, password); 
			return output;
	}
	
	

	//update user service	

@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData)
	{
		//Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();
		
		String ID = postObj.get("id").getAsString();
		String username = postObj.get("username").getAsString();
		String password = postObj.get("email").getAsString();
		String name = postObj.get("dob").getAsString();
		String type = postObj.get("type").getAsString();
		
		
		String output=rp_obj.updatePost(ID, username, password, name, type);
		
		return output;
	}



//Delete user service

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String userdata)
{
	Document doc= Jsoup.parse(userdata,"",Parser.xmlParser());
	
	String ID=doc.select("iduser").text();
	
	String output=rp_obj.deletePost(ID);
	return output;
}
	
}
