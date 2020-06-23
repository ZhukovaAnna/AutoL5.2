package ru.netology.rest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.awt.*;
import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
public class Generation {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void setUpAll(RegistrationDto registrationDto) {
        given()
                .spec(requestSpec)
                .body(registrationDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200); // код 200 OK
    }

    public static RegistrationDto generationUserActive() {
        Faker faker = new Faker(new Locale("EN"));
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        setUpAll(new RegistrationDto(login,password,"active"));
        return new RegistrationDto(login,password,"active");
    }
    public static RegistrationDto generationUserBlocked () {
        Faker faker = new Faker(new Locale("EN"));
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        setUpAll(new RegistrationDto(login,password,"blocked"));
        return new RegistrationDto(login,password,"blocked");
    }

    public static RegistrationDto generationUserIfLoginInvalid() {
        Faker faker = new Faker(new Locale("EN"));
        String password = faker.internet().password();
        setUpAll(new RegistrationDto("vasya",password,"active"));
        return new RegistrationDto("misha",password,"active");
    }

    public static RegistrationDto generationUserIfPasswordInvalid() {
        Faker faker = new Faker(new Locale("EN"));
        String login = faker.name().firstName().toLowerCase();
        setUpAll(new RegistrationDto(login,"password","active"));
        return new RegistrationDto(login,"parol","active");
    }



}
