package com.example.citipoc.presentation.ui.view.mealdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.citipoc.R
import com.example.citipoc.di.modules.viewmodelmodule.ViewModelFactory
import com.example.citipoc.databinding.FragmentMealDetailsBinding
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.presentation.ui.base.BaseFragment
import com.example.citipoc.presentation.ui.viewmodel.MealDetailsViewModel
import com.example.citipoc.utils.Resource
import javax.inject.Inject


class MealDetailsFragment : BaseFragment<FragmentMealDetailsBinding,MealDetailsViewModel> (){

    private var _binding: FragmentMealDetailsBinding? = null
    val binding: FragmentMealDetailsBinding
        get() = _binding!!
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MealDetailsViewModel
    private val args: MealDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MealDetailsViewModel::class.java)

        //Rx observer
        viewModel.mealDetailLivedata.observe(viewLifecycleOwner) { showMealDetails(it) }


        args.mealId?.let {
            //Coroutine
           // viewModel.getMealDetails(it)

            //Rx
            viewModel.getMealDetailsRx(it)
        }

//Coroutine
     /*   lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetails.collect {
                if (it.isLoading) {
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.mealDetails = it
                }
            }
        }*/

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showMealDetails(data: Resource<List<MealDetails>>?) {
        data?.let {
            binding.mealDetails = it.data?.get(0) as MealDetails
        }

    }


    override fun getLayoutId(): Int {
      return  R.layout.fragment_meal_details
    }

}