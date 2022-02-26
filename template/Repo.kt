package  (package).database.repositories

import androidx.lifecycle.LiveData
import (package).database.daos.TemplateDao
import (package).entities.TemplateEntity

/**
 * Created by Jamie Pezone on 09/12/2019.
 * Ontrac Ltd
 * jamie.pezone@on-trac.co.uk
 */
class TemplateRepo(private val dao: TemplateDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTemplates: LiveData<List<TemplateEntity>> = dao.getAll()

    fun insert(template: TemplateEntity) {
        dao.insert(template)
    }

    fun findByOtherId(other_id: Long): LiveData<TemplateEntity> {
        return dao.findByOtherId(other_id)
    }
    fun findById(id: Long): LiveData<TemplateEntity> {
        return dao.findById(id)
    }

    fun insertOrUpdate(template: TemplateEntity):Long {
        return when (dao.countByOtherId(template.other_id) > 0) {
            true -> {
                template.id=dao.idByOtherId(template.other_id)
                dao.update(template)
                dao.idByOtherId(template.other_id)
            }
            false -> dao.insert(template)
        }
    }
    fun delete(other_id: Long) {
        dao.deleteWithOtherid(other_id)
    }
}