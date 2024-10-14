package apiTest;

import AutoAssign.base.TestSetup;
import AutoAssign.util.UtilAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingAPITest extends TestSetup
{
    // Global variable to store booking ID for validation
    int bookingID;
    int invalidID=999999;

    UtilAPI bookingAPI = new UtilAPI();

    // Add a booking
    @Test(priority = 1)
    public void testAddBooking() {
        // Request body
        String requestBody = "{\n" +
                " \"firstname\" : \"testFirstName\",\n" +
                " \"lastname\" : \"lastName\",\n" +
                " \"totalprice\" : 10.11,\n" +
                " \"depositpaid\" : true,\n" +
                " \"bookingdates\" : {\n" +
                " \"checkin\" : \"2022-01-01\",\n" +
                " \"checkout\" : \"2024-01-01\"\n" +
                " },\n" +
                " \"additionalneeds\" : \"testAdd\"\n" +
                "}";

        // Sending POST request
        Response response = bookingAPI.addBookingPost(requestBody,"/booking");

        // Assert status code is 200 OK
        Assert.assertEquals(response.getStatusCode(), 200);

        // Extract booking ID from the response
        bookingID = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID: " + bookingID);

        System.out.println(response.prettyPrint());
        // Assert booking details in the response
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "testFirstName");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "lastName");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 10);
    }

    // Validate the booking with the ID
    @Test(priority = 2, dependsOnMethods = "testAddBooking")
    public void testValidateBookingByID() {

        // Sending GET request
        System.out.println("Booking ID: "+bookingID);
        Response response = bookingAPI.addBookingGet("/booking/",bookingID);
        System.out.println(response.prettyPrint());

        // Assert status code is 200 OK
        Assert.assertEquals(response.getStatusCode(), 200);

        // Assert booking details
        Assert.assertEquals(response.jsonPath().getString("firstname"), "testFirstName");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "lastName");
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), 10);
        Assert.assertTrue(response.jsonPath().getBoolean("depositpaid"));
    }

    // Add negative use case (Booking not found)
    @Test(priority = 3)
    public void testBookingNotFound() {

        // Sending GET request with invalid booking ID
        Response response = bookingAPI.addBookingGet("/booking/",invalidID);

        System.out.println(response.prettyPrint());
        // Assert status code is 404
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    // Add negative use case (Invalid payload)
    @Test(priority = 4)
    public void testInvalidBookingPayload() {
        // Invalid request body
        String invalidRequestBody = "{\n" +
                " \"firstname\" : 123,\n" + // firstname should be a string, but here it's a number
                " \"lastname\" : \"lastName\"\n" +
                "}";

        // Sending POST request with invalid payload
        Response response = bookingAPI.addBookingPost(invalidRequestBody,"/booking");

        System.out.println(response.prettyPrint());

        // Assert status code is 500
        Assert.assertEquals(response.getStatusCode(), 500);
    }
}
