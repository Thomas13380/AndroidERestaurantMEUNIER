package fr.isen.meunier.androiderestaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.meunier.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.meunier.androiderestaurant.databinding.FragmentRegisterBinding
import fr.isen.meunier.androiderestaurant.model.DishResult
import org.json.JSONObject

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val buttonValidate = binding.buttonValidate
        buttonValidate.setOnClickListener {
            register()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
    private fun register() {
        val params = HashMap<String, String>()
        params["firstName"] = binding.prenom.hint.toString()
        params["familyName"] = binding.nom.hint.toString()
        params["adress"] = binding.adresse.hint.toString()
        params["emailAdress"] = binding.email.hint.toString()
        params["password"] = binding.password.hint.toString()

        val queue = Volley.newRequestQueue(ConnectionActivity())
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val jsonObject = JSONObject(params as HashMap<*, *>)
        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Log.e("response", it.toString())
            }, {
                Log.e("API", it.toString())
            })
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,

            0,
            1f
        )
        queue.add(request)
    }


}