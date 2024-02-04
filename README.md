# WeatherNow

![WeatherNow Logo](https://github.com/DVyadav2307/WeatherNowJava/blob/main/app/src/main/resources/io/github/dvyadav/weathernow/images/weatherIcons/AllWeather.gif)

WeatherNow is a Java application that shows the current weather information for any location. You can enter the name of a city, country, or zip code, and the application will display the temperature, humidity, wind speed, and weather condition for that location.

## Features

- Simple and user-friendly interface
- Supports multiple locations and PIN Codes (Postal Codes)
- Uses TimeLine API to fetch weather data
- Displays weather icons and background images based on weather condition

## Requirements

- Java 21 or higher
- JavaFX 21.0.2 or higher
- Gradle 8.5 or higgher
- TimeLine API key

## Installation

To install WeatherNow, you have two options:

- Clone or download this repository from [GitHub](https://github.com/DVyadav2307/WeatherNowJava) and run the application with gradle. You need to obtain an API key from TimeLine Weather API and save in your system as enviroment veraible with variable name "WEATHER_API_KEY". You can get an API key for free by signing up on their website:

  - [TimelineWeatherAPI](https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/)

- Download the executable files from the [installer](installer) directory and run the application with the appropriate script for your operating system. The API key is already included in the executable files.

## Usage

To run WeatherNow, you can use one of the following methods:

- Use the gradle wrapper scripts (`gradlew` or `gradlew.bat`) that are included in the repository. You can run the application from the command line with the following command:

  ```bash
  ./gradlew run
  ```

- Use the executable files from the installer directory. You can run the application from the command line with the appropriate script for your operating system. For example, on Windows:

  ```bash
  WeatherNow.exe
  ```

Once the application is running, you can enter the name of a location in the text field and press the enter key or the search button. The application will display the weather information for that location. You can also change the units by clicking on the Celsius or Fahrenheit buttons.

## Contributing

WeatherNow is an open source project and contributions are welcome. If you want to contribute, please follow these steps:

- Fork this repository and create a new branch
- Make your changes and commit them with a descriptive message
- Push your branch to your forked repository
- Create a pull request and explain your changes

## Contact

If you have any questions, suggestions, or feedback, you can contact me at:

- Email: [divyanshuy858@gmail.com](mailto:divyanshuy858@gmail.com)
- GitHub: [DVyadav2307](https://github.com/DVyadav2307)

## Acknowledgements

I would like to thank the following sources for providing the resources and inspiration for this project:

-[TimelineWeatherAPI](https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/) for the weather data and icons
- [JavaFX](https://gluonhq.com/products/javafx/) for the GUI framework
- [Gson](https://github.com/google/gson) for the JSON parsing library
- [Introduction to Java Programming]( https://www.amazon.in/Intro-Programming-Comprehensive-Version-Pearson/dp/935306578X.) by Y. Daniel Liang for the Java concepts and examples
