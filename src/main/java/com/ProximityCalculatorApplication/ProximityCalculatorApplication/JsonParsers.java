package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * Class containing different ways of parsing the data we get from the API.
 */
public class JsonParsers {

    /**
     * Parses a JSON containing more than one user and maps this to an ArrayList of Persons. The list is then iterated
     * where the distance from Central London is computed for each person. If the person is with 50 miles of London,
     * they are added to the list that will later be returned when this API is called.
     * @param response The response from calling API that gave us the users list.
     * @param listToFill The list to be filled with users that pass the check.
     */
    void parseAndCheckResponse(String response, ArrayList<Person> listToFill) {
        Type personListType = new TypeToken<ArrayList<Person>>(){}.getType();
        ArrayList<Person> people = new Gson().fromJson(response, personListType);
        people.forEach(person -> {
            if (Haversine.doHaversine(person) < 50) { // 50 Miles
                listToFill.add(retrievePersonEntry(person));
            }
        });
    }

    /**
     * Retrieve the full entry for each person in the people list. This method doesn't perform any checks.
     * @param people The list of persons we want to retrieve all the information for.
     * @return A new list where each person in the list contains data for all of its fields.
     */
    ArrayList<Person> retrievePeopleEntries(ArrayList<Person> people) {
        ArrayList<Person> listToReturn = new ArrayList<>();
        people.forEach(person -> listToReturn.add(retrievePersonEntry(person)));

        return listToReturn;
    }

    /**
     * Make a call to the API to get the entry for a specific user. This is an expensive operation, but it looks like
     * it's the only way to retrieve all the data for a given person - including their city field.
     * @param person The person we want to retrieve all the data for
     * @return The full entry for the person requested.
     */
    Person retrievePersonEntry(Person person) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(RequestStrings.HEROKU_URL + RequestStrings.USER + person.getId())).build();

        String responseJson =  httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return new Gson().fromJson(responseJson, Person.class);
    }
}
