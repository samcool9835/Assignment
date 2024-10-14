package AutoAssign.util;

import AutoAssign.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UtilAPI extends TestBase
{
        // Set base URI
        private static final String BASE_URL = "https://restful-booker.herokuapp.com";

        // Method to add a new device
        public Response addBookingPost(String requestBody, String booking) {
            return RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(booking);

    }

    public Response addBookingGet(String booking,int bookingID) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get(booking+bookingID);

    }

}
