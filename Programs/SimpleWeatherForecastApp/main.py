import requests
import json
import datetime
import time
from configparser import ConfigParser

# Read the API key from the config file
config_file = 'config.ini'
config = ConfigParser()
config.read(config_file)
API_KEY = config['api_key']['key']

# Set the cache duration in seconds
CACHE_DURATION = 600  # Cache duration in seconds (10 minutes)
today = datetime.datetime.now().strftime('%Y-%m-%d')


class WeatherForecastApp:

    def __init__(self):
        self.cache = {}  # Initialize an empty cache to store weather data

    def run(self):
        while True:
            location = input("Enter a city name or ZIP code (or 'quit' to exit): ")
            if location.lower() == 'quit':
                break

            weather_data = self.get_weather_data(location)
            if weather_data:
                self.display_location(weather_data)
                self.display_weather(weather_data)
                self.display_forecast(weather_data)

    def display_location(self, weather_data):
        # Extract and display the city and country names from the weather data(json file)
        city_name = weather_data['name']
        country_name = weather_data['sys']['country']
        print(f"\nToday`s Date: {today}")
        print(f"Location: {city_name}, {country_name}")

    def get_weather_data(self, location):
        # The function first checks if the requested location is already in the cache.
        # If yes then it retrieves the cached data and checks if it is still valid based on the cache duration.
        # If the data is valid then it returns the cached weather data, without making an API call.
        if location in self.cache:
            cached_data = self.cache[location]
            if time.time() - cached_data['timestamp'] < CACHE_DURATION:
                return cached_data['data']

        # Fetch weather data from the OpenWeatherMap API
        url = f'http://api.openweathermap.org/data/2.5/weather?q={location}&appid={API_KEY}&units=metric'
        # It makes a GET request to the API using the requests.get method and stores the received data in the response variable
        response = requests.get(url)
        if response.status_code == 200:  # 200 means a successful request
            weather_data = json.loads(response.text)
            # Cache the fetched weather data with a timestamp for future use
            self.cache[location] = {'timestamp': time.time(), 'data': weather_data}
            # The fetched weather data is then cached in the self.cache dictionary with a timestamp showing when it was fetched
            return weather_data
        else:
            print(f"Failed to fetch weather data for {location}")
            return None

    def display_weather(self, weather_data):
        # Extract and display the current weather conditions from the weather data
        weather = weather_data['weather'][0]
        temperature = weather_data['main']['temp']
        humidity = weather_data['main']['humidity']
        wind_speed = weather_data['wind']['speed']

        print(f"Current Weather: {weather['description']}")
        print(f"Temperature: {temperature} °C")
        print(f"Humidity: {humidity}%")
        print(f"Wind Speed: {wind_speed} m/s")

    def display_forecast(self, weather_data):
        location_id = weather_data['id']
        # Fetch weather forecast data from the OpenWeatherMap API
        url = f'http://api.openweathermap.org/data/2.5/forecast?id={location_id}&appid={API_KEY}&units=metric'
        response = requests.get(url)
        if response.status_code == 200:
            forecast_data = json.loads(response.text)
            print("\nWeather Forecast:")
            daily_forecast = {}

            for forecast in forecast_data['list']:
                forecast_time = datetime.datetime.fromtimestamp(
                    forecast['dt'])  # for each forecast, it converts the forecast timestamp to a datetime object
                forecast_day = forecast_time.strftime(
                    '%Y-%m-%d')  # it extracts the forecast day in the format 'YYYY-MM-DD'

                if forecast_day != today: # exclude today's date from the forecast
                    if forecast_day not in daily_forecast:
                        # Initialize the daily forecast with the first occurrence of the day
                        daily_forecast[forecast_day] = {'min_temp': forecast['main']['temp'],
                                                        'max_temp': forecast['main']['temp'],
                                                        'description': forecast['weather'][0]['description']}
                    else:
                        # Update the min and max temperatures for the day if necessary
                        min_temp = min(daily_forecast[forecast_day]['min_temp'], forecast['main']['temp'])
                        max_temp = max(daily_forecast[forecast_day]['max_temp'], forecast['main']['temp'])
                        daily_forecast[forecast_day]['min_temp'] = min_temp
                        daily_forecast[forecast_day]['max_temp'] = max_temp

            for day, forecast in daily_forecast.items():
                # Display the daily forecast information
                print(f"\n{day}")
                print(f"Lowest Temperature: {forecast['min_temp']} °C")
                print(f"Highest Temperature: {forecast['max_temp']} °C")
                print(f"Weather Description: {forecast['description']}")
        else:
            print("Failed to fetch weather forecast data")


app = WeatherForecastApp()
app.run()
