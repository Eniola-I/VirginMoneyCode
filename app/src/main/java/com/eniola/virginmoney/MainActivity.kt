package com.eniola.virginmoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eniola.virginmoney.adapter.TabAdapter
import com.eniola.virginmoney.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var titleTab = arrayOf("Colleague", "Office")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewTab = binding.viewTab
        val contentTab = binding.contentTab
        viewTab.adapter = TabAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(contentTab,viewTab){
            tab, position ->
            tab.text = titleTab[position]
        }.attach()

    }
}