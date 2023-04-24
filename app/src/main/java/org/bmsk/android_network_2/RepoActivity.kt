package org.bmsk.android_network_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.bmsk.android_network_2.databinding.ActivityRepoBinding

class RepoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}