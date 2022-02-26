package  (package).database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import (package)database.entities.TemplateEntity


@Dao
interface TemplateDao {
    @Query("SELECT * FROM TemplateEntity")
    fun getAll(): LiveData<List<TemplateEntity>>
    /**
     * Get entity by id.
     * @return the entity from the table with a specific id.
     */
    @Query("SELECT * FROM TemplateEntity WHERE id = :id")
    fun findById(id: Long): LiveData<TemplateEntity>

    @Query("SELECT * FROM TemplateEntity WHERE pack_id = :pack_id")
    fun findByOtherId(pack_id: Long): LiveData<TemplateEntity>

    @Query("SELECT count(*) FROM TemplateEntity WHERE pack_id = :pack_id")
    fun countByOtherId(pack_id: String?): Int

    @Query("SELECT * FROM TemplateEntity WHERE pack_id = :pack_id")
    fun idByOtherId(pack_id: String?): Long

    /**
     * Insert an entity in the database. If it already exists, replace it.
     * @param TemplateEntity the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(template: TemplateEntity) :Long

    @Insert
    fun insertAll(vararg templates: TemplateEntity)

    @Delete
    fun delete(template: TemplateEntity)

    @Update
    fun update(vararg template: TemplateEntity)

    @Query("DELETE FROM TemplateEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM TemplateEntity WHERE pack_id = :pack_id")
    fun deleteWithOtherid(pack_id: Long)
}