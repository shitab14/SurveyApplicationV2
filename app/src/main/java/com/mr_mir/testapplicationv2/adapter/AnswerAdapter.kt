package com.mr_mir.testapplicationv2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.mr_mir.testapplicationv2.R

/**
 * Created by Shitab Mir on 28,July,2020
 */
class AnswerAdapter (var data: List<String>?, var context: Context): RecyclerView.Adapter<AnswerAdapter.MyViewHolder>() {

    //tempData
    companion object {
        var tempContactNumber: String = ""
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rlOption = itemView.findViewById<RelativeLayout>(R.id.rlOption) as RelativeLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_options, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        //Listeners
        holder.rlOption.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

}