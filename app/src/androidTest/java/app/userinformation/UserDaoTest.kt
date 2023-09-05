package app.userinformation

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.userinformation.database.AppDatabase
import app.userinformation.database.UserDao
import app.userinformation.database.UserEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = appDatabase.userDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertUser_retrievesUser() = runBlocking {
        val user = UserEntity(1,"Omar Alaa",
            25, "Developer", "Male")
        userDao.insertUser(user)

        val retrievedUser = userDao.getAllUsers()[0]

        // Compare individual properties of the retrievedUser with the user
        assertThat(retrievedUser.name, equalTo(user.name))
        assertThat(retrievedUser.age, equalTo(user.age))
        assertThat(retrievedUser.jobTitle, equalTo(user.jobTitle))
        assertThat(retrievedUser.gender, equalTo(user.gender))
    }
}