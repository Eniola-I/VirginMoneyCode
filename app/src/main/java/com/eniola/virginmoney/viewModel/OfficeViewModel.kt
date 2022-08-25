package com.eniola.virginmoney.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniola.virginmoney.api.OfficeRepository
import com.eniola.virginmoney.model.Colleagues
import com.eniola.virginmoney.model.ColleaguesItem
import com.eniola.virginmoney.util.UserInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class OfficeViewModel(
    private val officeRepository: OfficeRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher),
) : CoroutineScope by coroutineScope, ViewModel() {


    var colleagueData = MutableLiveData<List<ColleaguesItem>>()
    private var _allColleagues: MutableLiveData<UserInterface> =
        MutableLiveData(UserInterface.LOADING())
    val allColleagues: LiveData<UserInterface> get() = _allColleagues

    private var _allOffices: MutableLiveData<UserInterface> =
        MutableLiveData(UserInterface.LOADING())
    val allOffices: LiveData<UserInterface> get() = _allOffices

    fun getAllColleagues() {
        collectColleagueInfo()
        launch {
            officeRepository.getColleagueList()
        }
    }

    fun getAllOffices() {
        collectOfficeInfo()
        launch {
            officeRepository.getOfficeList()
        }
    }

    private fun collectColleagueInfo() {
        launch {
            officeRepository.colleagueList.collect { UserInterface ->
                when (UserInterface) {
                    is UserInterface.LOADING -> {
                        _allColleagues.postValue(UserInterface)
                    }
                    is UserInterface.COLLEAGUEOUTCOME -> {
                        _allColleagues.postValue(UserInterface)
                    }
                    is UserInterface.ERRORMESSAGE -> {
                        _allColleagues.postValue(UserInterface)
                    }
                }
            }
        }
    }

    private fun collectOfficeInfo() {
        launch {
            officeRepository.officeList.collect { UserInterface ->
                when (UserInterface) {
                    is UserInterface.LOADING -> {
                        _allOffices.postValue(UserInterface)
                    }
                    is UserInterface.OFFICEOUTCOME -> {
                        _allOffices.postValue(UserInterface)
                    }
                    is UserInterface.ERRORMESSAGE -> {
                        _allOffices.postValue(UserInterface)
                    }
                }
            }
        }
    }
}

