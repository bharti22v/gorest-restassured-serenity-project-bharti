package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class UserSteps {
    @Step("Creating User with name: {1}, email: {2}, gender :{3}, status :{4}")
    public ValidatableResponse createUser(String token, String name, String email, String gender, String status) {

        UserPojo userPojo = UserPojo.getUserPojo(name, email, gender, status);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post(EndPoints.GET_ALL_USER)
                .then();

    }

    @Step("Getting user detail by userid: {0}")
    public ValidatableResponse getUserInfoById(int userId, String token) {
        return SerenityRest.given()
                .pathParam("userID", userId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }

    @Step("Updating User with userId: {0}, name: {2}, email: {3}, gender :{4}, status :{5}")
    public ValidatableResponse updateUserInfoById(int userId, String token, String name, String email, String gender, String status) {

        UserPojo userPojo = UserPojo.getUserPojo(name, email, gender, status);

        return SerenityRest.given()
                .pathParam("userID", userId)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .patch(EndPoints.UPDATE_SINGLE_USER_BY_ID)
                .then();

    }

    @Step("Deleting user with userid: {0}")
    public ValidatableResponse deleteUser(int userId, String token) {
        return SerenityRest.given()
                .pathParam("userID", userId)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPoints.DELETE_SINGLE_USER_BY_ID)
                .then();
    }
}
