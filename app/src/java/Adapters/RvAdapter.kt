

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.room5.Entity.Customer
import com.ilhomjon.room5.databinding.ItemRvBinding

class CustomerAdapter(val list: List<Customer>) : RecyclerView.Adapter<CustomerAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(customer: Customer) {
            itemRv.txtName.text = customer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}