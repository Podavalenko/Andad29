package ru.netology.andad29.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.andad29.BuildConfig
import ru.netology.andad29.R
import ru.netology.andad29.databinding.PostCardBinding
import ru.netology.andad29.dto.AttachmentType
import ru.netology.andad29.dto.Post
import ru.netology.andad29.view.load
import ru.netology.andad29.view.loadCircleCrop

interface OnInterfactionListener {
    fun onLike(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onRepost(post: Post) {}
    fun onVideo(post: Post) {}
    fun onOpenPost(post: Post) {}
    fun onViewImage(post: Post) {}
}

class PostsAdapter(
    private val onInterfactionListener: OnInterfactionListener,
) : PagingDataAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInterfactionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        getItem(position)?.let {
            holder.bind(it)
        }

    }
}

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onInterfactionListener: OnInterfactionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            avatar.loadCircleCrop("${BuildConfig.BASE_URL}/avatars/${post.authorAvatar}")
            content.text = post.content
            like.isChecked = post.likedByMe
            like.text = "${post.likes}"
            if (!post.videoUrl.isNullOrEmpty()) {
                video.visibility = View.VISIBLE
            } else video.visibility = View.GONE
            if (post.attachment != null && post.attachment.type == AttachmentType.IMAGE) {
                attachmentView.visibility = View.VISIBLE
                attachmentView.load("${BuildConfig.BASE_URL}/media/${post.attachment.url}")
            } else attachmentView.visibility = View.GONE
            menu.visibility = if (post.ownedByMe) View.VISIBLE else View.INVISIBLE
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    // TODO: if we don't have other options, just remove dots
                    menu.setGroupVisible(R.id.owned, post.ownedByMe)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInterfactionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInterfactionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
            like.setOnClickListener {
                onInterfactionListener.onLike(post)
            }
            repost.setOnClickListener {
                onInterfactionListener.onRepost(post)
            }
            video.setOnClickListener {
                onInterfactionListener.onVideo(post)
            }
            content.setOnClickListener {
                onInterfactionListener.onOpenPost(post)
            }
            attachmentView.setOnClickListener {
                onInterfactionListener.onViewImage(post)
            }
        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}