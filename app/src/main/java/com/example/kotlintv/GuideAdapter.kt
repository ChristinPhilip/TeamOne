
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintv.GuideFragment
import com.example.kotlintv.GuideInfoList
import com.example.kotlintv.R

class GuideAdapter(
    private val mList: ArrayList<GuideInfoList>,
    private val context: FragmentActivity?,
    private val mFocusCallBack: GuideFragment
) :
    RecyclerView.Adapter<GuideAdapter.ViewHolder>() {

    private lateinit var mValue: String
    private var mIndex: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.guide_item, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rlMain.visibility = View.VISIBLE
        if(position == mIndex){
            if (holder.rlMain.getChildAt(position) != null)
                holder.rlMain.getChildAt(position).requestFocus()
            if (context != null) {
                updateExpandCard(holder, context, position)
            }
        }
        holder.rlMain.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                if (context != null) {
                    updateExpandCard(holder, context, position)
                }
            } else {
                if (context != null) {
                    holder.rlMain.background =(context.getDrawable(R.drawable.non_focused))
                }
                holder.rlExpand.visibility= View.GONE
                holder.rlCollapse.visibility= View.VISIBLE
            }

        }

        Glide.with(context!!)
            .load(mList[position].backgroundcardImageUrl)
            .centerCrop()
            .error(context.getDrawable(R.drawable.movie))
            .into(holder.channel_logo_small)

        holder.textCollapse.text = mList[position].showName

        holder.rlMain.setOnKeyListener(View.OnKeyListener setOnKeyListener@{ v: View, keyCode: Int, event: KeyEvent ->
            if(keyCode == KeyEvent.KEYCODE_DPAD_UP && ( position == 0 || position == 1 || position == 2 || position == 3)){
                mFocusCallBack.updateFocus()
            } else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT|| keyCode == KeyEvent
                    .KEYCODE_DPAD_LEFT|| keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                mFocusCallBack.updateTimeSlotText(position)
            }
            return@setOnKeyListener false
        })
    }

    private fun updateExpandCard(
        holder: ViewHolder,
        context: FragmentActivity,
        position: Int
    ) {
        holder.rlMain.background = (context.getDrawable(R.drawable.focussed_card))
        holder.rlExpand.visibility = View.VISIBLE
        holder.rlCollapse.visibility = View.GONE
        holder.textExpand.text = "StartTime : " + mList[position].startTime
        holder.tv_min_left.text = "TimeLeft : " + mList[position].timeLeft
        holder.tv_show_name.text = mList[position].showName
        holder.tv_season.text = mList[position].episode
        holder.tv_desc.text = mList[position].description
        holder.tv_duration.text = "Duration : " + mList[position].duration
        Glide.with(context)
            .load(mList[position].cardImageUrl)
            .centerCrop()
            .error(context.getDrawable(R.drawable.movie))
            .into(holder.channel_logo_large)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateText(value: String, index: Int) {
        mValue = value
        mIndex = index
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val channel_logo_small: ImageView = itemView.findViewById(R.id.channel_logo_small)
        val channel_logo_large: ImageView = itemView.findViewById(R.id.channel_logo)
        val textExpand: TextView = itemView.findViewById(R.id.tv_time)
        val tv_min_left: TextView = itemView.findViewById(R.id.tv_min_left)
        val tv_show_name: TextView = itemView.findViewById(R.id.tv_show_name)
        val tv_season: TextView = itemView.findViewById(R.id.tv_season)
        val tv_desc: TextView = itemView.findViewById(R.id.tv_desc)
        val tv_duration: TextView = itemView.findViewById(R.id.tv_duration)

        val textCollapse: TextView = itemView.findViewById(R.id.txt_collapse)
        val rlMain: RelativeLayout = itemView.findViewById(R.id.main_container)
        val rlCollapse: RelativeLayout = itemView.findViewById(R.id.container_collapse)
        val rlExpand: RelativeLayout = itemView.findViewById(R.id.container_expand)
    }
}
