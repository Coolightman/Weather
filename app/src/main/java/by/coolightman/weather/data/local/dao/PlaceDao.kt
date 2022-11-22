package by.coolightman.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import by.coolightman.weather.data.local.modelDb.PlaceDb
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(placeDb: PlaceDb): Long

    @Query("SELECT * FROM placedb")
    fun getAllFlow(): Flow<List<PlaceDb>>

    @Update
    suspend fun update(placeDb: PlaceDb)

    @Update
    suspend fun updateList(list: List<PlaceDb>)

    @Query("DELETE FROM placedb WHERE id = :id")
    suspend fun delete(id: Long)
}