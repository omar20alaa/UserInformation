package app.userinformation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.userinformation.R
import app.userinformation.adapter.UserListAdapter
import app.userinformation.database.MyApplication
import app.userinformation.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDataActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)
        initUi()
    }

    private fun initUi() {
        userDao = MyApplication.database.userDao()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UserListAdapter()
        recyclerView.adapter = adapter
        loadUsers(adapter)
    }

    private fun loadUsers(adapter: UserListAdapter) {
        GlobalScope.launch(Dispatchers.Main) {
            val users = userDao.getAllUsers()
            adapter.setUsers(users)
        }
    }
}