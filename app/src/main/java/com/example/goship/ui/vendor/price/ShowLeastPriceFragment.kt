package com.example.goship.ui.vendor.price

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.example.goship.R
import com.example.goship.databinding.FragmentShowLeastPriceBinding
import kotlinx.android.synthetic.main.fragment_show_least_price.view.*

class ShowLeastPriceFragment : Fragment() {

    private lateinit var showLeastPriceViewModel: ShowLeastPriceViewModel


//    companion object {
//        fun newInstance() = ShowLeastPriceFragment()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentShowLeastPriceBinding>(inflater, R.layout.fragment_show_least_price,container,false)

        showLeastPriceViewModel = ViewModelProviders.of(this).get(ShowLeastPriceViewModel::class.java)

        binding.sourceAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            showLeastPriceViewModel.sourcedivision.value = showLeastPriceViewModel.divisions.value!![position]
        }

        binding.destinationAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            showLeastPriceViewModel.destinationdivision.value = showLeastPriceViewModel.divisions.value!![position]
        }

        showLeastPriceViewModel.divisions.observe(viewLifecycleOwner, Observer { newdivisions ->
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, newdivisions)
            (binding.sourceTextField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            (binding.destinationTextField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        })

        binding.vendorGetPriceButtonId.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(
                ShowLeastPriceFragmentDirections.actionNavVendorEstimateToUpdateLeastPriceFragment(sourcedivision = binding.sourceAutoCompleteTextView.text.toString(), destinationdivision = binding.destinationAutoCompleteTextView.text.toString())
            )
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLeastPriceViewModel = ViewModelProviders.of(this).get(ShowLeastPriceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
