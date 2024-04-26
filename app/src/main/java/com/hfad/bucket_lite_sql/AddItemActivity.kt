package com.hfad.bucket_lite_sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hfad.bucket_lite_sql.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private lateinit var db: TaskDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)
       // binding.completedCheckBox.isChecked


        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }



        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
           // val complete = binding.completedCheckBox.isChecked
            val task = Task(0, title, content)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
        }
    }
}