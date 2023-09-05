package app.userinformation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.userinformation.R
import app.userinformation.database.MyApplication
import app.userinformation.database.UserEntity
import app.userinformation.viewmodel.UserInfoViewModel
import app.userinformation.viewmodel.UserInfoViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private var btnSave: Button? = null
    private var editName: EditText? = null
    private var editAge: EditText? = null
    private var editJobTitle: EditText? = null
    private var editGender: EditText? = null
    private lateinit var viewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUi()
        eventClick()
    }

    private fun initUi() {
        editName = findViewById(R.id.editName)
        editAge = findViewById(R.id.editAge)
        editJobTitle = findViewById(R.id.editJobTitle)
        editGender = findViewById(R.id.editGender)
        btnSave = findViewById(R.id.btnSave)
    }

    private fun eventClick() {
        btnSave!!.setOnClickListener {
            val name = editName!!.text.toString()
            val age = editAge!!.text.toString().toIntOrNull() ?: 0
            val jobTitle = editJobTitle!!.text.toString()
            val gender = editGender!!.text.toString()

            val user = UserEntity(name = name,
                age = age, jobTitle = jobTitle, gender = gender)
            val userDao = MyApplication.database.userDao()
            viewModel = ViewModelProvider(this,
                UserInfoViewModelFactory(userDao))[UserInfoViewModel::class.java]
            viewModel.saveUser(user)
            Toast.makeText(this@RegisterActivity,
                getString(R.string.user_information_saved), Toast.LENGTH_SHORT)
                .show()

            val intent = Intent(this@RegisterActivity, UserDataActivity::class.java)
            startActivity(intent)
        }
    }

}