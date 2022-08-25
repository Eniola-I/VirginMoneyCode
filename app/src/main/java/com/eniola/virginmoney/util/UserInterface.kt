package com.eniola.virginmoney.util

import com.eniola.virginmoney.model.ColleaguesItem
import com.eniola.virginmoney.model.OfficeItem

sealed class UserInterface {
    class LOADING(): UserInterface()
    class COLLEAGUEOUTCOME(val colleagues : List<ColleaguesItem>): UserInterface()
    class OFFICEOUTCOME(val office: List<OfficeItem>): UserInterface()
    class ERRORMESSAGE(val error: Throwable): UserInterface()
}
