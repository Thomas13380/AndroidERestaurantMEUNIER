package fr.isen.meunier.androiderestaurant.model

import java.io.Serializable

data class DishBasket(val dishName: MutableList<BasketData>, var quantity: Int): Serializable

data class BasketData(val DishName: DishModel, var quantity : Int): Serializable

