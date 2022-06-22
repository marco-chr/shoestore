package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoedetailsBinding
import com.udacity.shoestore.models.SaveState
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoelistViewModel

class shoedetails : Fragment() {

    private val viewModel: ShoelistViewModel by activityViewModels()
    private val shoeData = Shoe("",0.0,"","")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentShoedetailsBinding.inflate(inflater, container, false)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeData = shoeData

        binding.cancelButton.setOnClickListener{
            val action = shoedetailsDirections.actionShoedetailsToShoelist()
            findNavController().navigate(action)
        }

        viewModel.saveState.observe(this as LifecycleOwner, Observer{ss ->
            when(ss) {
                SaveState.SAVE -> {
                    navigateToShoeList()
                    viewModel.onEventSaveComplete()
                }
            }
        })


        return binding.root

    }

    private  fun navigateToShoeList() {
        val action = shoedetailsDirections.actionShoedetailsToShoelist()
        findNavController().navigate(action)

    }

}