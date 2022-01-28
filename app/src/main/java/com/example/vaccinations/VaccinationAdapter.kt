package com.example.vaccinations

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccinationAdapter(var dataSet: List<Vaccination>) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCountry: TextView
        val textViewDate : TextView
        val textViewTimeline : TextView
        val layout: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            textViewCountry = view.findViewById(R.id.textView_vaccinationdetail_country)
            textViewDate = view.findViewById(R.id.textView_vaccinationdetail_timeline)
            textViewTimeline = view.findViewById(R.id.textView_vaccinationdetail_vaccinations)
            layout = view.findViewById(R.id.layout_vaccinationItem)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_vaccination, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val vaccination = dataSet[position]
        viewHolder.textViewCountry.text = vaccination.country.toString()
        viewHolder.textViewDate.text = vaccination.timeline
        viewHolder.layout.setOnClickListener {
            //Toast.makeText(it.context, "Hi, you clicked on ${hero.name}", Toast.LENGTH_SHORT).show()
            //get the context from something in the viewHolder
            val context = viewHolder.layout.context
            val heroDetailIntent = Intent(context, VaccinationDetailActivity::class.java).apply {
                putExtra(VaccinationDetailActivity.EXTRA_COUNTRY, vaccination)
            }
            context.startActivity(heroDetailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}