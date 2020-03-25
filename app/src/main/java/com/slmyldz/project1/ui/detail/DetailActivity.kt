package com.slmyldz.project1.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.slmyldz.project1.R
import com.slmyldz.project1.network.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context, user: User): Intent {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("user", user)
            return i
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user: User = intent.getSerializableExtra("user") as User
        setUser(user)
        setToolbar(user)

        fab.setOnClickListener {
            sendEmail(user.email)
        }
    }

    private fun setUser(user:User){
        firstname.setText(user.first_name)
        lastname.setText(user.last_name)
        email.setText(user.email)

    }
    private fun setToolbar(user:User){
        Glide.with(app_bar_image).load(user.avatar).into(app_bar_image)
        toolbar.title = "${user.first_name} ${user.last_name}"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun sendEmail(email: String) {
        val i = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/html"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, "")
            putExtra(Intent.EXTRA_TITLE, "")
        }
        if (packageManager != null && i.resolveActivity(packageManager) != null) {
            startActivity(i)
        }
    }

}
