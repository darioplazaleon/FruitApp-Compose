package com.example.composepractice.data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composepractice.data.model.Fruit

class CartViewModel : ViewModel(){
    private val _fruits = MutableLiveData<List<Fruit>>()


    val fruits: LiveData<List<Fruit>> get() = _fruits

    fun addFruit(fruit: Fruit, count: Int) {
        val existingFruit = fruits.value?.find { it.name == fruit.name }
        if (existingFruit != null) {
            existingFruit.quantity += count
        } else {
            val updatedList = fruits.value?.toMutableList() ?: mutableListOf()
            updatedList.add(fruit.copy(quantity = count))
            _fruits.value = updatedList
        }
    }

    fun removeFruit(fruit: Fruit) {
        val updatedList = fruits.value?.toMutableList() ?: mutableListOf()
        updatedList.remove(fruit)
        _fruits.value = updatedList
    }

    fun removeAndRefresh(fruit: Fruit) {
        val updatedList = fruits.value?.toMutableList() ?: mutableListOf()
        updatedList.remove(fruit)
        _fruits.value = updatedList
    }

    fun getFruits(): List<Fruit> {
        return _fruits.value ?: emptyList()
    }

    fun getTotalPrice(): Int {
        return fruits.value?.sumOf { it.price * it.quantity } ?: 0
    }
}