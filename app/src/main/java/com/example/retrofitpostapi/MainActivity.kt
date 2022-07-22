package com.example.retrofitpostapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpostapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        binding.buttonCreate.setOnClickListener {
            createUser()
        }
    }

    private fun createUser(){
        val user = User("",binding.editTextName.text.toString(), binding.editTextEmail.text.toString(),"Active","Male")
        viewModel.createNewUser(user)

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer<UserResponse?>{

            if(it == null){
                Toast.makeText(this@MainActivity, "FAILD",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this@MainActivity, "Success",Toast.LENGTH_SHORT).show()
            }

        })
    }
}