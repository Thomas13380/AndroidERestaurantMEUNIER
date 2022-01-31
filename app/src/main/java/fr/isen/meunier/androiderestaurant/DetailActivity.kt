package fr.isen.meunier.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.meunier.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.meunier.androiderestaurant.model.Dish

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.detailTitle.text = (intent.getSerializableExtra("dish") as Dish).name
        binding.dishPicture.setImageResource((intent.getSerializableExtra("dish") as Dish).picture)
    }
}