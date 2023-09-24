package com.mc7.myhiltinjectionapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mc7.myhiltinjectionapp.ui.viewmodel.DetailViewModel
import com.mc7.myhiltinjectionapp.R
import com.mc7.myhiltinjectionapp.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var rand: String = "defaultName"
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (!viewModel.isFilledUser()){
            viewModel.getDetailUser().observe(this){ result ->
                if (result != null){
                    when (result){
                        is Result.Loading -> {
                            rand = "Loading"
                            viewModel.saveUsername(rand)
                        }
                        is Result.Success -> {
                            rand = result.data.login.toString()
                            viewModel.saveUsername(rand)
                        }
                        is Result.Error -> {
                            rand = "Error"
                            viewModel.saveUsername(rand)
                        }
                    }
                }
            }
        }

        viewModel.isUsername.observe(this){
            findViewById<TextView>(R.id.tv_username).text = it
        }
    }
}