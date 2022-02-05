package fr.isen.meunier.androiderestaurant.model

import java.io.Serializable

data class AccountModel(val familyName:String, val firstName: String,val adress: String, val mailAdress: String, val password: String): Serializable
