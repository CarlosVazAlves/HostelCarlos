package carlos.alves.hostelcarlos.ui.results

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import carlos.alves.hostelcarlos.R
import carlos.alves.hostelcarlos.api.HostelDetails
import coil.load

class HostelResultsAdapter internal constructor(private val context: Context) : RecyclerView.Adapter<HostelResultsAdapter.ItemViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var hostels = mutableListOf<HostelDetails>()
    private var currentHostel: MutableLiveData<HostelDetails> = MutableLiveData()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hostelThumbnail: ImageView = itemView.findViewById(R.id.hostel_thumbnail)
        val hostelName: TextView = itemView.findViewById(R.id.hostel_name)
        val hostelLocation: TextView = itemView.findViewById(R.id.hostel_location)
        val hostelLowestPrice: TextView = itemView.findViewById(R.id.hostel_lowest_price)
        val hostelRating: TextView = itemView.findViewById(R.id.hostel_rating)
        val hostelFeatured: TextView = itemView.findViewById(R.id.hostel_featured)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = inflater.inflate(R.layout.hostel_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val noImages = hostels[position].imageLinks.isEmpty()
        if (!noImages) {
            holder.hostelThumbnail.load(hostels[position].imageLinks[0])
        }
        holder.hostelName.text = String.format(context.getString(R.string.name), hostels[position].name)
        holder.hostelLocation.text = String.format(context.getString(R.string.location), hostels[position].district)
        holder.hostelLowestPrice.text = String.format(context.getString(R.string.price), hostels[position].lowestPricePerNight)
        holder.hostelRating.text = String.format(context.getString(R.string.rating), hostels[position].overallRating)
        holder.hostelFeatured.visibility = if (hostels[position].isFeatured) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener {
            currentHostel.value = hostels[position]
        }
    }

    override fun getItemCount(): Int = hostels.size

    fun getCurrentHostel(): MutableLiveData<HostelDetails> {
        return currentHostel
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHostels(hostels: MutableList<HostelDetails>) {
        this.hostels = hostels
        notifyDataSetChanged()
    }
}