package utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Builder {
   public static String baseUrl = "https://rahulshettyacademy.com";
   public static RequestSpecification req;

   public  static RequestSpecification addPlaceRequestSpec() throws FileNotFoundException {
       if (req==null){
           PrintStream log =new PrintStream(new FileOutputStream("test-output/log/test_places.log"));
             req =new RequestSpecBuilder()
                   .setBaseUri(baseUrl)
                   .addQueryParam("key","qaclick123")
                   .addFilter(RequestLoggingFilter.logRequestTo(log))
                   .addFilter(ResponseLoggingFilter.logResponseTo(log))
                   .setContentType(ContentType.JSON).build();
           return req;
       }
       return req;
   };
}
