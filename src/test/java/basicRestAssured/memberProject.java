package basicRestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import static io.restassured.RestAssured.given;

public class memberProject {
    Response response;
    int idProject=1;
    @Test
    public void verifyCreateMember(){
        String filePath=new File("").getAbsolutePath()+"/src/test/resources/bodyCreateMember.json";

        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic("admin","admin")
                .body(new File(filePath))
                .log().all().
        when()
                .post("http://localhost:5002/api/members");

        response
                .then()
                .statusCode(201)
                .log()
                .all();
        idProject = response.then().extract().path("id");

    }

    @Test
    public void verifyUpdateMember(){
        String filePath=new File("").getAbsolutePath()+"/src/test/resources/bodyUpdateMember.json";

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic("admin","admin")
                .body(new File(filePath))
                .log().all().
                when()
                .put("http://localhost:5002/api/members/"+idProject).
                then()
                .statusCode(200)
                .log()
                .all();


    }

}
