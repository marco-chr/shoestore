package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import com.udacity.shoestore.models.Shoe

enum class SaveState {
    SAVE,
    NOOP
}

class ShoelistViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()

    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList
    private var _saveState = MutableLiveData<SaveState>()
    val saveState : LiveData<SaveState>
        get() = _saveState

    init {
        _shoeList.value = mutableListOf()
        addShoe("Air Force", 8.0, "Nike", "desc")
        addShoe("Air Max", 8.0, "Nike", "desc")
        addShoe("Air Jordan", 8.0, "Nike", "desc")
        addShoe("Cortez", 8.0, "Nike", "desc")
        addShoe("Waffle Racer", 8.0, "Nike", "desc")
        Log.i("shoelistViewModel", "shoelistViewModel created!")
    }

    fun addShoe(name: String, size: Double, company: String, description: String) {
        _shoeList.value?.add(Shoe(name, size, company, description))
    }

    fun onEventSave(name: String, size: String, company: String, description: String) {
        Log.i("ShoeViewModel", "onEventSave name $name company $company")
        var sizeDouble : Double = 0.0
        try {
            sizeDouble = size.toDouble()
        } catch (e: NumberFormatException) {
        }
        addShoe(name, sizeDouble, company, description)
        _saveState.value = SaveState.SAVE
    }
    fun onEventSaveComplete() {
        _saveState.value = SaveState.NOOP
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("shoelistViewModel", "shoelistViewModel destroyed!")
    }
}
