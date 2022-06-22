package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.ShoeRowBinding
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.ShoelistViewModel

class shoelist : Fragment()  {

    private val viewModel: ShoelistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, container, false)

        viewModel.shoeList.observe(this as LifecycleOwner, Observer {

            for (shoe in viewModel.shoeList.value!!) {
                val inBinding = ShoeRowBinding.inflate(layoutInflater)
                inBinding.shoeData = shoe
                binding.innerLayout.addView(inBinding.root)
            }
        })

        binding.shoeDetailButton.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoelist_to_shoedetails)
        }

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}