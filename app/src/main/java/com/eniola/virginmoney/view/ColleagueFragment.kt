package com.eniola.virginmoney.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniola.virginmoney.adapter.ColleagueRecyclerViewAdapter
import com.eniola.virginmoney.databinding.ColleagueFragmentBinding
import com.eniola.virginmoney.model.ColleaguesItem
import com.eniola.virginmoney.util.UserInterface
import com.eniola.virginmoney.viewModel.OfficeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ColleagueFragment : Fragment() {
    private val binding: ColleagueFragmentBinding by lazy {
        ColleagueFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var colleagueRecyclerViewAdapter: ColleagueRecyclerViewAdapter
    private val officeViewModel : OfficeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listOfColleagues : MutableList<ColleaguesItem> = mutableListOf()
        colleagueRecyclerViewAdapter = ColleagueRecyclerViewAdapter(listOfColleagues)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.colleaguesFragment.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = colleagueRecyclerViewAdapter
        }

        officeViewModel.allColleagues.observe(viewLifecycleOwner,::resultHandler)
        officeViewModel.getAllColleagues()
        binding.itemReload.setOnRefreshListener {
            officeViewModel.getAllColleagues()
        }
        return binding.root
    }

    fun resultHandler(userInterface: UserInterface?){

        when(userInterface){
            is UserInterface.LOADING ->{
                Toast.makeText(requireContext(),"LOADING...", Toast.LENGTH_LONG).show()
        }
            is UserInterface.COLLEAGUEOUTCOME ->{
                colleagueRecyclerViewAdapter.setColleagueInfo(userInterface.colleagues)
            }
            is UserInterface.ERRORMESSAGE ->{
                Toast.makeText(requireContext(),"Error: "+ userInterface.error.localizedMessage,Toast.LENGTH_LONG).show()
            }
    }
}

    companion object{
        @JvmStatic
        fun newInstance() = ColleagueFragment()

    }
}