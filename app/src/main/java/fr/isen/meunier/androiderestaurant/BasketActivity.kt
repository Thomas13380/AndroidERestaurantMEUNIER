package fr.isen.meunier.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.meunier.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.meunier.androiderestaurant.model.BasketData
import fr.isen.meunier.androiderestaurant.model.DishModel


class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.basketTitle.text = "Votre Panier"
        val recyclerview = binding.basketItem
        val i = intent
        var dish = intent.getSerializableExtra("data") as List<BasketData>
        Log.e("test", dish.toString())
        displayDishes(dish)
        var buttonConnection = binding.buttonConnection
        buttonConnection.setOnClickListener{
            startActivity(Intent(this,ConnectionActivity::class.java))
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    private fun displayDishes(dishresult : List<BasketData>){

        val recyclerview = binding.basketItem

        recyclerview.layoutManager = LinearLayoutManager(this)

        binding.basketItem.layoutManager = LinearLayoutManager(this)
        binding.basketItem.adapter = BasketAdapter(dishresult)
    }

}