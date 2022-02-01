package fr.isen.meunier.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.meunier.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.meunier.androiderestaurant.model.DishModel
import java.io.File


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)
        var list = ""
        val listIngredients = (intent.getSerializableExtra("dish") as DishModel).getFormatedIngredients()
        for(i in listIngredients.indices){
            list += listIngredients[i].name_fr + ", "
        }
        binding.detail.text = list
        var counter=1
        var price = (intent.getSerializableExtra("dish") as DishModel).prices[0].price
        var totPrice = price.toFloat()*counter
        val buttonMinus = binding.buttonMinus
        val buttonPlus = binding.buttonPlus
        binding.buttonTot.text = "Total : " + price+"€"
        buttonPlus.setOnClickListener {
            counter++
            totPrice = price.toFloat()*counter
            binding.counter.text = counter.toString()
            binding.buttonTot.text = "Total : " + totPrice.toString() + "€"
        }
        buttonMinus.setOnClickListener {
            if (counter!=1){
                counter--
                totPrice = price.toFloat()*counter
                binding.counter.text = counter.toString()
                binding.buttonTot.text = "Total : " + totPrice.toString() + "€"
            }
        }

        val buttonTot = binding.buttonTot
        buttonTot.setOnClickListener{
            File(this.filesDir, "panier.txt").outputStream().use {
                it.write("Bonjour !!!".toByteArray())
            }
        }

    }
    private fun initDetail(dish: DishModel){
        binding.detailTitle.text = dish.name_fr
        binding.dishPhotoPager.adapter = DishPictureAdapter(this,dish.pictures)
    }

}