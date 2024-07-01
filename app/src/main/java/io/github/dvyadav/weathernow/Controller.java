package io.github.dvyadav.weathernow;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable{

    ExecutorService exe = Executors.newCachedThreadPool();
    WeatherData weatherData;

    @FXML
    Rectangle wallpaperBox;

    @FXML
    ImageView wallpaperImage;

    @FXML
    Label currentWeatherTitleLabel;

    @FXML
    Label currentTimeLabel;

    @FXML
    ImageView weatherVisualImageView;

    @FXML
    Label actualTempLabel;

    @FXML
    Label weatherNameLabel;

    @FXML
    Label feelsLikeLabel;

    @FXML
    Label weatherDescriptionLabel;

    @FXML
    Label windTitleLabell;
    @FXML
    Label windValueLabel;

    @FXML
    Label humidityTitleLabel;
    @FXML
    Label  humidityValueLabel;

    @FXML
    Label pressureTitleLabel;
    @FXML
    Label  pressureValueLabel;

    @FXML
    Label dewTitleLabel;
    @FXML
    Label  dewValueLabel;

    @FXML
    Label vsisibilityTitleLabel;
    @FXML
    Label  visibilityValueLabel;

    @FXML
    Label sunriseTitleLabel;
    @FXML
    Label  suriseValuelabel;

    @FXML
    Label sunsetTitleLabel;
    @FXML
    Label  susnsetValueLabel;

    @FXML
    TextField searchLocationTextField;
    @FXML
    ImageView searchIconImageView;
    @FXML
    Rectangle searchIconRectangle;

    @FXML
    Label placeNameLabel;

    @FXML
    Rectangle day1Rectangle;
    @FXML
    Rectangle day2Rectangle;
    @FXML
    Rectangle day3Rectangle;
    @FXML
    Rectangle day4Rectangle;

    @FXML
    Label day1titleLabel;
    @FXML
    Label day2titleLabel;
    @FXML
    Label day3titleLabel;
    @FXML
    Label day4titleLabel;

    @FXML
    ImageView day1ImageView;
    @FXML
    ImageView day2ImageView;
    @FXML
    ImageView day3ImageView;
    @FXML
    ImageView day4ImageView;

    @FXML
    Label day1minTempLabel;
    @FXML
    Label day2minTempLabel;
    @FXML
    Label day3minTempLabel;
    @FXML
    Label day4minTempLabel;

    @FXML
    Label day1maxTempLabel;
    @FXML
    Label day2maxTempLabel;
    @FXML
    Label day3maxTempLabel;
    @FXML
    Label day4maxTempLabel;

    @FXML
    Group searchFieldGroup;

    @FXML
    ImageView laodingIcon;

    // thsi method will execute on loading the fmxl file to scene
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // setting wallaper image
        // wallpaperBox.setFill(new ImagePattern(wallpaperImage.getImage()));
    }
    

    public void highlight(MouseEvent e){
        searchIconRectangle.setOpacity(1.0);
    }

    public void removeHighlight(MouseEvent e){
        searchIconRectangle.setOpacity(0.0);
    }

    public void inceraseSearchFieldSize(MouseEvent e){
        searchFieldGroup.setScaleX(1.01);
        searchFieldGroup.setScaleY(1.01);
    }

    public void decreaseSearchFieldSize(MouseEvent e){
        searchFieldGroup.setScaleX(1);
        searchFieldGroup.setScaleY(1);
    }

    public void increaseWallpaperBoxSize(MouseEvent e){
        wallpaperBox.setScaleX(1.01);
        wallpaperBox.setScaleY(1.01);

        placeNameLabel.setScaleX(1.02);
        placeNameLabel.setScaleY(1.02);

        currentWeatherTitleLabel.setScaleX(1.01);
        currentWeatherTitleLabel.setScaleY(1.01);

        currentTimeLabel.setScaleX(1.01);
        currentTimeLabel.setScaleY(1.01);

        weatherVisualImageView.setScaleX(1.01);
        weatherVisualImageView.setScaleY(1.01);

        actualTempLabel.setScaleX(1.01);
        actualTempLabel.setScaleY(1.01);

        weatherNameLabel.setScaleX(1.01);
        weatherNameLabel.setScaleY(1.01);

        feelsLikeLabel.setScaleX(1.01);
        feelsLikeLabel.setScaleY(1.01);

        weatherDescriptionLabel.setScaleX(1.01);
        weatherDescriptionLabel.setScaleY(1.01);

        windTitleLabell.setScaleX(1.01);
        windTitleLabell.setScaleY(1.01);
        windValueLabel.setScaleX(1.01);
        windValueLabel.setScaleY(1.01);

        humidityTitleLabel.setScaleX(1.01);
        humidityTitleLabel.setScaleY(1.01);
        humidityValueLabel.setScaleX(1.01);
        humidityValueLabel.setScaleY(1.01);

        pressureTitleLabel.setScaleX(1.01);
        pressureTitleLabel.setScaleY(1.01);
        pressureValueLabel.setScaleX(1.01);
        pressureValueLabel.setScaleY(1.01);

        dewTitleLabel.setScaleX(1.01);
        dewTitleLabel.setScaleY(1.01);
        dewValueLabel.setScaleX(1.01);
        dewValueLabel.setScaleY(1.01);

        visibilityValueLabel.setScaleX(1.01);
        visibilityValueLabel.setScaleY(1.01);
        vsisibilityTitleLabel.setScaleX(1.01);
        vsisibilityTitleLabel.setScaleY(1.01);

        sunriseTitleLabel.setScaleX(1.01);
        sunriseTitleLabel.setScaleY(1.01);
        suriseValuelabel.setScaleX(1.01);
        suriseValuelabel.setScaleY(1.01);

        sunsetTitleLabel.setScaleX(1.01);
        sunsetTitleLabel.setScaleY(1.01);
        susnsetValueLabel.setScaleX(1.01);
        susnsetValueLabel.setScaleY(1.01);
    }
    

    public void decreaseWallpaperBoxSize(MouseEvent e){
        wallpaperBox.setScaleX(1);
        wallpaperBox.setScaleY(1);

        placeNameLabel.setScaleX(1.0);
        placeNameLabel.setScaleY(1.0);

        currentWeatherTitleLabel.setScaleX(1);
        currentWeatherTitleLabel.setScaleY(1);

        currentTimeLabel.setScaleX(1);
        currentTimeLabel.setScaleY(1);

        weatherVisualImageView.setScaleX(1);
        weatherVisualImageView.setScaleY(1);

        actualTempLabel.setScaleX(1);
        actualTempLabel.setScaleY(1);

        weatherNameLabel.setScaleX(1);
        weatherNameLabel.setScaleY(1);

        feelsLikeLabel.setScaleX(1);
        feelsLikeLabel.setScaleY(1);

        weatherDescriptionLabel.setScaleX(1);
        weatherDescriptionLabel.setScaleY(1);

        windTitleLabell.setScaleX(1);
        windTitleLabell.setScaleY(1);
        windValueLabel.setScaleX(1);
        windValueLabel.setScaleY(1);

        humidityTitleLabel.setScaleX(1);
        humidityTitleLabel.setScaleY(1);
        humidityValueLabel.setScaleX(1);
        humidityValueLabel.setScaleY(1);

        pressureTitleLabel.setScaleX(1);
        pressureTitleLabel.setScaleY(1);
        pressureValueLabel.setScaleX(1);
        pressureValueLabel.setScaleY(1);

        dewTitleLabel.setScaleX(1);
        dewTitleLabel.setScaleY(1);
        dewValueLabel.setScaleX(1);
        dewValueLabel.setScaleY(1);

        visibilityValueLabel.setScaleX(1);
        visibilityValueLabel.setScaleY(1);
        vsisibilityTitleLabel.setScaleX(1);
        vsisibilityTitleLabel.setScaleY(1);

        sunriseTitleLabel.setScaleX(1);
        sunriseTitleLabel.setScaleY(1);
        suriseValuelabel.setScaleX(1);
        suriseValuelabel.setScaleY(1);

        sunsetTitleLabel.setScaleX(1);
        sunsetTitleLabel.setScaleY(1);
        susnsetValueLabel.setScaleX(1);
        susnsetValueLabel.setScaleY(1);
    }

    public void increaseDay1BoxSize(MouseEvent e){
        day1Rectangle.setScaleX(1.05);
        day1Rectangle.setScaleY(1.05);

        day1ImageView.setScaleX(1.05);
        day1ImageView.setScaleY(1.05);

        day1maxTempLabel.setScaleX(1.05);
        day1maxTempLabel.setScaleY(1.05);

        day1minTempLabel.setScaleX(1.05);
        day1minTempLabel.setScaleY(1.05);

        day1titleLabel.setScaleX(1.05);
        day1titleLabel.setScaleY(1.05);

    }

     public void decreaseDay1BoxSize(MouseEvent e){
        day1Rectangle.setScaleX(1.0);
        day1Rectangle.setScaleY(1.0);

        day1ImageView.setScaleX(1.0);
        day1ImageView.setScaleY(1.0);

        day1maxTempLabel.setScaleX(1.0);
        day1maxTempLabel.setScaleY(1.0);

        day1minTempLabel.setScaleX(1.0);
        day1minTempLabel.setScaleY(1.0);

        day1titleLabel.setScaleX(1.0);
        day1titleLabel.setScaleY(1.0);
    }

    public void increaseDay2BoxSize(MouseEvent e){
        day2Rectangle.setScaleX(1.05);
        day2Rectangle.setScaleY(1.05);

        day2ImageView.setScaleX(1.05);
        day2ImageView.setScaleY(1.05);

        day2maxTempLabel.setScaleX(1.05);
        day2maxTempLabel.setScaleY(1.05);

        day2minTempLabel.setScaleX(1.05);
        day2minTempLabel.setScaleY(1.05);

        day2titleLabel.setScaleX(1.05);
        day2titleLabel.setScaleY(1.05);

    }

     public void decreaseDay2BoxSize(MouseEvent e){
        day2Rectangle.setScaleX(1.0);
        day2Rectangle.setScaleY(1.0);

        day2ImageView.setScaleX(1.0);
        day2ImageView.setScaleY(1.0);

        day2maxTempLabel.setScaleX(1.0);
        day2maxTempLabel.setScaleY(1.0);

        day2minTempLabel.setScaleX(1.0);
        day2minTempLabel.setScaleY(1.0);

        day2titleLabel.setScaleX(1.0);
        day2titleLabel.setScaleY(1.0);
    }

     public void increaseDay3BoxSize(MouseEvent e){
        day3Rectangle.setScaleX(1.05);
        day3Rectangle.setScaleY(1.05);

        day3ImageView.setScaleX(1.05);
        day3ImageView.setScaleY(1.05);

        day3maxTempLabel.setScaleX(1.05);
        day3maxTempLabel.setScaleY(1.05);

        day3minTempLabel.setScaleX(1.05);
        day3minTempLabel.setScaleY(1.05);

        day3titleLabel.setScaleX(1.05);
        day3titleLabel.setScaleY(1.05);

    }

     public void decreaseDay3BoxSize(MouseEvent e){
        day3Rectangle.setScaleX(1.0);
        day3Rectangle.setScaleY(1.0);

        day3ImageView.setScaleX(1.0);
        day3ImageView.setScaleY(1.0);

        day3maxTempLabel.setScaleX(1.0);
        day3maxTempLabel.setScaleY(1.0);

        day3minTempLabel.setScaleX(1.0);
        day3minTempLabel.setScaleY(1.0);

        day3titleLabel.setScaleX(1.0);
        day3titleLabel.setScaleY(1.0);
    }

     public void increaseDay4BoxSize(MouseEvent e){
        day4Rectangle.setScaleX(1.05);
        day4Rectangle.setScaleY(1.05);

        day4ImageView.setScaleX(1.05);
        day4ImageView.setScaleY(1.05);

        day4maxTempLabel.setScaleX(1.05);
        day4maxTempLabel.setScaleY(1.05);

        day4minTempLabel.setScaleX(1.05);
        day4minTempLabel.setScaleY(1.05);

        day4titleLabel.setScaleX(1.05);
        day4titleLabel.setScaleY(1.05);

    }

     public void decreaseDay4BoxSize(MouseEvent e){
        day4Rectangle.setScaleX(1.0);
        day4Rectangle.setScaleY(1.0);

        day4ImageView.setScaleX(1.0);
        day4ImageView.setScaleY(1.0);

        day4maxTempLabel.setScaleX(1.0);
        day4maxTempLabel.setScaleY(1.0);

        day4minTempLabel.setScaleX(1.0);
        day4minTempLabel.setScaleY(1.0);

        day4titleLabel.setScaleX(1.0);
        day4titleLabel.setScaleY(1.0);
    }

    //    handle serach, manages both mouse and key events
    public void handleSearchAndEvent(Event e){
        /* 
         * serach if text is not empty
         * and avoid if the keypressed on text feild is other than ENTER
         */
        if(! searchLocationTextField.getText().isEmpty()){
            // do not search if ENTER not pressed
            if(e.getSource() == searchLocationTextField && ((KeyEvent)e).getCode() != KeyCode.ENTER){
                return;
            }

            // this object manages list of task to perform concurrently
            Task<Void> backendTask = new Task<Void>() {
                // Perfomes this method to handle backend task without freezing UI
                @Override
               protected Void call(){
                    sendLocation();
                    return null;
                }
                // shows laoding inteface while becked task are running
                @Override
                protected void running(){
                    placeNameLabel.setText("Loading...");
                    laodingIcon.setVisible(true);
                }
                //  set the data to fronted after successfull fetching of weather data
                @Override
                protected void succeeded(){
                    laodingIcon.setVisible(false);
                    setFronted();
                }

            };

            // new Thread(backendTask).start();
            exe.execute(backendTask);
            // exe.shutdown();
            
        }
    }


    // setting forntend 
    public void setFronted(){
        // setting placeNamelabel
    placeNameLabel.setText(weatherData.getPlaceName());

    // setting currettimeLabel
    currentTimeLabel.setText(weatherData.getTime());

    // setting weatherVisualImageView & wallpaperImage Always call this f(x) before getBackgroundImage
    weatherVisualImageView.setImage(new Image(getClass().getResourceAsStream(weatherData.getWeatherImagePath())));
   wallpaperBox.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(weatherData.getbackgroundImagePath()))));//DO NOT call it before getWeatherImagePath()


// \u00B0

    // setting actualTempLabel
    actualTempLabel.setText(weatherData.getActualTemp()+"\u00B0"+"C");

    // setting weatherNameLabel
    weatherNameLabel.setText(weatherData.getWeatherName());

    // setting fellsLikealabel
    feelsLikeLabel.setText("feels like "+weatherData.getFeelsLikeLabel()+"\u00B0"+"C");

    // setting weatherDescriptionLabel
    weatherDescriptionLabel.setText(weatherData.getWeatherDescription());

    // setting Wind
    windValueLabel.setText(weatherData.getWind()+" kmh");

    // setting humidity
    humidityValueLabel.setText(weatherData.getHumidity()+"%");

    // setting pressure
    pressureValueLabel.setText(weatherData.getPresssure()+" mb");

    // setting dew
    dewValueLabel.setText(weatherData.getDew()+"\u00B0");

    // setting Visbility
    visibilityValueLabel.setText(weatherData.getVisibility()+" km");

    // setting sunrise
    suriseValuelabel.setText(weatherData.getSunsirse());

    // setting sunset
    susnsetValueLabel.setText(weatherData.getSunset());

    // setting day1 title
    day1titleLabel.setText(weatherData.getDate1());
    // setting day2 title
    day2titleLabel.setText(weatherData.getDate2());
    // setting day3 title
    day3titleLabel.setText(weatherData.getDate3());
    // setting day4 title
    day4titleLabel.setText(weatherData.getDate4());


    // setting day1image
    day1ImageView.setImage(new Image(getClass().getResourceAsStream(weatherData.getDate1ImagePath())));
    // setting day2 image
    day2ImageView.setImage(new Image(getClass().getResourceAsStream(weatherData.getDate2ImagePath())));
    // setting day3 image
    day3ImageView.setImage(new Image(getClass().getResourceAsStream(weatherData.getDate3ImagePath())));
    // setting day4 image
    day4ImageView.setImage(new Image(getClass().getResourceAsStream(weatherData.getDate4ImagePath())));
    
    // setting day1 Max  & Min temp
    day1maxTempLabel.setText(weatherData.getDate1MaxTemp()+"\u00B0"+"C");
    day1minTempLabel.setText(weatherData.getDate1MinTemp()+"\u00B0"+"C");

    // setting day2 Max  & Min temp
    day2maxTempLabel.setText(weatherData.getDate2MaxTemp()+"\u00B0"+"C");
    day2minTempLabel.setText(weatherData.getDate2MinTemp()+"\u00B0"+"C");

    // setting day1 Max  & Min temp
    day3maxTempLabel.setText(weatherData.getDate3MaxTemp()+"\u00B0"+"C");
    day3minTempLabel.setText(weatherData.getDate3MinTemp()+"\u00B0"+"C");
    
    // setting day1 Max  & Min temp
    day4maxTempLabel.setText(weatherData.getDate4MaxTemp()+"\u00B0"+"C");
    day4minTempLabel.setText(weatherData.getDate4MinTemp()+"\u00B0"+"C");

    }
   
   // this method should not handle event , i should be called by event handling method
  public void sendLocation(){

    // Api call request and backedn processes
    try {
        weatherData  = new WeatherData( searchLocationTextField.getText());
    } catch (Exception e) {
        System.out.println("API KEY ERROR IN \'api_key.txt\'");
    }

  }
}