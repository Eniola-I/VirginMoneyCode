package com.eniola.virginmoney.api

import com.eniola.virginmoney.util.UserInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalStateException

interface OfficeRepository {
    val colleagueList: StateFlow<UserInterface>
    suspend fun getColleagueList()

    val officeList: StateFlow<UserInterface>
    suspend fun getOfficeList()
}

class OfficeRepositoryImpl(
    private val officeApi: OfficeApi
 ): OfficeRepository{

    private val  _colleagueList : MutableStateFlow<UserInterface> = MutableStateFlow(UserInterface.LOADING())
    override val colleagueList: StateFlow<UserInterface>
        get() = _colleagueList

    private val _officeList : MutableStateFlow<UserInterface> = MutableStateFlow(UserInterface.LOADING())
        override val officeList: StateFlow<UserInterface>
        get() = _officeList

    override suspend fun getColleagueList() {
        try {
            val result = officeApi.getColleaguesInfo()
            if (result.isSuccessful) {
                result.body()?.let { ColleaguesItem ->
                    _colleagueList.value = UserInterface.COLLEAGUEOUTCOME(ColleaguesItem)
                } ?: run {
                    _colleagueList.value =
                        UserInterface.ERRORMESSAGE(IllegalStateException("List is null"))
                }
            }
            else { _colleagueList.value = UserInterface.ERRORMESSAGE(Exception(result.errorBody()?.toString()))
                }

            }catch(e: Exception){
                _colleagueList.value = UserInterface.ERRORMESSAGE(e)
            }
        }

    override suspend fun getOfficeList() {
       try{
           val result = officeApi.getOfficeInfo()
           if (result.isSuccessful){
               result.body()?.let {
                  _officeList.value =
                  UserInterface.OFFICEOUTCOME(it)
               } ?: UserInterface.ERRORMESSAGE(Throwable("List is empty"))
           }
           else { _officeList.value = UserInterface.ERRORMESSAGE(Exception(result.errorBody()?.toString()))
           }
       } catch (e: Exception) {
           _officeList.value = UserInterface.ERRORMESSAGE(e)
       }
    }
}