package com.example.citipoc.presentation.ui.view.mealsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.citipoc.R
import com.example.citipoc.databinding.FragmentMealSearchBinding
import com.example.citipoc.databinding.ViewHolderSearchListBinding
import com.example.citipoc.presentation.adapters.MealSearchAdapter
import com.example.citipoc.di.modules.viewmodelmodule.ViewModelFactory
import com.example.citipoc.domain.model.Meal
import com.example.citipoc.presentation.ui.base.BaseFragment
import com.example.citipoc.presentation.ui.viewmodel.MealSearchViewModel
import com.example.citipoc.utils.Resource
import com.example.citipoc.utils.base.Adapter
import com.example.citipoc.utils.base.BaseModel
import com.example.citipoc.utils.base.MealModel
import javax.inject.Inject

class MealSearchFragment : BaseFragment<FragmentMealSearchBinding, MealSearchViewModel>() {

    private val searchAdapter = MealSearchAdapter()
    val items = ArrayList<BaseModel>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MealSearchViewModel

    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MealSearchViewModel::class.java)
        binding.mealSearchRecycler.apply {
            adapter = searchAdapter
        }



        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    viewModel.getSearchMeals(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        //Coroutine
        //viewModel.getSearchMeals("veg")

        //Rx kotlin
        viewModel.getMealSearchRx("veg")
        viewModel.mealSearchLivedata.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                binding.nothingFound.visibility = View.GONE
                binding.progressMealSearch.visibility = View.VISIBLE
            }
            if (it.error.isNotBlank()) {
                binding.nothingFound.visibility = View.GONE
                binding.progressMealSearch.visibility = View.GONE
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
            it.data?.let {
                items.addAll(listOf(MealModel().apply {
                    list = it as MutableList<Meal>
                }))
                if (it.isEmpty()) {
                    binding.nothingFound.visibility = View.VISIBLE
                }
                binding.progressMealSearch.visibility = View.GONE
                binding.mealSearchRecycler.adapter = Adapter(items){
                    holder,model->
                    when(holder.binding){
                      //  is ViewHolderSearchListBinding ->holder.binding= model as MealModel
                    }
                }
                //searchAdapter.setContentList(it.toMutableList())
            }
        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }


            }
        }


        searchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    it.id
                )
            )
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_meal_search
    }

}