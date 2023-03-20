# Weapose - Weather + Compose

This is an example to show current the current weather from any location. The **Weapose** is built with Android Jetpack Compose, MVVM, Clean Architecture, etc.

For the Gozio Health Android coding challenge you have been provided with a starter Weather App project.  The goal of the coding challenge will be to connect the Open Weather API (https://openweathermap.org/current) to the Current Weather Screen.

## Project Setup
In the root `local.properties` file add a key value pair for the Open Weather API. The entry should look something like this:

`OPENWEATHER_API_KEY="your_api_key"`

The API key is read in the app by calling `BuildConfig.OPENWEATHER_API_KEY`. This way the value of the API key is not committed to the repo and shared publicly.

The starter project includes:

The Current Weather Screen using Jetpack Compose → CurrentWeatherScreen.kt
Gozio Headquarters default Lat/Long → Constants.kt
Current weather icon mapping → WeatherExt.kt
Current Weather API data models → data/model directory


Open Weather API endpoint:
https://api.openweathermap.org/data/2.5/weather?lat=33.78591032377107&lon=-84.40964058633683&appid=3aa158b2f14a9f493a8c725f8133d704&units=imperial

lat=33.78591032377107 and lon=-84.40964058633683 represent the Gozio Headquarters.

Current Weather Screen Objectives

Display the following (the layout doesn’t need to be pixel perfect):
- City Name from the Weather API
- High/Low Temperatures in Fahrenheit
Current Temperature in Fahrenheit
- Weather Description
- Sunrise Time
- Wind
- Humidity
- OpenWeather icon on the left side of the screen for the current weather.  Icons are included in project (see WeatherExt.kt) or can be retrieved from: https://openweathermap.org/weather-conditions
Icons for location, sunrise, wind and humidity (included in project assets)


When coding, have fun and follow best Kotlin, Jetpack Compose and Android MVVM coding practices.  While the app is simple now, build it with future plans for adding new features and maintainability–so focus on clean code, project structure and architecture.  You may use non-commercial libraries. The app code should be submitted via a git repository so we can see a few commits showing the progress of your work.

Feel free to reach out with any questions and good luck!



## License

```
Copyright 2022 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
