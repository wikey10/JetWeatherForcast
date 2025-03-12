![Screenshot_1741765869](https://github.com/user-attachments/assets/8b0df34f-a9a8-44dd-90be-74a5bb24a8af)# 🌦️ Weather App  

A simple Kotlin-based weather application that allows users to check the weather of any city, add favorite cities, and toggle between Celsius and Fahrenheit.  

## 🚀 Features  
- 🔍 Search for weather by city name  
- ⭐ Add cities to your favorites for quick access  
- 🌡️ Toggle between Celsius and Fahrenheit  
- 🗄️ Uses **Room Database** to store favorite cities  
- 🌍 Fetches real-time weather data from **OpenWeather API**  

## 🛠️ Tech Stack  
- **Kotlin** (Jetpack Compose for UI)  
- **Retrofit** (for API calls)  
- **Room Database** (for storing favorite cities)  
- **Hilt** (for Dependency Injection)  
- **Coroutines & Flow** (for async data handling)  

## 🔧 Setup  
1. Clone the repository:  
   ```sh
   git clone <your-repository-url>
   cd <your-project-folder>
2. Get an API key from OpenWeather
3. Add your API key to Constants.kt:

object Constants {
    const val ApiKey = "YOUR_API_KEY_HERE"
}

4. Build and run the app in Android Studio
📸 Screenshots

![Screenshot_1741765869](https://github.com/user-attachments/assets/35b28baa-ff68-409e-a093-0e3a78475b27)

![Screenshot_1741765875](https://github.com/user-attachments/assets/6eaf16aa-2e33-44dc-b866-d65087a9a73f)



📜 License
This project is open-source. Feel free to use and modify it.


