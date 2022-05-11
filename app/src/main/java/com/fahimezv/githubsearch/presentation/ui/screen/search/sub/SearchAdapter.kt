package com.fahimezv.githubsearch.presentation.ui.screen.search.sub

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fahimezv.githubsearch.presentation.ui.common.animation.BounceClickEffectAnimator
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.presentation.OnUserClickListener
import com.fahimezv.githubsearch.presentation.ui.util.ImageLoaderUtils
import com.google.android.material.imageview.ShapeableImageView

class SearchAdapter(

    private val onItemClick: OnUserClickListener
) : PagingDataAdapter<Search.User, SearchAdapter.ViewHolder>(SearchComparator) {

    private val list = mutableListOf<Search.User>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(holder.itemView.context, position)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun bind(posts: List<Search.User>) {
        list.clear()
        list.addAll(posts)
        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var imageView: ShapeableImageView = view.findViewById(R.id.avatar_imageView)
        private var userNameTextView =
            view.findViewById<AppCompatTextView>(R.id.username_TextView)
        private var githubLinkTextView =
            view.findViewById<AppCompatTextView>(R.id.githubLink_TextView)


        init {
            itemView.setOnClickListener(this)
            // For click animation
            BounceClickEffectAnimator(view)

        }

        override fun onClick(view: View?) {
            onItemClick(list[adapterPosition])
        }

        fun bind(context: Context, position: Int) {
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
            ImageLoaderUtils.with(context).placeholder(R.drawable.noimage)
                .load(list[position].avatarUrl)
                .into(imageView)
            githubLinkTextView.text = "Github Link: ${list[position].htmLUrl}"
            userNameTextView.text = "${list[position].login}"
        }
    }


    object SearchComparator : DiffUtil.ItemCallback<Search.User>() {
        override fun areItemsTheSame(oldItem: Search.User, newItem: Search.User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Search.User, newItem: Search.User): Boolean {
            return oldItem == newItem
        }
    }

}