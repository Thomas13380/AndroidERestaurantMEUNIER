package fr.isen.meunier.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.meunier.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.meunier.androiderestaurant.model.BasketData
import fr.isen.meunier.androiderestaurant.model.DishBasket
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



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var counter=1
        var dishName = (intent.getSerializableExtra("dish") as DishModel)
        var price = (intent.getSerializableExtra("dish") as DishModel).prices[0].price
        var totPrice = price.toFloat()*counter
        val buttonMinus = binding.buttonMinus
        val buttonPlus = binding.buttonPlus
        val buttonPanier = binding.buttonPanier
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
        var data = ArrayList<BasketData>()
        val buttonTot = binding.buttonTot
        buttonTot.setOnClickListener{
            val filename = "/panier.json"
            Snackbar.make(it,"Ajouté au panier", Snackbar.LENGTH_LONG).show()
            File(cacheDir.absolutePath + filename).bufferedWriter().use { file->
                file.write(Gson().toJson(DishBasket(dishName,counter)))
            }

            val recup = File(cacheDir.absolutePath + filename).bufferedReader().readText();

            val resultat = Gson().fromJson(recup,DishBasket::class.java);
            Log.d("panier","$recup")
            var bool = false
            for(i in data.indices)
                if(resultat.dishName.name_fr==data[i].DishName){
                    data[i].quantity += resultat.quantity
                    bool = true
                }
                else {
                    data.add(BasketData(resultat.dishName.name_fr,resultat.quantity))
                    bool = true}
            if(bool==false) data.add(BasketData(resultat.dishName.name_fr,resultat.quantity))
            Log.e("test", resultat.toString())
        }
        buttonPanier.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun initDetail(dish: DishModel){
        binding.detailTitle.text = dish.name_fr
        binding.dishPhotoPager.adapter = DishPictureAdapter(this,dish.pictures)
    }

}