package by.coolightman.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.coolightman.weather.data.local.modelDb.CurrentConditionsDb
import by.coolightman.weather.data.local.modelDb.DayWeatherDb
import by.coolightman.weather.data.local.modelDb.HourWeatherDb
import by.coolightman.weather.data.local.modelDb.WeatherStampDb
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertWeatherStamp(stampDb: WeatherStampDb): Long

    @Insert(onConflict = REPLACE)
    suspend fun insertCurrentConditions(conditionsDb: CurrentConditionsDb)

    @Insert(onConflict = REPLACE)
    suspend fun insertDaysForecast(daysWeatherDb: List<DayWeatherDb>)

    @Insert(onConflict = REPLACE)
    suspend fun insertHoursForecast(hoursWeatherDb: List<HourWeatherDb>)

    @Query("SELECT * FROM weatherstampdb")
    suspend fun getAllWeatherStamps(): List<WeatherStampDb>

    @Query("SELECT * FROM weatherstampdb WHERE id = :stampId")
    suspend fun getAllWeatherStamp(stampId: Long): WeatherStampDb

    @Query("SELECT * FROM currentconditionsdb")
    fun getAllCurrentConditions(): Flow<List<CurrentConditionsDb>>

    @Query("SELECT * FROM currentconditionsdb WHERE stampId = :stampId")
    suspend fun getCurrentConditions(stampId: Long): CurrentConditionsDb

    @Query("SELECT * FROM dayweatherdb WHERE stampId = :stampId")
    suspend fun getDays(stampId: Long): List<DayWeatherDb>

    @Query("SELECT * FROM hourweatherdb WHERE stampId = :stampId")
    suspend fun getHours(stampId: Long): List<HourWeatherDb>

    @Query("DELETE FROM weatherstampdb WHERE id = :stampId")
    suspend fun deleteWeatherStamp(stampId: Long)

    @Query("DELETE FROM currentconditionsdb WHERE stampId = :stampId")
    suspend fun deleteCurrentConditions(stampId: Long)

    @Query("DELETE FROM dayweatherdb WHERE stampId = :stampId")
    suspend fun deleteDays(stampId: Long)

    @Query("DELETE FROM hourweatherdb WHERE stampId = :stampId")
    suspend fun deleteHours(stampId: Long)
}