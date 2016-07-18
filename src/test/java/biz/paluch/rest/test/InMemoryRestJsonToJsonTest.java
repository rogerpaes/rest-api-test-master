package biz.paluch.rest.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 12.03.15 08:33
 * https://github.com/mp911de/rest-api-test
 */
public class InMemoryRestJsonToJsonTest {

    @Path("myresource")
    public static class MyResource {
    	@POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Pessoa createMyModel(Pessoa obj) {
			return obj;
        }
    }

    public static MyResource restImplementation = new MyResource();
    public static InMemoryRestServer server;
    public static Gson gson = new Gson(); //caso precise converter seu objeto para Json e vice-versa

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(restImplementation);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void postSimpleBodyAndReturnAllFields() throws Exception {
    	
    	Pessoa pessoaToSend = new Pessoa(1,"JOAO",42);
    	
    	Response response = server.newRequest("/myresource").request().buildPost(Entity.json(pessoaToSend)).invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Pessoa pessoaRetorno = response.readEntity(Pessoa.class);
        System.out.println(gson.toJson(pessoaRetorno));
		assertEquals(pessoaRetorno, pessoaToSend);
        
    }
}
