package by.coolightman.weather.data.remote.service

import by.coolightman.weather.BuildConfig
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.util.API_ICON_SET_2
import by.coolightman.weather.util.API_ICON_SET_PARAM
import by.coolightman.weather.util.API_KEY_PARAM
import by.coolightman.weather.util.API_LANG_EN
import by.coolightman.weather.util.API_LANG_PARAM
import by.coolightman.weather.util.API_PLACE_PATH
import by.coolightman.weather.util.API_UNIT_METRIC
import by.coolightman.weather.util.API_UNIT_PARAM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{$API_PLACE_PATH}")
    suspend fun getWeatherStamp(
        @Path(API_PLACE_PATH) place: String,
        @Query(API_KEY_PARAM) key: String = BuildConfig.API_KEY,
        @Query(API_UNIT_PARAM) unit: String = API_UNIT_METRIC,
        @Query(API_LANG_PARAM) lang: String = API_LANG_EN,
        @Query(API_ICON_SET_PARAM) icons: String = API_ICON_SET_2
    ): Response<WeatherStampDto>
}