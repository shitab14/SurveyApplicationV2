package com.mr_mir.testapplicationv2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.mr_mir.testapplicationv2.R

/**
 * Created by Shitab Mir on 28,July,2020
 */
class CheckboxAdapter(var context: Context, var list: List<String>?): RecyclerView.Adapter<CheckboxAdapter.MyViewHolder>(),
    CompoundButton.OnCheckedChangeListener {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkbox: CheckBox = itemView.findViewById<CheckBox>(R.id.checkbox) as CheckBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_checkbox, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.checkbox.text = list?.get(position).toString()
        holder.checkbox.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        // todo
    }

}