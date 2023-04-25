package org.bmsk.android_network_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.bmsk.android_network_2.databinding.ItemRepoBinding
import org.bmsk.android_network_2.model.Repo

class RepoAdapter(
    private val onClick: (Repo) -> Unit
) : ListAdapter<Repo, RepoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(
        private val viewBinding: ItemRepoBinding
    ) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(repo: Repo) {
            viewBinding.repoNameTextView.text = repo.name
            viewBinding.descriptionTextView.text = repo.description
            viewBinding.starCountTextView.text = "${repo.starCount}"
            viewBinding.forkCountTextView.text = "${repo.forkCount}"

            viewBinding.root.setOnClickListener {
                onClick(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

        }
    }
}