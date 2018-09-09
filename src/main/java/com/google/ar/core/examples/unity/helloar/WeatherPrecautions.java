package com.google.ar.core.examples.unity.helloar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that maps the weather code returned by API call to warnings and precautions
 * appropriate for the presented weather condition.
 *
 * For displaying on the board along with other weather qualities.
 */

public class WeatherPrecautions {

    Map<Integer, String> weatherWarnings;

    final String safe = ". No precautions needed.";
    final String thunderWarning = " Please" +
            "find a safe shelter,\n " +
            "move from outdoors \n" +
            "into a building, unplug appliances, \n" +
            "avoid flooded roadways.";

    final String floodWarning = ". Please seek high grounds \n " +
            "turn off utilities at main switches \n " +
            "and unplug appliances. \n " +
            "Do not try to drive over a flooded road";

    final String snowWarning = ". Please stay off roads \n " +
            "Stay indoors and dress warmly.\n" +
            " Prepare for power outages.\n" +
            " Use generators outside only and away from windows.\n" +
            " Listen for emergency information and alerts.\n" +
            " Look for signs of hypothermia and frostbite.\n" +
            " Check on neighbors.";

    final String tornadoWarning = ". If you can safely get to a \n" +
            "sturdy building, then do so immediately.\n" +
            "Go to a safe room, basement, or storm cellar.\n" +
            "Stay away from windows, doors, and outside walls.\n" +
            "Do not get under an overpass or bridge. \n" +
            "You’re safer in a low, flat location.\n" +
            "Use your arms to protect your head and neck.";

    public WeatherPrecautions() {
        weatherWarnings = new HashMap<>();
        mapCodeToCondition();
    }

    private void mapCodeToCondition() {
        weatherWarnings.put(200,	"thunderstorm with light rain" + safe);
        weatherWarnings.put(201,	"thunderstorm with rain" + thunderWarning);
        weatherWarnings.put(202, "thunderstorm with heavy rain" + thunderWarning);
        weatherWarnings.put(210,	"light thunderstorm"	+ thunderWarning);
        weatherWarnings.put(211,	"thunderstorm" + thunderWarning);
        weatherWarnings.put(212, "heavy thunderstorm" + thunderWarning);
        weatherWarnings.put(221,	"ragged thunderstorm" + thunderWarning);
        weatherWarnings.put(230,	"thunderstorm with light drizzle" + safe);
        weatherWarnings.put(231,	"thunderstorm with drizzle" + safe);
        weatherWarnings.put(232, "thunderstorm with heavy drizzle" + thunderWarning);


        weatherWarnings.put(300,"light intensity drizzle" + safe);
        weatherWarnings.put(301,	"drizzle" + safe);
        weatherWarnings.put(302,	"heavy intensity drizzle" + floodWarning);
        weatherWarnings.put(310,	"light intensity drizzle rain" + safe);
        weatherWarnings.put(311, "drizzle rain" + safe);
        weatherWarnings.put(312,	"heavy intensity drizzle rain" + floodWarning);
        weatherWarnings.put(313,	"shower rain and drizzle" + safe);
        weatherWarnings.put(314,	"heavy shower rain and drizzle" + floodWarning);
        weatherWarnings.put(321,	"shower drizzle" + safe);

        weatherWarnings.put(500,	"light rain" + safe);
        weatherWarnings.put(501,	"moderate rain" + safe);
        weatherWarnings.put(502,	"heavy intensity rain" + floodWarning);
        weatherWarnings.put(503,	"very heavy rain" + floodWarning);
        weatherWarnings.put(504,	"extreme rain" + floodWarning);
        weatherWarnings.put(511,	"freezing rain" + snowWarning);
        weatherWarnings.put(520,	"light intensity shower rain" + safe);
        weatherWarnings.put(521,	"shower rain" + safe);
        weatherWarnings.put(522,	"heavy intensity shower rain" + floodWarning);
        weatherWarnings.put(531, "ragged shower rain" + safe);

        weatherWarnings.put(600,	"light snow" + safe);
        weatherWarnings.put(601,	"snow" + safe);
        weatherWarnings.put(602,	"heavy snow" + snowWarning);
        weatherWarnings.put(615,	"light rain and snow" + safe);
        weatherWarnings.put(616,	"rain and snow" + safe);
        weatherWarnings.put(620,	"light shower snow" + safe);
        weatherWarnings.put(621,	"shower snow" + snowWarning);
        weatherWarnings.put(622,	"heavy shower snow" + snowWarning);

        weatherWarnings.put(781,	"tornado" + tornadoWarning);

        weatherWarnings.put(800,	"clear sky" + safe);
        weatherWarnings.put(801,	"few clouds" + safe);
        weatherWarnings.put(802,	"scattered clouds" + safe);
        weatherWarnings.put(803,	"broken clouds" + safe);
        weatherWarnings.put(804,	"overcast clouds" + safe);
    }

    public String getWarningAndPrecaution(int code) {
        return weatherWarnings.get(code);
    }

    public Map<Integer, String> getWeatherWarningsMap() {
        return Collections.unmodifiableMap(weatherWarnings);
    }




}
