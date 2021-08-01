package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JsonParsersTest {

    @Test
    void parse_And_Check_Response_modifies_list_correctly() {
        // Create objects to be used
        String input =
                "[" +
                    "{" +
                        "\"id\":266," +
                        "\"first_name\":Ancell\"," +
                        "\"last_name\":\"Garnsworthy\"," +
                        "\"email\":\"agarnsworthy7d@seattletimes.com\"," +
                        "\"ip_address\":\"67.4.69.137\"," +
                        "\"latitude\":51.6553959," +
                        "\"longitude\":0.0572553," +
                        "\"city\":\"L’govskiy\"" +
                    "}," +
                    "{" +
                        "\"id\": 322," +
                        "\"first_name\":\"Hugo\"," +
                        "\"last_name\":\"Lynd\"," +
                        "\"email\":\"hlynd8x@merriam-webster.com\"," +
                        "\"ip_address\": \"109.0.153.166\"," +
                        "\"latitude\": 51.6710832," +
                        "\"longitude\": 0.8078532," +
                        "\"city\": \"Rokiciny\"" +
                    "}," +
                    "{" +
                        "\"id\": 554," +
                        "\"first_name\": \"Phyllys\"," +
                        "\"last_name\": \"Hebbs\"," +
                        "\"email\": \"phebbsfd@umn.edu\"," +
                        "\"ip_address\": \"100.89.186.13\"," +
                        "\"latitude\": 51.5489435," +
                        "\"longitude\": 0.3860497," +
                        "\"city\": \"Krolevets’\"" +
                    "}" +
                "]";

        ArrayList<Person> list = new ArrayList<>();

        // Mock

        // Subject under test
        JsonParsers jsonParsers = new JsonParsers();

        // Test
        jsonParsers.parseAndCheckResponse(input, list);

        // Verify
        Assertions.assertEquals(list.size(), 3);
    }

    @Test
    void parse_And_Check_Response_does_not_add_out_of_range_users() {
        // Create objects to be used
        String input =
                "[" +
                    "{" +
                        "\"id\":266," +
                        "\"first_name\":Ancell\"," +
                        "\"last_name\":\"Garnsworthy\"," +
                        "\"email\":\"agarnsworthy7d@seattletimes.com\"," +
                        "\"ip_address\":\"67.4.69.137\"," +
                        "\"latitude\":51.6553959," +
                        "\"longitude\":0.0572553," +
                        "\"city\":\"L’govskiy\"" +
                    "}," +
                    "{" +
                        "\"id\": 322," +
                        "\"first_name\":\"Hugo\"," +
                        "\"last_name\":\"Lynd\"," +
                        "\"email\":\"hlynd8x@merriam-webster.com\"," +
                        "\"ip_address\": \"109.0.153.166\"," +
                        "\"latitude\": 51.6710832," +
                        "\"longitude\": 0.8078532," +
                        "\"city\": \"Rokiciny\"" +
                    "}," +
                    "{" +
                        "\"id\": 554," +
                        "\"first_name\": \"Phyllys\"," +
                        "\"last_name\": \"Hebbs\"," +
                        "\"email\": \"phebbsfd@umn.edu\"," +
                        "\"ip_address\": \"100.89.186.13\"," +
                        "\"latitude\": 51.5489435," +
                        "\"longitude\": 0.3860497," +
                        "\"city\": \"Krolevets’\"" +
                    "}," +
                    "{" +
                        "\"id\": 322," +
                        "\"first_name\":\"Hugo\"," +
                        "\"last_name\":\"Lynd\"," +
                        "\"email\":\"hlynd8x@merriam-webster.com\"," +
                        "\"ip_address\": \"109.0.153.166\"," +
                        "\"latitude\": 34.6710832," +
                        "\"longitude\": -117.8078532," +
                        "\"city\": \"Rokiciny\"" +
                    "}," +
                    "{" +
                        "\"id\": 322," +
                        "\"first_name\":\"Hugo\"," +
                        "\"last_name\":\"Lynd\"," +
                        "\"email\":\"hlynd8x@merriam-webster.com\"," +
                        "\"ip_address\": \"109.0.153.166\"," +
                        "\"latitude\": 21.6710832," +
                        "\"longitude\": 15.8078532," +
                        "\"city\": \"Rokiciny\"" +
                    "}" +
                "]";

        ArrayList<Person> list = new ArrayList<>();

        // Mock

        // Subject under test
        JsonParsers jsonParsers = new JsonParsers();

        // Test
        jsonParsers.parseAndCheckResponse(input, list);

        // Verify
        Assertions.assertEquals(list.size(), 3);
    }
}
