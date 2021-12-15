package com.example.kotlintv

import GuideAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.leanback.widget.VerticalGridView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class GuideFragment : Fragment(), FocusCallBack {
    private var isKeyRightPressed: Boolean = false
    private lateinit var mScrollView: HorizontalScrollView
    private lateinit var mTimeslot: LinearLayout
    private lateinit var mRecyclerView: VerticalGridView
    private var mTimeSlot: ArrayList<GuideInfoList> = ArrayList()
    private var focusedPos: Int = 0
    private var selectedPosition: Int = 0
    private lateinit var mGuideAdapter: GuideAdapter
    private lateinit var mFocusCallBack: FocusCallBack
    private lateinit var btn:Button

    private var isKeyDownPressed = false
    private var isKeyClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getJsonData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_guide, container, false)
        mFocusCallBack = this
        mGuideAdapter = GuideAdapter(mTimeSlot, activity, mFocusCallBack as GuideFragment)
        mScrollView = view.findViewById(R.id.scrollView)
        mTimeslot = view.findViewById(R.id.time_slot)
        mRecyclerView = view.findViewById(R.id.vrt_grid_view)
        setTimeSlot()
        mRecyclerView.setNumColumns(4)
        mRecyclerView.setColumnWidth(800)
        mGuideAdapter.updateText(mTimeSlot[0].startTime, 0)
        mRecyclerView.adapter = mGuideAdapter
        mGuideAdapter.notifyDataSetChanged()
        return view
    }


    private fun setTimeSlot() {
        for ((index, _) in mTimeSlot.withIndex()) {
            val btnTime = Button(context)
            val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                180,
                80
            )
            btn=btnTime
            param.setMargins(0, 0, 12, 0)
            btnTime.layoutParams = param
            btnTime.requestFocus()
            btnTime.gravity = Gravity.CENTER
            btnTime.text = mTimeSlot[index].startTime
            btnTime.textSize = 12F
            btnTime.setPadding(20, 0, 20, 0)
            btnTime.setOnKeyListener(View.OnKeyListener setOnKeyListener@{ _: View, keyCode: Int, _: KeyEvent ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                    setSelectedUI(btnTime)
                    isKeyDownPressed = true
                }
                if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                    isKeyRightPressed = true
                }
                return@setOnKeyListener false
            })

            btnTime.setOnFocusChangeListener { view, _ ->
                if (view.hasFocus()) {
                    focusedPos = index
                    if (focusedPos == selectedPosition) {
                        setSelectedUI(btnTime)
                    }
                    setFocusedUI(btnTime)
                } else {
                    setNonFocusedUI(btnTime)
                    if (index == 0 && !isKeyRightPressed) {
                        setSelectedUI(btnTime)
                    }
                    if (isKeyClicked) {
                        setSelectedUI(btnTime)
                        isKeyDownPressed = false
                        isKeyClicked = false
                    }
                }
            }
            btnTime.setOnClickListener {
                isKeyClicked = true
                selectedPosition = index
                updateView(mTimeSlot[index].startTime, index)
                setSelectedUI(btnTime)
            }
            mTimeslot.addView(btnTime)
        }
        mTimeslot.getChildAt(focusedPos).requestFocus()
    }

    private fun updateView(value: String, index: Int) {
        mGuideAdapter.updateText(value,index)
        mRecyclerView.getChildAt(index).requestFocus()
        mGuideAdapter.notifyDataSetChanged()
    }

    private fun getJsonData() {
        val jsonFileString = activity?.let { getJsonDataFromAsset(it, "guide_info.json") }
        if (jsonFileString != null) {
            jsonFileString.let { Log.i("data", it) }
            val jsonArray = JSONArray(jsonFileString)
            mTimeSlot = ArrayList()
            for (i in 0 until jsonArray.length()) {
                val jsonObj: JSONObject = jsonArray.getJSONObject(i)
                mTimeSlot.add(
                    GuideInfoList(
                        jsonObj.getString("id"),
                        jsonObj.getString("showName"),
                        jsonObj.getString("startTime"),
                        jsonObj.getString("endTime"),
                        jsonObj.getString("duration"),
                        jsonObj.getString("timeLeft"),
                        jsonObj.getString("episode"),
                        jsonObj.getString("description"),
                        jsonObj.getString("videoUrl"),
                        jsonObj.getString("backgroundcardImageUrl"),
                        jsonObj.getString("cardImageUrl")
                    )
                )
            }
        }
    }
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setNonFocusedUI(btnTime: Button) {
        btnTime.setTextColor(resources.getColor(R.color.black))
        btnTime.background = resources.getDrawable(R.drawable.light_border)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setFocusedUI(btnTime: Button) {
        btnTime.setTextColor(resources.getColor(R.color.black))
        btnTime.background = resources.getDrawable(R.drawable.focussed_card)
        val myAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
        val interpolator = MyBounceInterpolator(0.2, 20)
        myAnim.interpolator = interpolator
        btnTime.startAnimation(myAnim)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setSelectedUI(btnTime: Button) {
        btnTime.setTextColor(resources.getColor(R.color.black))
        btnTime.background = resources.getDrawable(R.drawable.selected_bg)
    }


    companion object;

    override fun setFocus() {
        TODO("Not yet implemented")
    }

    fun updateFocus() {
        mTimeslot.getChildAt(focusedPos).requestFocus()
        mTimeslot.getChildAt(focusedPos).isSelected
    }

    fun updateTimeSlotText(position: Int) {
        for (i in 0 until mTimeSlot.size) {
            if (i == position) {
                mTimeslot.getChildAt(position).background =
                    resources.getDrawable(R.drawable.selected_bg)
                focusedPos = i
            } else {
                mTimeslot.getChildAt(i).background =
                    resources.getDrawable(R.drawable.light_border)
            }
        }
    }
}
