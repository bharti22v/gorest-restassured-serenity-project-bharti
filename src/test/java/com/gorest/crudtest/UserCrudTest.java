package com.gorest.crudtest;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.UserSteps;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCrudTest extends TestBase {
    static String name = TestUtils.generateName();
    static String email = "exampletesting" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "female";
    static String status = "active";
    static String token = "d6851f31a083a10f963dbeb428800b37e36edc1241ba6a0d01ae5e1abde36c9e";

    static int userId;

    @Steps
    UserSteps userSteps;

    @Title("Test001-This will create a new User")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(token, name, email, gender, status);
        response.statusCode(201);
        userId = response.extract().path("id");
    }

    @Title("Test002-Getting user detail and verify it")
    @Test
    public void test002() {
        ValidatableResponse response = userSteps.getUserInfoById(userId, token);
        response.statusCode(200);
    }

    @Title("Test003-Updating user info and verify updated details")
    @Test
    public void test003() {
        String uName = name + "updated";
        String uEmail = "u" + email;
        String uStatus = "inactive";

        ValidatableResponse response = userSteps.updateUserInfoById(userId, token, uName, uEmail, gender, uStatus);
        response.statusCode(200);
    }

    @Title("Test004-Deleting user and verify it deleted")
    @Test
    public void test004() {
        userSteps.deleteUser(userId, token).statusCode(204);
        userSteps.getUserInfoById(userId, token).statusCode(404);

    }
}
