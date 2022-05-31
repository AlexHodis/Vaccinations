package com.example.vaccinations

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccinations.databinding.ActivityVaccinationListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VaccinationListActivity : AppCompatActivity() {

    val TAG = "VaccinationListActivity"

    private lateinit var binding: ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // var vaccineList = listOf<VaccinationInfo>()

        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)

        vaccineCall.enqueue(object : Callback<List<VaccinationInfo>> {
            override fun onResponse(
                call: Call<List<VaccinationInfo>>,
                response: Response<List<VaccinationInfo>>
            ) {
                //here yk you got info back from the server
                //So any code that relies upon having this info has to go here
                //setting up the adapter
                Log.d(TAG, "onResponse: ${response.body()}")
                var vaccineList = response.body() ?: listOf<VaccinationInfo>()
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager =
                    LinearLayoutManager(this@VaccinationListActivity)

                /* val country1 = vaccineList[0]
                 val firstDay = country1.timeline.firstKey()
                 val lastDay = country1.timeline.lastKey()
                 country1.timeline.get(firstDay)

                 //could convert the map to a list of pairs and sort that list of pairs by either the key or value

                 country1.timeline.toList().sortedBy {
                     it.second
                 }[0] */
            }

            override fun onFailure(call: Call<List<VaccinationInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        }
        )




        // other call
        val vaccineCall2 = vaccineApi.getCases(10)

        vaccineCall2.enqueue(object : Callback<List<CovidCases>> {
            override fun onResponse(
                call: Call<List<CovidCases>>,
                response: Response<List<CovidCases>>
            ) {
                //here yk you got info back from the server
                //So any code that relies upon having this info has to go here
                //setting up the adapter
                Log.d(TAG, "onResponse Cases: ${response.body()}")

                /* val country1 = vaccineList[0]
                 val firstDay = country1.timeline.firstKey()
                 val lastDay = country1.timeline.lastKey()
                 country1.timeline.get(firstDay)

                 //could convert the map to a list of pairs and sort that list of pairs by either the key or value

                 country1.timeline.toList().sortedBy {
                     it.second
                 }[0] */
            }

            override fun onFailure(call: Call<List<CovidCases>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sorting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.name -> {
                Toast.makeText(this, "You sorted by name", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy { it.country }
                adapter.notifyDataSetChanged()
                true
            }
           R.id.totalvaccnations -> {
                Toast.makeText(this, "You sorted by total vaccinations", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedByDescending { it.timeline.get(it.timeline.lastKey()) }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.largestchange -> {
                Toast.makeText(this, "You sorted by largest 10 day change", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sorted()
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}