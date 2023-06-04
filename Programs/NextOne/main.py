import requests
import json
import datetime
import time

API_KEY = 'e8f3dab029ed6fb1ccaef6dc3a204ff7'

CACHE_DURATION = 600  # Cache duration in seconds (10 minutes)


class WeatherForecastApp:
    def __init__(self):
        self.cache = {}

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
        city_name = weather_data['name']
        country_name = weather_data['sys']['country']
        print(f"\nLocation: {city_name}, {country_name}")

    def get_weather_data(self, location):
        if location in self.cache:
            cached_data = self.cache[location]
            if time.time() - cached_data['timestamp'] < CACHE_DURATION:
                return cached_data['data']

        url = f'http://api.openweathermap.org/data/2.5/weather?q={location}&appid={API_KEY}&units=metric'
        response = requests.get(url)
        if response.status_code == 200:
            weather_data = json.loads(response.text)
            self.cache[location] = {'timestamp': time.time(), 'data': weather_data}
            return weather_data
        else:
            print(f"Failed to fetch weather data for {location}")
            return None

    def display_weather(self, weather_data):
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
        url = f'http://api.openweathermap.org/data/2.5/forecast?id={location_id}&appid={API_KEY}&units=metric'
        response = requests.get(url)
        if response.status_code == 200:
            forecast_data = json.loads(response.text)
            print("\nWeather Forecast:")
            daily_forecast = {}
            for forecast in forecast_data['list']:
                forecast_time = datetime.datetime.fromtimestamp(forecast['dt'])
                forecast_day = forecast_time.strftime('%Y-%m-%d')
                if forecast_day not in daily_forecast:
                    daily_forecast[forecast_day] = {'min_temp': forecast['main']['temp'],
                                                    'max_temp': forecast['main']['temp'],
                                                    'description': forecast['weather'][0]['description']}
                else:
                    min_temp = min(daily_forecast[forecast_day]['min_temp'], forecast['main']['temp'])
                    max_temp = max(daily_forecast[forecast_day]['max_temp'], forecast['main']['temp'])
                    daily_forecast[forecast_day]['min_temp'] = min_temp
                    daily_forecast[forecast_day]['max_temp'] = max_temp

            for day, forecast in daily_forecast.items():
                print(f"\n{day}")
                print(f"Lowest Temperature: {forecast['min_temp']} °C")
                print(f"Highest Temperature: {forecast['max_temp']} °C")
                print(f"Weather Description: {forecast['description']}")
        else:
            print("Failed to fetch weather forecast data")
app = WeatherForecastApp()
app.run()
