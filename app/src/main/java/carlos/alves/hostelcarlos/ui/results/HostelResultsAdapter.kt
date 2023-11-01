package carlos.alves.hostelcarlos.ui.results

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import carlos.alves.hostelcarlos.api.HostelDetails
import carlos.alves.hostelcarlos.databinding.HostelItemBinding
import coil.load

class HostelResultsAdapter internal constructor(private val context: Context) : ListAdapter<HostelDetails, HostelResultsAdapter.ItemViewHolder>(HostelDetailsItemDiffCallback()) {

    private var hostels = mutableListOf<HostelDetails>()
    private var currentHostel: MutableLiveData<HostelDetails> = MutableLiveData()

    inner class ItemViewHolder(private val binding: HostelItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hostel: HostelDetails) {
            binding.apply {
                if (hostel.imageLinks.isNotEmpty()) {
                    hostelThumbnail.load(hostel.imageLinks[0])
                }
                hostelName.text = hostel.formattedName(context)
                hostelLocation.text = hostel.formattedLocation(context)
                hostelLowestPrice.text = hostel.formattedLowestPrice(context)
                hostelRating.text = hostel.formattedRating(context)
                hostelFeatured.visibility = if (hostel.isFeatured) View.VISIBLE else View.GONE
                itemView.setOnClickListener {
                    currentHostel.value = hostel
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = HostelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(hostels[position])
    }

    override fun getItemCount(): Int = hostels.size

    fun getCurrentHostel(): MutableLiveData<HostelDetails> {
        return currentHostel
    }

    fun setHostels(hostels: MutableList<HostelDetails>) {
        this.hostels = hostels
    }

    class HostelDetailsItemDiffCallback : DiffUtil.ItemCallback<HostelDetails>() {
        override fun areItemsTheSame(oldItem: HostelDetails, newItem: HostelDetails): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: HostelDetails, newItem: HostelDetails): Boolean {
            return (
                oldItem.name == newItem.name &&
                oldItem.isFeatured == newItem.isFeatured &&
                oldItem.district == newItem.district &&
                oldItem.imageLinks == newItem.imageLinks &&
                oldItem.lowestPricePerNight == newItem.lowestPricePerNight &&
                oldItem.overallRating == newItem.overallRating &&
                oldItem.overview == newItem.overview
            )
        }
    }
}