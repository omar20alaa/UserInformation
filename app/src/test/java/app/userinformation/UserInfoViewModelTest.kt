package app.userinformation

import app.userinformation.database.UserDao
import app.userinformation.database.UserEntity
import app.userinformation.viewmodel.UserInfoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserInfoViewModelTest {

    /*
   https://developer.android.com/kotlin/coroutines/test
   Unit testing code that uses coroutines requires some extra attention,
   as their execution can be asynchronous and happen across multiple threads.
   This guide covers how suspending functions can be tested,
   the testing constructs you need to be familiar with, and how to make your code that uses coroutines testable.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var userDao: UserDao

    private lateinit var viewModel: UserInfoViewModel

    @Before
    fun setUp() {
        viewModel = UserInfoViewModel(userDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun saveUser_insertsUserToDao() = runTest {
        val user = UserEntity(1, "John Doe", 25,
            "Developer", "Male")

        viewModel.saveUser(user)

        // Verify that userDao.insertUser(user) is called exactly once
        verify(userDao, times(1)).insertUser(user)
    }

}