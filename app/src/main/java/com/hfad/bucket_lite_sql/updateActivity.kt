package com.hfad.bucket_lite_sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hfad.bucket_lite_sql.databinding.ActivityUpdateBinding

class updateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TaskDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        binding.returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }



        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1){
            finish()
            return
        }
        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updatecontentEditText.setText(task.content)
        binding.completedCheckBoxs.isChecked

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val newComplete = binding.completedCheckBoxs.isChecked
            val updateTask = Task(taskId, newTitle,newContent,newComplete)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Task Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}