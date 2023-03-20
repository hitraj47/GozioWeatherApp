package com.gozio.weather.weapose.currentweather.composables

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.NEXUS_5
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gozio.weather.weapose.R
import com.gozio.weather.weapose.common.composables.LoadingSpinner
import com.gozio.weather.weapose.common.composables.SimpleMessageDialog
import com.gozio.weather.weapose.common.theme.WeaposeTheme
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewData
import com.gozio.weather.weapose.currentweather.data.CurrentWeatherViewState
import com.gozio.weather.weapose.currentweather.data.factory.previewCurrentWeatherViewData
import com.gozio.weather.weapose.currentweather.utils.Constants
import com.gozio.weather.weapose.currentweather.viewmodels.CurrentWeatherViewModel

@Composable
fun CurrentWeather(
    appState: WeatherAppState,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.observeAsState(CurrentWeatherViewState.Loading)

    LaunchedEffect(key1 = null, block = {
        viewModel.retrieveCurrentWeatherByCoordinates(
            Constants.Default.LAT_LNG_DEFAULT.latitude.toString(),
            Constants.Default.LAT_LNG_DEFAULT.longitude.toString()
        )
    })
    CurrentWeatherScreen(
        state = state
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentWeatherScreen(
    state: CurrentWeatherViewState,
) {
    when (state) {
        is CurrentWeatherViewState.Loading -> LoadingSpinner(text = stringResource(id = R.string.loading_message))
        is CurrentWeatherViewState.Success -> Column {
            CurrentWeatherAppBar(city = state.currentWeather.city)
            HomeContent(
                currentWeather = state.currentWeather
            )
        }
        CurrentWeatherViewState.Error -> SimpleMessageDialog(message = stringResource(id = R.string.default_error_message))
    }
}

@Composable
fun HomeContent(
    currentWeather: CurrentWeatherViewData,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        NowWeather(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentWeather = currentWeather,
        )

        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(
                    top = 50.dp,
                    bottom = 30.dp,
                )
        )
    }
}

@Composable
fun NowWeather(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeatherViewData,
    isMetric: Boolean = false
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = currentWeather.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .weight(1f),
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.FillHeight,
        )

        Column(
            modifier = Modifier
                .padding(end = 15.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    id = if (isMetric) R.string.home_text_celsius_high_low else R.string.home_text_fahrenheit_high_low,
                    currentWeather.maxTemp,
                    currentWeather.minTemp,
                ),
            )

            Degrees(
                currentWeather = currentWeather.weather,
                currentTemp = currentWeather.temp,
            )

            DetailWeather(
                iconId = R.drawable.ic_sun_rise,
                title = stringResource(id = R.string.sun_rise),
                description = currentWeather.sunRise,
            )

            DetailWeather(
                iconId = R.drawable.ic_wind,
                title = stringResource(id = R.string.wind),
                description = stringResource(
                    id = R.string.home_text_meter_per_second,
                    currentWeather.wind
                ),
            )

            DetailWeather(
                iconId = R.drawable.ic_humidity,
                title = stringResource(id = R.string.humidity),
                description = stringResource(
                    id = R.string.home_text_humidity,
                    currentWeather.humidity
                ),
            )
        }
    }
}

@Composable
fun DetailWeather(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 5.dp),
        )

        Text(
            text = description,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    city: String? = null,
    onNavigateSearch: () -> Unit = {},
) {
    SmallTopAppBar(
        modifier = modifier,
        title = {
            if (title.isNotBlank()) {
                Text(text = title, maxLines = 1, overflow = TextOverflow.Visible)
            }
        },
        actions = {
            Card(
                onClick = onNavigateSearch,
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Sharp.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 5.dp),
                    )

                    Text(
                        text = city ?: stringResource(id = R.string.unknown_address),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        },
    )
}

@Composable
fun Degrees(
    modifier: Modifier = Modifier,
    currentTemp: String,
    currentWeather: String,
    isMetric: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = currentTemp,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.alignBy(LastBaseline),
            )

            Column(modifier = Modifier.alignBy(LastBaseline)) {
                Text(text = "o", modifier = Modifier.padding(bottom = 10.dp))

                if (isMetric) {
                    Text(text = "C")
                } else {
                    Text(text = "F")
                }
            }
        }

        Text(
            text = currentWeather,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 22.sp),
        )
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NowWeatherPreview() {
    WeaposeTheme {
        NowWeather(
            modifier = Modifier.size(500.dp),
            currentWeather = previewCurrentWeatherViewData(),
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DegreesPreview() {
    WeaposeTheme {
        Degrees(
            currentWeather = previewCurrentWeatherViewData().weather,
            currentTemp = previewCurrentWeatherViewData().temp,
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppBarPreview() {
    WeaposeTheme {
        CurrentWeatherAppBar(city = previewCurrentWeatherViewData().city)
    }
}

@Preview(name = "Light", showBackground = true, device = NEXUS_5)
@Preview(
    name = "Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = PIXEL_4_XL
)
@Composable
private fun ScreenPreview() {
    WeaposeTheme {
        CurrentWeatherScreen(
            CurrentWeatherViewState.Success(
                CurrentWeatherViewData(
                    "city",
                    "max temp",
                    "min temp",
                    "temp",
                    "weather",
                    "sunRise",
                    "wind",
                    "humidity",
                    R.drawable.bg_rain
                )
            )
        )
    }
}

