import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class WeatherData {

    private String rawResposeForJson;
    private String backgroundImagePath;
        
    private JSONObject completeForcastJsonObject;
    private JSONObject todayForcastJsonObject;
    private JSONObject day1ForcastJsonObject;
    private JSONObject day2ForcastJsonObject;
    private JSONObject day3ForcastJsonObject;
    private JSONObject day4ForcastJsonObject;
    private JSONObject nearestHourForcastJsonObject;

    public WeatherData(String location){

         // handling empty loactions
         if(location.isEmpty()){
            System.out.println("Empty Request");
            return;
         }
       
      // neccesaary process to happen after getting location
      buildQuerySendRequestObtainResponse(location);
        setTodayForcastJsonObject();
        setDay1ForcastJsonObject();
        setDay2ForcastJsonObject();
        setDay3ForcastJsonObject();
        setDay4ForcastJsonObject();
        setNearestHourForcastJsonObject();
    }

    
   //  sets completeforcastJsonObject ultimately by creating send and fetching data from api
    public void buildQuerySendRequestObtainResponse(String location){
        //setting api link value
        String apiEndpointRequestLink = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

        //setting parameter for api link parameters
        String unitGroupParam  = "metric";
        String keyParam = "WQCKJXL6BDNPMZMBGDVT74TW3";
        String contentTypeParam = "json";

        //adding location string into api link
        StringBuilder apiEndpointRequestLinkStringBuilder = new StringBuilder(apiEndpointRequestLink);
        apiEndpointRequestLinkStringBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8));

        try {
            //adding parametes to the api link e.g. ?unitGroup="metric" etc.
            URIBuilder apiEndpointRequestLinkUriBuilder = new URIBuilder(apiEndpointRequestLinkStringBuilder.toString());
            apiEndpointRequestLinkUriBuilder.setParameter("unitGroup", unitGroupParam);
            apiEndpointRequestLinkUriBuilder.setParameter("key", keyParam);
            apiEndpointRequestLinkUriBuilder.setParameter("ContentType", contentTypeParam);

            // creating object to send api request
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // using GET method to send request by building the URI
            HttpGet get = new HttpGet(apiEndpointRequestLinkUriBuilder.build());

            // sending request and fetching response
            CloseableHttpResponse httpResponse = httpClient.execute(get);

            // incase of bad response from server
            if(httpResponse.getCode() != HttpStatus.SC_OK){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Error Code: " + httpResponse.getCode());
                alert.setContentText("Bad request From Server, Retry!");
                alert.showAndWait();
            }

            // fetching entity from response
            HttpEntity responseEntity = httpResponse.getEntity();
            // incase of null entity
            if(responseEntity == null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Empty Response");
                alert.setContentText("Server sent an empty response.");
                alert.showAndWait();
            }

            // extracting raw response for Json Processing
            rawResposeForJson = EntityUtils.toString(responseEntity, Charset.forName("utf-8"));
            // setting completeForcastObject from raw response string
            completeForcastJsonObject = new JSONObject(rawResposeForJson);



        } catch (Exception e) {
            e.printStackTrace();
            // incase of network issue, show alert
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Network Error");
                alert.setHeaderText("You are Dissconnected !");
                alert.setContentText("Network Exceprion occured, please connect to internet.\n Tip: \r\n" + //
                        "Check your network cables, wifi,  modem and routers.");
                alert.showAndWait();
        }
        
    }

   //  seeting jsononject for today forcast
    public void setTodayForcastJsonObject(){
      todayForcastJsonObject = completeForcastJsonObject.getJSONArray("days").getJSONObject(0);
    }


   //  setting object for other days forcast
    public void setDay1ForcastJsonObject(){
       day1ForcastJsonObject = completeForcastJsonObject.getJSONArray("days").getJSONObject(1);
    }
    public void setDay2ForcastJsonObject(){
       day2ForcastJsonObject = completeForcastJsonObject.getJSONArray("days").getJSONObject(2);
      }
   public void setDay3ForcastJsonObject(){
      day3ForcastJsonObject = completeForcastJsonObject.getJSONArray("days").getJSONObject(3);
   }
   public void setDay4ForcastJsonObject(){
      day4ForcastJsonObject = completeForcastJsonObject.getJSONArray("days").getJSONObject(4);
   }

// seeting nearest hour Json object for hour based info for accurate resluts
    public void setNearestHourForcastJsonObject(){

      // obtaining cuurent epoch time
      long currentEpochTime = System.currentTimeMillis()/1000;

      // obtaining today hours forcast to fetch the nearest epoch forcast
      JSONArray todayHoursForcastArray = todayForcastJsonObject.getJSONArray("hours");

      for(int i = 0; i <= 23; i++){
         // finding just bigger  hoursJsonObject by matching next hour epoch
         if( todayHoursForcastArray.getJSONObject(i).getLong("datetimeEpoch") > currentEpochTime){

            // obtaining differnce from cuurent epoch  from next hour epoch
            long curentEpochTimeUpperCieling = todayHoursForcastArray.getJSONObject(i).getLong("datetimeEpoch") - currentEpochTime;

            // when current epoch is shorter then the shortest epoch from response
            // avoiding i = -1 situation
            if( i ==0 ){
               nearestHourForcastJsonObject  = todayHoursForcastArray.getJSONObject(i);      
            }
            else{
               // obtaining differnt from cuurent epoch to just previous hour epoch
               long cuurentEpochTimeLowerFloor = currentEpochTime - todayHoursForcastArray.getJSONObject(i-1).getLong("datetimeEpoch");
               
               // finding nearest hour by camparing diff
               if(curentEpochTimeUpperCieling <= cuurentEpochTimeLowerFloor){
                  nearestHourForcastJsonObject = todayHoursForcastArray.getJSONObject(i);
               }
               else{
                  nearestHourForcastJsonObject = todayHoursForcastArray.getJSONObject(i-1);
               }
            }

            // stoping loop from futher comparinson since nearest hour is found
            break;
         }
      }


   }

    public String getPlaceName(){
        return completeForcastJsonObject.getString("resolvedAddress");
   }

    public String getTime(){

      // getting current time and minutes of India
      LocalTime currentTimeInIndia = LocalTime.now();
      long currentTimeOfIndiaInMinutes = 0 - currentTimeInIndia.until(LocalTime.MIDNIGHT, ChronoUnit.MINUTES);

      // getting UTC time ( UTCtime + 5:30 = IndiaTime )
      long UTCTimeInMinutes = currentTimeOfIndiaInMinutes - (long)(5.5 * 60);
      
      // getting preffered place time in miute
      long searchedPlaceTimeInMinutes = UTCTimeInMinutes + (long)(completeForcastJsonObject.getDouble("tzoffset") * 60 );

      // obtaining HH : MM
      long searchedPlaceTimeInHH = searchedPlaceTimeInMinutes / 60 ;
      long searchedPlaceTimeInMM = searchedPlaceTimeInMinutes % 60;

      // ensuring 00:00 format
       String searcedPlaceTimeInHHString = ""+searchedPlaceTimeInHH;
       String searchedPlaceTimeInMMString = ""+searchedPlaceTimeInMM;

      if(searchedPlaceTimeInHH < 10){
          searcedPlaceTimeInHHString = "0" + searchedPlaceTimeInHH;
      }
      if(searchedPlaceTimeInMM < 10){
          searchedPlaceTimeInMMString = "0" + searchedPlaceTimeInMM;
      }
     
         return searcedPlaceTimeInHHString + ":" + searchedPlaceTimeInMMString;
      
   }

   // setting weather icon and wallpaper same time
    public String getWeatherImagePath(){

      String imagepath = "";

      switch (nearestHourForcastJsonObject.getString("icon")) {

         case "snow":
            imagepath = "/images/weatherIcons/Snow.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "snow-showers-day":
            imagepath = "/images/weatherIcons/Snow-Shower-Day.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "snow-showers-night":
            imagepath = "/images/weatherIcons/Snow-Shower-Night.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Night.jpg";
            break;
         
         case "thunder-rain":
            imagepath = "/images/weatherIcons/Thunder-Rain.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "thunder-showers-day":
            imagepath = "/images/weatherIcons/Thunder-Shower-Day.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "thunder-showers-night":
            imagepath = "/images/weatherIcons/Thunder-Shower-Night.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Night.jpg";
            break;
         
         case "rain":
            imagepath = "/images/weatherIcons/Rain.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "showers-day":
            imagepath = "/images/weatherIcons/Shower-Day.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "showers-night":
            imagepath = "/images/weatherIcons/Shower-Night.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Night.jpg";
            break;
         
         case "fog":
            imagepath = "/images/weatherIcons/Fog.gif";
            backgroundImagePath = "/images/deaultWeather.jpg";
            break;
         
         case "wind":
            imagepath = "/images/weatherIcons/Wind.png";
            backgroundImagePath = "/images/deaultWeather.jpg";
            break;
         
         case "cloudy":
            imagepath = "/images/weatherIcons/Cloudy.png";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "partly-cloudy-day":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Day.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Day.jpg";
            break;
         
         case "partly-cloudy-night":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Night.gif";
            backgroundImagePath = "/images/Cloudy-Sky-Night.jpg";
            break;
         
         case "clear-day":
            imagepath = "/images/weatherIcons/Clear-Day.gif";
            backgroundImagePath = "/images/Clear-Sky-Day.jpg";
            break;
         
         case "clear-night":
            imagepath = "/images/weatherIcons/Clear-Night.gif";
            backgroundImagePath = "/images/Clear-Sky-Night.jpg";
            break;
      
         default:
            imagepath = "/images/weatherIcons/AllWeather.gif";
            backgroundImagePath = "/images/deaultWeather.jpg";
            break;
      }
       return imagepath;
   }

    public String getActualTemp(){
       return ""+nearestHourForcastJsonObject.getDouble("temp");
    }

    public String getWeatherName(){
       return nearestHourForcastJsonObject.getString("icon");
    }

    public String getFeelsLikeLabel(){
       return "" +nearestHourForcastJsonObject.getDouble("feelslike");
    }

    public String getWeatherDescription(){
        return todayForcastJsonObject.getString("description");
    }

   //  this method must be called after calling getWeatherImage() method
    public String getbackgroundImagePath(){
       return backgroundImagePath;
    }

    public String getWind(){
       return ""+nearestHourForcastJsonObject.getDouble("windgust");
    }

    public String getHumidity(){
       return ""+nearestHourForcastJsonObject.getDouble("humidity");

    }

    public String getPresssure(){
       return ""+nearestHourForcastJsonObject.getDouble("pressure");
    }

    public String getDew(){
       return ""+nearestHourForcastJsonObject.getDouble("dew");
    }

    public String getVisibility(){
        return "" + ( 100 - nearestHourForcastJsonObject.getDouble("visibility"));
    }

    public String getSunsirse(){
       return todayForcastJsonObject.getString("sunrise");
    }

    public String getSunset(){
       return todayForcastJsonObject.getString("sunset");
    }


    public String getDate1(){
      // parsing into E , dd MMM
      String inputDateString = day1ForcastJsonObject.getString("datetime");
      LocalDate date  = LocalDate.parse(inputDateString);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM");
      String formattedDate = date.format(formatter);

       return formattedDate;
    }

    public String getDate2(){
      // parsing into E , dd MMM
      String inputDateString = day2ForcastJsonObject.getString("datetime");
      LocalDate date  = LocalDate.parse(inputDateString);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM");
      String formattedDate = date.format(formatter);

       return formattedDate;
    }

    public String getDate3(){
      // parsing into E , dd MMM
      String inputDateString = day3ForcastJsonObject.getString("datetime");
      LocalDate date  = LocalDate.parse(inputDateString);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM");
      String formattedDate = date.format(formatter);

       return formattedDate;
    }

    public String getDate4(){
      // parsing into E , dd MMM
      String inputDateString = day4ForcastJsonObject.getString("datetime");
      LocalDate date  = LocalDate.parse(inputDateString);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM");
      String formattedDate = date.format(formatter);

       return formattedDate;
    }

    public String getDate1ImagePath(){

      String imagepath = "";

      switch (day1ForcastJsonObject.getString("icon")) {

         case "snow":
            imagepath = "/images/weatherIcons/Snow.gif";
            break;
         
         case "snow-showers-day":
            imagepath = "/images/weatherIcons/Snow-Shower-Day.gif";
            break;
         
         case "snow-showers-night":
            imagepath = "/images/weatherIcons/Snow-Shower-Night.gif";
            break;
         
         case "thunder-rain":
            imagepath = "/images/weatherIcons/Thunder-Rain.gif";
            break;
         
         case "thunder-showers-day":
            imagepath = "/images/weatherIcons/Thunder-Shower-Day.gif";
            break;
         
         case "thunder-showers-night":
            imagepath = "/images/weatherIcons/Thunder-Shower-Night.gif";
            break;
         
         case "rain":
            imagepath = "/images/weatherIcons/Rain.gif";
            break;
         
         case "showers-day":
            imagepath = "/images/weatherIcons/Shower-Day.gif";
            break;
         
         case "showers-night":
            imagepath = "/images/weatherIcons/Shower-Night.gif";
            break;
         
         case "fog":
            imagepath = "/images/weatherIcons/Fog.gif";
            break;
         
         case "wind":
            imagepath = "/images/weatherIcons/Wind.png";
            break;
         
         case "cloudy":
            imagepath = "/images/weatherIcons/Cloudy.png";
            break;
         
         case "partly-cloudy-day":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Day.gif";
            break;
         
         case "partly-cloudy-night":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Night.gif";
            break;
         
         case "clear-day":
            imagepath = "/images/weatherIcons/Clear-Day.gif";
            break;
         
         case "clear-night":
            imagepath = "/images/weatherIcons/Clear-Night.gif";
            break;
      
         default:
            imagepath = "/images/weatherIcons/AllWeather.gif";
            break;
      }
       return imagepath;
    }

    public String getDate2ImagePath(){

      String imagepath = "";

      switch (day2ForcastJsonObject.getString("icon")) {

         case "snow":
            imagepath = "/images/weatherIcons/Snow.gif";
            break;
         
         case "snow-showers-day":
            imagepath = "/images/weatherIcons/Snow-Shower-Day.gif";
            break;
         
         case "snow-showers-night":
            imagepath = "/images/weatherIcons/Snow-Shower-Night.gif";
            break;
         
         case "thunder-rain":
            imagepath = "/images/weatherIcons/Thunder-Rain.gif";
            break;
         
         case "thunder-showers-day":
            imagepath = "/images/weatherIcons/Thunder-Shower-Day.gif";
            break;
         
         case "thunder-showers-night":
            imagepath = "/images/weatherIcons/Thunder-Shower-Night.gif";
            break;
         
         case "rain":
            imagepath = "/images/weatherIcons/Rain.gif";
            break;
         
         case "showers-day":
            imagepath = "/images/weatherIcons/Shower-Day.gif";
            break;
         
         case "showers-night":
            imagepath = "/images/weatherIcons/Shower-Night.gif";
            break;
         
         case "fog":
            imagepath = "/images/weatherIcons/Fog.gif";
            break;
         
         case "wind":
            imagepath = "/images/weatherIcons/Wind.png";
            break;
         
         case "cloudy":
            imagepath = "/images/weatherIcons/Cloudy.png";
            break;
         
         case "partly-cloudy-day":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Day.gif";
            break;
         
         case "partly-cloudy-night":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Night.gif";
            break;
         
         case "clear-day":
            imagepath = "/images/weatherIcons/Clear-Day.gif";
            break;
         
         case "clear-night":
            imagepath = "/images/weatherIcons/Clear-Night.gif";
            break;
      
         default:
            imagepath = "/images/weatherIcons/AllWeather.gif";
            break;
      }
       return imagepath;
    }

    public String getDate3ImagePath(){

      String imagepath = "";

      switch (day3ForcastJsonObject.getString("icon")) {

         case "snow":
            imagepath = "/images/weatherIcons/Snow.gif";
            break;
         
         case "snow-showers-day":
            imagepath = "/images/weatherIcons/Snow-Shower-Day.gif";
            break;
         
         case "snow-showers-night":
            imagepath = "/images/weatherIcons/Snow-Shower-Night.gif";
            break;
         
         case "thunder-rain":
            imagepath = "/images/weatherIcons/Thunder-Rain.gif";
            break;
         
         case "thunder-showers-day":
            imagepath = "/images/weatherIcons/Thunder-Shower-Day.gif";
            break;
         
         case "thunder-showers-night":
            imagepath = "/images/weatherIcons/Thunder-Shower-Night.gif";
            break;
         
         case "rain":
            imagepath = "/images/weatherIcons/Rain.gif";
            break;
         
         case "showers-day":
            imagepath = "/images/weatherIcons/Shower-Day.gif";
            break;
         
         case "showers-night":
            imagepath = "/images/weatherIcons/Shower-Night.gif";
            break;
         
         case "fog":
            imagepath = "/images/weatherIcons/Fog.gif";
            break;
         
         case "wind":
            imagepath = "/images/weatherIcons/Wind.png";
            break;
         
         case "cloudy":
            imagepath = "/images/weatherIcons/Cloudy.png";
            break;
         
         case "partly-cloudy-day":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Day.gif";
            break;
         
         case "partly-cloudy-night":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Night.gif";
            break;
         
         case "clear-day":
            imagepath = "/images/weatherIcons/Clear-Day.gif";
            break;
         
         case "clear-night":
            imagepath = "/images/weatherIcons/Clear-Night.gif";
            break;
      
         default:
            imagepath = "/images/weatherIcons/AllWeather.gif";
            break;
      }
       return imagepath;
    }

    public String getDate4ImagePath(){

      String imagepath = "";

      switch (day4ForcastJsonObject.getString("icon")) {

         case "snow":
            imagepath = "/images/weatherIcons/Snow.gif";
            break;
         
         case "snow-showers-day":
            imagepath = "/images/weatherIcons/Snow-Shower-Day.gif";
            break;
         
         case "snow-showers-night":
            imagepath = "/images/weatherIcons/Snow-Shower-Night.gif";
            break;
         
         case "thunder-rain":
            imagepath = "/images/weatherIcons/Thunder-Rain.gif";
            break;
         
         case "thunder-showers-day":
            imagepath = "/images/weatherIcons/Thunder-Shower-Day.gif";
            break;
         
         case "thunder-showers-night":
            imagepath = "/images/weatherIcons/Thunder-Shower-Night.gif";
            break;
         
         case "rain":
            imagepath = "/images/weatherIcons/Rain.gif";
            break;
         
         case "showers-day":
            imagepath = "/images/weatherIcons/Shower-Day.gif";
            break;
         
         case "showers-night":
            imagepath = "/images/weatherIcons/Shower-Night.gif";
            break;
         
         case "fog":
            imagepath = "/images/weatherIcons/Fog.gif";
            break;
         
         case "wind":
            imagepath = "/images/weatherIcons/Wind.png";
            break;
         
         case "cloudy":
            imagepath = "/images/weatherIcons/Cloudy.png";
            break;
         
         case "partly-cloudy-day":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Day.gif";
            break;
         
         case "partly-cloudy-night":
            imagepath = "/images/weatherIcons/Partly-Cloudy-Night.gif";
            break;
         
         case "clear-day":
            imagepath = "/images/weatherIcons/Clear-Day.gif";
            break;
         
         case "clear-night":
            imagepath = "/images/weatherIcons/Clear-Night.gif";
            break;
      
         default:
            imagepath = "/images/weatherIcons/AllWeather.gif";
            break;
      }
       return imagepath;
    }

    public String getDate1MinTemp(){
       return ""+day1ForcastJsonObject.getDouble("tempmin");
    }

    public String getDate2MinTemp(){
       return ""+day2ForcastJsonObject.getDouble("tempmin");
    }

    public String getDate3MinTemp(){
      return ""+day3ForcastJsonObject.getDouble("tempmin");    
   }

    public String getDate4MinTemp(){
      return ""+day4ForcastJsonObject.getDouble("tempmin");  
  }

    public String getDate1MaxTemp(){
       return ""+day1ForcastJsonObject.getDouble("tempmax");
    }

    public String getDate2MaxTemp(){
      return ""+day2ForcastJsonObject.getDouble("tempmax");
    }

    public String getDate3MaxTemp(){
      return ""+day3ForcastJsonObject.getDouble("tempmax");
    }

    public String getDate4MaxTemp(){
      return ""+day4ForcastJsonObject.getDouble("tempmax");
    }




}
