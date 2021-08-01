package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

public class Haversine {

    // Average radius of the earth is approx 6371 KM or 3963 Miles
    static final double RADIUS_KM = 6371.0;
    static final double RADIUS_MILES = 3963.0;

    /**
     * These coordinates are taken from central london. I was going to
     * average the coordinates from the available people entries but realised
     * the coordinates didn't necessarily match up to the cities, so having
     * the field City = "London" doesn't mean the person is currently in London.
     */
    static final double londonLatitude = 51.50722;
    static final double londonLongitude = -0.1275;

    /**
     * After doing a bit of research on how does one calculate the distance between two sets of coordinates,
     * the Haversine formula seemed to come up a lot, so I decided to go with it.
     * @param person The person we are currently measuring the distance of from London.
     * @return The distance (in KM or Miles, depending on which constant you use in the calculation) between the input
     * person and the london coordinates.
     */
    public static double doHaversine(Person person) {
        double inputLatitude = person.getLatitude();
        double inputLongitude = person.getLongitude();

        double latDistance = Math.toRadians(inputLatitude - londonLatitude);
        double lonDistance = Math.toRadians(inputLongitude - londonLongitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(londonLatitude)) * Math.cos(Math.toRadians(inputLatitude)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return RADIUS_MILES * c; // the distance between the input person and the London coordinates
    }
}
