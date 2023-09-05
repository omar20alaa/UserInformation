package app.userinformation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.userinformation.database.UserDao
import app.userinformation.database.UserEntity
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userDao: UserDao) : ViewModel()  {

    fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }
}