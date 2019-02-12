package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.net.URL;
import java.net.URLConnection;

import static io.restassured.RestAssured.given;


public class steps_testing_api {

    public Response response;
    public String title;
    public boolean completed;
    public int status;

       @Given("^que estou conectado a internet$")
       public void que_estou_conectado_a_internet() throws Throwable {

           try {
               URL url = new URL("https://www.google.com/");
               URLConnection connection = url.openConnection();
               connection.connect();

               System.out.println("Connection Successful");

               Assert.assertTrue(true);
           }
           catch (Exception e) {
               System.out.println("Internet Not Connected");
               Assert.assertTrue(false);
           }
       }

       @When("^efetuo o request com metodo GET na url \"([^\"]*)\"$")
       public void efetuo_o_request_com_metodo_GET_na(String url) throws Throwable {

           System.out.println(url);

           response =
                   given()
                           .when()
                           .get(url)
                           .then()
                           .extract().
                           response();

           response.print();



           JsonPath pathjson = response.getBody().jsonPath();

           title = pathjson.get("title");
           completed = pathjson.get("completed");
           status = response.statusCode();

           System.out.println(title);
           System.out.println(completed);
           System.out.println(status);

       }

       @Then("^campo title \"([^\"]*)\"$")
       public void campo_title(String campo_title) throws Throwable {


           if(title.equals(campo_title)) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }

       @Then("^campo completed \"([^\"]*)\"$")
       public void campo_completed(String campo_completed) throws Throwable {

           boolean campo_completed_final = Boolean.parseBoolean(campo_completed);

           if(completed == campo_completed_final) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }

       @Then("^status ok (\\d+)$")
       public void status_ok(int status) throws Throwable {

           if(this.status == status) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }



   }