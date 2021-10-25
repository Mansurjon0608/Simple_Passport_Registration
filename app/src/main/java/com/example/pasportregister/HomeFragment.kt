package com.example.pasportregister

import Models.MyObject
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pasportregister.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onStart() {
        super.onStart()
        MyObject.isHome = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


       binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))

        binding.btnAllCitizen.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }

        binding.btnNewPasport.setOnClickListener {
            findNavController().navigate(R.id.addFragment)

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}