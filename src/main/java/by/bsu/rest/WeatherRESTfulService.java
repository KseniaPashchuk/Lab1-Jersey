package by.bsu.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/weather")
public class WeatherRESTfulService {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    //
    // http://localhost:8080/rest/weather/{date}
    // Example:
    // http://localhost:8080/rest/weather/2016-09-27
    // http://localhost:8080/rest/weather/2016-09-27
    //
    @Path("{date}")
    @GET
    @Produces("application/json")
    public String getWeather_JSON(@PathParam("date") String dateStr) {

        Date date = null;
        if (dateStr == null || dateStr.isEmpty()) {
            date = new Date();
        } else {
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                date = new Date();
            }
        }
        dateStr = df.format(date);


        int randomTemperature = -40 + (int) (Math.random() * 40);

        return "{" //
                + "'date': '" + dateStr + "'," //
                + "'location': Minsk," //
                + "'temperature': '" + randomTemperature + "'" //
                + "}";
    }
    //
    // http://localhost:8080/rest/weather/
    //
    @Path("")
    @GET
    @Produces("application/json")
    public String getWeather_JSON() {
        return getWeather_JSON(null);
    }
}
