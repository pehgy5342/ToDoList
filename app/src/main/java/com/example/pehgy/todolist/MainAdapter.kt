package com.example.pehgy.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view.view.*

class MainAdapter(context: Context, var infoList: ArrayList<Info>,val addDeleteList:(String,Boolean) -> Unit) :
    RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

  //  private var deleteList = arrayListOf<String>() //加private是因為有get-deleteList的方法，加了就不會重名

    override fun getItemCount(): Int = infoList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return CustomViewHolder(view)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        Log.d(
//            "onBindViewHolder", "position: ${position} holder: ${holder} infoList: ${infoList.size} " +
//                    "info: ${infoList[position]}"
//        )

        holder.bind(infoList[position])
    }


    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv = itemView.tv_show
        var click = itemView.choice


        fun bind(info: Info) {
            // Log.d("CustomViewHolder", "info: ${info} msg: ${info}")

            tv.setText(info.msg)
            click.isChecked = false
            click.setOnCheckedChangeListener { buttonView, isChecked ->

                addDeleteList.invoke(info.key,isChecked)

            }
        }


    }


    //更改畫面
    fun updateList(newinfoList: ArrayList<Info>) {
        this.infoList = newinfoList
        this.notifyDataSetChanged()

    }

}