package com.example.vaccinations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vaccinations.databinding.ActivityVaccinationDetailBinding

class VaccinationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVaccinationDetailBinding

    companion object{
        val EXTRA_COUNTRY = "country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countryInfo = intent.getParcelableExtra<VaccinationInfo>(EXTRA_COUNTRY)

        //put each field of the Vaccination object into the respecitve widgets
        binding.textViewVaccinationdetailCountry.text = countryInfo?.country
       // binding.textViewVaccinationdetailTimeline.text = countryInfo?.timeline

        binding.textViewVaccinationdetailTimeline.text =
            countryInfo?.timeline?.toList()?.joinToString{
                it.first + ": " + it.second + "\n"
         }?.replace(", ", "")
    }
}