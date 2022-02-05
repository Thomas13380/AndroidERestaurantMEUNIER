package fr.isen.meunier.androiderestaurant.model

import java.io.Serializable

data class DishBasket(val dishName: DishModel, val quantity: Int): Serializable

data class BasketData(val DishName: String, var quantity : Int): Serializable

