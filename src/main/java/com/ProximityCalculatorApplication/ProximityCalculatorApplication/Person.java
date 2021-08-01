package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

public class Person {

    private final int id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String ip_address;
    private final double latitude;
    private final double longitude;
    private final String city;

    public Person(int id,
                  String first_name,
                  String last_name,
                  String email,
                  String ip_address,
                  double latitude,
                  double longitude,
                  String city
    ){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.ip_address = ip_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    int getId() {
        return id;
    }

    String getFirst_name() {
        return first_name;
    }

    String getLast_name() {
        return last_name;
    }

    String getEmail() {
        return email;
    }

    String getIp_address() {
        return ip_address;
    }

    double getLatitude() {
        return latitude;
    }

    double getLongitude() {
        return longitude;
    }

    String getCity() {
        return city;
    }
}
