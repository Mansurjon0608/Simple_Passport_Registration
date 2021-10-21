package Adapter

import Models.Fuqaro
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pasportregister.R
import com.example.pasportregister.databinding.ItemRvBinding


class RvAdapter(val context: Context?, val list: List<Fuqaro>, val rvOnClick: RvOnClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(fuqaro: Fuqaro, position: Int) {
            itemRv.txtItemRvName.text = "${fuqaro.name} ${fuqaro.lastName}"
            itemRv.txtItemRvPassportSeriya.text = fuqaro.passportSeriya
            itemRv.txtItemRvPosition.text = "${position + 1}"

            itemRv.cardItemRv.setOnClickListener {
                rvOnClick.itemOnClick(fuqaro, position)
            }
            itemRv.itemRvMore.setOnClickListener {
                rvOnClick.moreOnClick(fuqaro, position, itemRv.itemRvMore)
            }


            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvOnClick {
    fun itemOnClick(fuqaro: Fuqaro, position: Int)
    fun moreOnClick(fuqaro: Fuqaro, position: Int, v: ImageView)
}
