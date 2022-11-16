package com.example.lesson_18_hw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.*

class ListFragment : Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
    }

    private fun setListeners() {
        btn_launchSecondCoroutine.setOnClickListener {
            val coroutineTask = CoroutineScope(Dispatchers.IO).async {
                sayHelloKotlin()
            }

            CoroutineScope(Dispatchers.IO).launch {
                val result = coroutineTask.await()
                withContext(Dispatchers.Main) {
                    btn_launchSecondCoroutine.text = result
                }
            }
        }

        btn_launchFirstCoroutine.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(requireContext())
                    .load("https://media1.giphy.com/media/5ZYBuyZC9uubrIIYXf/" +
            "giphy.gif?cid=790b7611363bb2bed0a9dbeddc93446da12c4f75f64ea121&rid=giphy.gif&ct=g")
                    .into(ivPicture)
            }
        }

    }

    private fun sayHelloKotlin() : String {
        return "It's Kotlin!"
    }

}