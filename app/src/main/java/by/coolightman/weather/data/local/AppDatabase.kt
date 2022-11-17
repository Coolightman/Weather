package by.coolightman.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.coolightman.weather.data.local.dao.WeatherDao
import by.coolightman.weather.data.local.modelDb.CurrentConditionsDb
import by.coolightman.weather.data.local.modelDb.DayWeatherDb
import by.coolightman.weather.data.local.modelDb.HourWeatherDb
import by.coolightman.weather.data.local.modelDb.WeatherStampDb

@Database(
    version = 1,
    entities = [
        WeatherStampDb::class,
        CurrentConditionsDb::class,
        DayWeatherDb::class,
        HourWeatherDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}