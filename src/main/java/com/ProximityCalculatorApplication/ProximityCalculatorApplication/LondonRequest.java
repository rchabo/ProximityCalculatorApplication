package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * A class containing the default London Request. More requests could be added to this class with a few tweaks.
 */
public class LondonRequest {

    /**
     * Method for getting people from London or currently near London. First we grab all the users from the API and use
     * our Haversine method to decide which users are close to London, using hard coded coordinates. We then call the
     * API individually for each person that is close to London in order to get an entry with all their data, including
     * their city.
     *
     * Next, we call the API for all users from London, and again call the API for each user that is returned in order
     * to get an entry with all of their information.
     *
     * We then return a list containing all the entries that passed the checks.
     *
     * @return A list of the people from London or whose coordinates are currently near London.
     */
    ArrayList<Person> getPeopleFromOrNearLondon()
    {
        ArrayList<Person> people = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(RequestStrings.HEROKU_URL + RequestStrings.USERS)).build();

        JsonParsers jsonParsers = new JsonParsers();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(inputString -> jsonParsers.parseAndCheckResponse(inputString, people))
                .join();

        // We can just get the people with city='london' this way, rather than doing a call for each individual person
        // and checking their city field.
        HttpRequest cityRequest = HttpRequest.newBuilder(URI.create(RequestStrings.HEROKU_URL + RequestStrings.FOR_CITY + "London/" + RequestStrings.USERS)).build();

        httpClient.sendAsync(cityRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> people.addAll(jsonParsers.retrievePeopleEntries(new Gson().fromJson(response, new TypeToken<ArrayList<Person>>(){}.getType()))))
                .join();

        return people;
    }
}
