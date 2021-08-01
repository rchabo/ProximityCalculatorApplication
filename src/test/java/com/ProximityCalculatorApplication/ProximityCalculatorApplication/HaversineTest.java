package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Most of the tests in here are getting the 'expected' values by cross-checking a few different websites such as:
 *
 *  - https://latlongdata.com/distance-calculator/
 *  - https://gps-coordinates.org/distance-between-coordinates.php
 *
 * As the results seem to differ from website to website. There are also some rounding errors in the haversine
 * function. It is for these reasons that I've given the assertions a fairly large delta.
 */
class HaversineTest {

    @Test
    void do_haversine_with_normal_values_calculates_distances_as_expected() {

        // Create objects we will use
        Person person = mock(Person.class);

        // Mock
        when(person.getLatitude()).thenReturn(5.4);
        when(person.getLongitude()).thenReturn(12.443);

        // Test
        double result  = Haversine.doHaversine(person);

        // Verify
        Assertions.assertEquals(3266.505, result, 5);
    }

    @Test
    void do_haversine_with_two_negative_values_calculates_distances_as_expected() {

        // Create objects we will use
        Person person = mock(Person.class);

        // Mock
        when(person.getLatitude()).thenReturn(-5.4);
        when(person.getLongitude()).thenReturn(-12.443);

        // Test
        double result  = Haversine.doHaversine(person);

        // Verify
        Assertions.assertEquals(3998.915, result, 5);
    }

    @Test
    void do_haversine_with_large_numbers_calculates_distances_as_expected() {

        // Create objects we will use
        Person person = mock(Person.class);

        // Mock
        when(person.getLatitude()).thenReturn(180.0);
        when(person.getLongitude()).thenReturn(-180.0);

        // Test
        double result  = Haversine.doHaversine(person);

        // Verify
        Assertions.assertEquals(3558.806, result, 5);
    }
}