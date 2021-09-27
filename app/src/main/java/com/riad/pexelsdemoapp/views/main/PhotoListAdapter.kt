package com.riad.pexelsdemoapp.views.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riad.pexelsdemoapp.App
import com.riad.pexelsdemoapp.R
import com.riad.pexelsdemoapp.data.models.Photo
import kotlinx.android.synthetic.main.photo_list_item.view.*

class PhotoListAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var mRecyclerView : RecyclerView? = null
    private var photoList: MutableList<Photo> = arrayListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder?.let {
//            it.itemTextView.text = itemList.get(position)
//            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
            it.bind(photoList[position], context)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.photo_list_item, parent, false)

//        mView.setOnClickListener { view ->
//            mRecyclerView?.let {
//                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
//            }
//        }

        return RecyclerViewHolder(mView)
    }

    fun setDataList(dataList: List<Photo>){
        photoList.clear()
        photoList.addAll(dataList)
        notifyDataSetChanged()
    }



}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(photo: Photo?,context: Context) {
        itemView.text_view_title.text = App.instance.getString(R.string.photographer, photo?.photographer)

        Glide.with(itemView.context)
            .load(photo?.src?.small)
            .into(itemView.iv_photo)

        itemView.setOnClickListener{
//            val intent = Intent(context, SingleMovie::class.java)
//            intent.putExtra("id", movie?.id)
//            context.startActivity(intent)
        }

    }

}