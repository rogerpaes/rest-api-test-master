package biz.paluch.rest.test;

import static org.junit.Assert.assertEquals;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 12.03.15 08:33
 * https://github.com/mp911de/rest-api-test
 */
public class InMemoryRestJsonToStringJsonViewTest {

    @Path("myresource")
    public static class MyResource {
    	@POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.TEXT_PLAIN)
        public String createMyModel(PessoaComJsonIgnore obj) throws JsonGenerationException, JsonMappingException, IOException {
    		ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.getSerializationConfig().addMixInAnnotations(PessoaComJsonIgnore.class, PessoaComJsonIgnoreMixinNomeAsNomePessoa.class);

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
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

    //teste que retorna variavel do bean, renomeando a mesma no json
    @Test
    public void postSimpleBodyAndReturnOnlyNomeAsNomePessoa() throws Exception {
    	
    	PessoaComJsonIgnore pessoaToSend = new PessoaComJsonIgnore(1,"PEDRO JSON IGNORE RETURN NOME AS NOMEPESSOA",42);
    	
    	Response response = server.newRequest("/myresource").request().buildPost(Entity.json(pessoaToSend)).invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        String pessoaRetornoAsJsonString = response.readEntity(String.class);
        //pessoaRetornoAsJsonString = pessoaRetornoAsJsonString.replaceAll("\n", "");//remover quebra de linha
        System.out.println("--------------------------------------");
        System.out.println(pessoaRetornoAsJsonString);
        System.out.println("--------------------------------------");
        assertJsonEquals("{\"nomePessoa\":\"PEDRO JSON IGNORE RETURN NOME AS NOMEPESSOA\"}", pessoaRetornoAsJsonString);
        
    }
}
