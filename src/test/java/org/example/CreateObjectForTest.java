package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.dto.Role;
import org.example.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.example.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateObjectForTest {

    static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON);
    }

    @Test
    void testStub() {
        Assertions.assertTrue(true);
    }

    @Test
    void testConnect() {
        requestSpecification
                .given()
                .get("/petclinic")
                .then()
                .statusCode(SUCCESS_CODE);
    }

    @Test
    void crateUserAdmin() {

        Role role = Role.builder()
                .name("admin")
                .build();

        User user = User.builder()
                .username("testadmin")
                .password("123456")
                .enabled(true)
                .roles(new Role[]{role})
                .build();

        requestSpecification
                .given()
                .with().body(user)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .post("/petclinic/api/users")
                .then()
                .statusCode(CREATE_CODE);
    }

}