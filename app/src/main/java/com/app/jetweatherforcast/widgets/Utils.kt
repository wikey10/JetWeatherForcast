package com.app.jetweatherforcast.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.jetweatherforcast.R
import com.app.jetweatherforcast.model.WeatherItem
import com.app.jetweatherforcast.model.WeatherModel
import com.app.jetweatherforcast.utils.formatDateTime
import com.app.jetweatherforcast.utils.formatDecimals

@Composable
fun WeatherStateImage(imageUrl:String){
    Image(painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "",
        modifier = Modifier.size(80.dp)
    )
}


@Composable
fun HumidityWindPressureRow(weather: WeatherModel, isImperial: Boolean){
    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(weather.list[0].main.humidity.toString()+" %",
                style = MaterialTheme.typography.labelMedium)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(weather.list[0].main.pressure.toString()+" psi",
                style = MaterialTheme.typography.labelMedium)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(weather.list[0].wind.speed.toString()+ if(isImperial) "mph"
                else "m/s",
                style = MaterialTheme.typography.labelMedium)

        }
    }
}

@Composable
fun SunriseSunsetRow(weather: WeatherModel){
    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                formatDateTime(weather.city.sunrise),
                style = MaterialTheme.typography.labelMedium)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                formatDateTime(weather.city.sunset),
                style = MaterialTheme.typography.labelMedium)
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun WeekListRow(item: List<WeatherItem>){
    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        color = Color(0XFFEE1EF),
        shape = RoundedCornerShape(2.dp)
        ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(item){
                    item -> Card(
                modifier = Modifier.fillMaxWidth().padding(6.dp)
            ){
                Text(formatDateTime(item.dt), modifier = Modifier.padding(4.dp ))
                val weather = item.weather.firstOrNull()
                if(weather!=null){
                    val imageUrl = "https://openweathermap.org/img/wn/${weather.icon}.png"
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                        WeatherStateImage(imageUrl = imageUrl)
                        Surface(modifier = Modifier.padding(0.dp),
                            shape = CircleShape,
                            color = Color(0XFFFFC400)
                        ) {
                            Text(weather.description, modifier = Modifier.padding(5.dp))
                        }

                        Text(formatDecimals(item.main.temp_min) +" °",
                            color = Color.Blue,
                            style = MaterialTheme.typography.titleLarge
                            )
                        Text(formatDecimals(item.main.temp_max) +" °")
                    }
                }

            }
            }
        }
    }
}