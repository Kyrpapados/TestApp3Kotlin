package kyrpap.testapp3kotlin.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_trend.view.*
import kyrpap.testapp3kotlin.R
import kyrpap.testapp3kotlin.data.model.local.Trend

class TrendAdapter(private val mContext: Context, private val dataList : List<Trend>,  private val listener : Listener) : RecyclerView.Adapter<TrendAdapter.ViewHolder>(){
    interface Listener {

        fun onItemClick(trend: Trend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trend, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position],mContext, listener)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(trend: Trend, mContext: Context, listener: Listener) {

            itemView.name.text = trend.name

            Glide.with(mContext)
                    .applyDefaultRequestOptions(RequestOptions().override(200, 200))
                    .load(trend.owner.avatar)
                    .into(itemView.thumb)

            itemView.setOnClickListener{ listener.onItemClick(trend) }
        }
    }
}