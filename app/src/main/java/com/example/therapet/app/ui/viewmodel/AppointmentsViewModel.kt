package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.repository.contracts.AppointmentRepositoryContract
import com.example.therapet.app.data.session.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val repository: AppointmentRepositoryContract,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun addAppointment(
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    ) {
        val session = sessionManager.session.value ?: return

        viewModelScope.launch {
            repository.createAppointment(
                therapistUserId = session.userid,
                dateTimeMillis = dateTimeMillis,
                appointmentType = appointmentType
            )
        }
    }

    fun updateAppointment(appointment: AppointmentEntity) {
        viewModelScope.launch {
            repository.updateAppointment(appointment)
        }
    }

    fun deleteAppointment(appointment: AppointmentEntity) {
        viewModelScope.launch {
            repository.deleteAppointment(appointment)
        }
    }

    fun getAppointmentsForTherapist(): Flow<List<AppointmentEntity>> {
        val therapistId = sessionManager.session.value?.userid
            ?: return flowOf(emptyList())

        return repository.getAppointmentsForTherapist(therapistId)
    }
}
