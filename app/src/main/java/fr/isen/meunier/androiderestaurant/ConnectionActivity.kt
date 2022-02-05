package fr.isen.meunier.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import fr.isen.meunier.androiderestaurant.databinding.ActivityConnectionBinding

class ConnectionActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewPager = binding.pager
        val buttonLogin = binding.buttonLogin
        val buttonRegister = binding.buttonRegister
        val pagerAdapter = ConnectionAdapter(this)
        viewPager.adapter = pagerAdapter

    }
}