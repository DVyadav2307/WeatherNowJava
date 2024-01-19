# WeatherNow

![WeatherNow Logo](https://github.com/DVyadav2307/WeatherNowJava/blob/main/app/src/main/resources/io/github/dvyadav/weathernow/images/weatherIcons/AllWeather.gif)

WeatherNow is a Java application that shows the current weather information for any location. You can enter the name of a city, country, or zip code, and the application will display the temperature, humidity, wind speed, and weather condition for that location.

## Features

- Simple and user-friendly interface
- Supports multiple locations and units
- Uses OpenWeatherMap API to fetch weather data
- Displays weather icons and background images based on weather condition

## Requirements

- Java 11 or higher
- JavaFX 11 or higher
- Gson 2.8.6 or higher
- OpenWeatherMap API key

## Installation

To install WeatherNow, you have two options:

- Clone or download this repository from [GitHub](https://github.com/DVyadav2307/WeatherNowJava) and run the application with gradle. You need to obtain an API key from OpenWeatherMap and store it in a file named `api_key.txt` in the `resources` folder of your project. You can get an API key for free by signing up on their website:

  - [TimelineWeatherAPI](^https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/^)

- Download the executable files from the [installer](installer) directory and run the application with the appropriate script for your operating system. The API key is already included in the executable files.

## Usage

To run WeatherNow, you can use one of the following methods:

- Use the gradle wrapper scripts (`gradlew` or `gradlew.bat`) that are included in the repository. You can run the application from the command line with the following command:

  ```bash
  ./gradlew run
  ```

- Use the executable files from the installer directory. You can run the application from the command line with the appropriate script for your operating system. For example, on Windows:

  ```bash
  WeatherNow.bat
  ```

Once the application is running, you can enter the name of a location in the text field and press the enter key or the search button. The application will display the weather information for that location. You can also change the units by clicking on the Celsius or Fahrenheit buttons.

## License

WeatherNow is licensed under the MIT License. See the `LICENSE` file for more details.

## Contributing

WeatherNow is an open source project and contributions are welcome. If you want to contribute, please follow these steps:

- Fork this repository and create a new branch
- Make your changes and commit them with a descriptive message
- Push your branch to your forked repository
- Create a pull request and explain your changes

## Contact

If you have any questions, suggestions, or feedback, you can contact me at:

- Email: dvyadav2307@gmail.com
- GitHub: [DVyadav2307](^4^)

## Acknowledgements

I would like to thank the following sources for providing the resources and inspiration for this project:

- [OpenWeatherMap](^2^) for the weather data and icons
- [Unsplash](^5^) for the background images
- [JavaFX] for the GUI framework
- [Gson] for the JSON parsing library
- [Introduction to Java Programming] by Y. Daniel Liang for the Java concepts and examples

Source: Conversation with Bing, 19/1/2024
(1) Intro To Java Programming, Comprehensive... by Y. Daniel Liang. https://www.amazon.in/Intro-Programming-Comprehensive-Version-Pearson/dp/935306578X.
(2) Introduction to Java Programming : Liang, Y. Daniel: Amazon.in: Books. https://www.amazon.in/Introduction-Java-Programming-Student-Value/dp/0134671716.
(3) Intro to Java Programming, Comprehensive Version Y. Daniel Liang .... https://www.pearsoned.co.in/web/books/9789353065782_Intro-to-Java-Programming-Comprehensive-Version_Y-Daniel-Liang.aspx.
(4) Introduction to Java Programming, Brief Version - Pearson. https://www.pearson.com/en-us/subject-catalog/p/Liang-Introduction-to-Java-Programming-Brief-Version-11th-Edition/P200000003486/9780137504374.
(5) Introduction to Java Programming : Liang, Y. Daniel: Amazon.in: Books. https://www.amazon.in/Introduction-Java-Programming-Student-Value/dp/0134671716.
(6) Introduction to Java Programming - Google Books. https://books.google.com/books/about/Introduction_to_Java_Programming.html?id=OmC4ngEACAAJ.
