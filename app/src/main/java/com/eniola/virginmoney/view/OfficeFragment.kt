package com.eniola.virginmoney.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniola.virginmoney.adapter.OfficeRecyclerViewAdapter
import com.eniola.virginmoney.databinding.OfficeFragmentBinding
import com.eniola.virginmoney.model.OfficeItem
import com.eniola.virginmoney.util.UserInterface
import com.eniola.virginmoney.viewModel.OfficeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OfficeFragment : Fragment(){

    private val binding: OfficeFragmentBinding by lazy{
        OfficeFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var officeRecyclerViewAdapter: OfficeRecyclerViewAdapter
    private val officeViewModel: OfficeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listOffice: MutableList<OfficeItem> = mutableListOf()
        officeRecyclerViewAdapter = OfficeRecyclerViewAdapter(listOffice)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.officeFrag.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = officeRecyclerViewAdapter
        }

        officeViewModel.allOffices.observe(viewLifecycleOwner, ::resultHandler)
        officeViewModel.getAllOffices()
        binding.itemReload.setOnRefreshListener {
            officeViewModel.getAllOffices()
        }

        return binding.root
    }

    fun resultHandler(userInterface: UserInterface?) {
        when(userInterface){
            is UserInterface.LOADING ->{
                Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
                binding.officeProgressBar.visibility = View.VISIBLE
                binding.officeFrag.visibility = View.GONE
            }
            is UserInterface.OFFICEOUTCOME ->{
                binding.officeProgressBar.visibility = View.GONE
                binding.officeFrag.visibility = View.VISIBLE
                officeRecyclerViewAdapter.setOfficeInfo(userInterface.office)
            }
            is UserInterface.ERRORMESSAGE ->{
                binding.officeProgressBar.visibility = View.GONE
                binding.officeFrag.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Error: "+ userInterface.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance() = OfficeFragment()
    }
}