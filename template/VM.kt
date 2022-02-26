package (package).database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import (package).database.SWPDatabase.Companion.getDatabase
import (package).database.entities.TemplateEntity
import (package).database.repositories.TemplateRepo

/**
 * Created by Jamie Pezone on 09/12/2019.
 * Ontrac Ltd
 * jamie.pezone@on-trac.co.uk
 */
class TemplateVM(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repo: TemplateRepo
    // LiveData gives us updated words when they change.
    private val allTemplates: LiveData<List<TemplateEntity>>

    init {
        val templateDao = getDatabase(application, viewModelScope).TemplateDao()
        repo = TemplateRepo(templateDao)
        allTemplates = repo.allTemplates
    }

    fun insertOrUpdate(template: TemplateEntity):Long{
        return repo.insertOrUpdate(template)
    }

    fun findByOtherId(other_id: Long): LiveData<TemplateEntity> {
        return repo.findByOtherId(other_id)
    }
    fun findById(id: Long): LiveData<TemplateEntity> {
        return repo.findById(other_id)
    }
    fun delete(other_id: Long) {
        return repo.delete(other_id)
    }
}