package app.userinformation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.userinformation.R
import app.userinformation.database.UserEntity

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val users: MutableList<UserEntity> = mutableListOf()

    fun setUsers(usersList: List<UserEntity>) {
        users.clear()
        users.addAll(usersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        private val jobTitleTextView: TextView = itemView.findViewById(R.id.jobTitleTextView)
        private val genderTextView: TextView = itemView.findViewById(R.id.genderTextView)

        fun bind(user: UserEntity) {
            nameTextView.text = "Name: ${user.name}"
            ageTextView.text = "Age: ${user.age}"
            jobTitleTextView.text = "Job Title: ${user.jobTitle}"
            genderTextView.text = "Gender: ${user.gender}"
        }
    }
}