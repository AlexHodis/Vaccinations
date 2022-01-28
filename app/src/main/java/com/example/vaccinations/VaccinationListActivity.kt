package com.example.vaccinations

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccinations.databinding.ActivityVaccinationListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VaccinationListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination_list)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.vaccination)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val type = object : TypeToken<List<Vaccination>>() {}.type
        val heroes = gson.fromJson<List<Vaccination>>(jsonText, type)

        adapter = VaccinationAdapter(heroes)
        binding.recyclerViewVaccinationList.adapter = adapter
        binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this)
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sorting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.country -> {
                Toast.makeText(this, "You sorted by name", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy { it.name }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.rank -> {
                Toast.makeText(this, "You sorted by rank", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sorted()
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    } */
}