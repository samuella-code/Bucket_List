package com.hfad.bucket_lite_sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.bucket_lite_sql.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDatabaseHelper
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = TaskDatabaseHelper(this)
        taskAdapter = TaskAdapter(db.getAllTasks(), this)

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = taskAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
        binding.aboutUsImageView.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java )
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getAllTasks())


    }

}