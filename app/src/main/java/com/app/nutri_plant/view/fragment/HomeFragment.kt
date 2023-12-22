package com.app.nutri_plant.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.app.nutri_plant.R
import com.app.nutri_plant.adapter.SayurAdapter
import com.app.nutri_plant.databinding.FragmentHomeBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.DataSayur
import com.app.nutri_plant.view.base.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home), SayurAdapter.CellClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    private fun setListener() {

    }

    private fun setView(){
        binding.rvSayur.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSayur.adapter = SayurAdapter(requireContext(), listSayur(), this)
    }

    private fun listSayur(): List<DataSayur>{
        val data = mutableListOf<DataSayur>()
        data.add(DataSayur("Wortel", R.drawable.ic_wortel))
        data.add(DataSayur("Timun", R.drawable.ic_timun))
        data.add(DataSayur("Terong", R.drawable.ic_terong))
        data.add(DataSayur("Bawang", R.drawable.ic_bawang))
        data.add(DataSayur("Kentang", R.drawable.ic_kentang))
        data.add(DataSayur("Kol", R.drawable.ic_kol))

        return data
    }

    override fun selectSayur(data: DataSayur) {

    }
}