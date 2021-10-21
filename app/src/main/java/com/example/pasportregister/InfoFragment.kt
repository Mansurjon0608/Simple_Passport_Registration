package com.example.pasportregister

import Models.Fuqaro
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pasportregister.databinding.FragmentInfoBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding
    lateinit var fuqaro: Fuqaro

    private var param1: String? = null
    private var param2: String? = null



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
        binding = FragmentInfoBinding.inflate(LayoutInflater.from(context))

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        fuqaro = arguments?.getSerializable("keyFuqaro") as Fuqaro

        binding.image.setImageURI(Uri.parse(fuqaro.imagePath))

        if (fuqaro.jinsi == 1){
            binding.jinsi.text = "Jinsi Erkak"
        } else {
            binding.jinsi.text = "Jinsi Ayol"
        }


        binding.txtInfoBar.text = " ${fuqaro.name}  ${fuqaro.lastName}"
        binding.txtIsm.text = "${fuqaro.name}"
        binding.txtFamiliya.text = "${fuqaro.lastName}"
        binding.txtOtaIsmi.text = "Otasining ismi: ${fuqaro.otasiningIsmi}"
        binding.shahar.text = "Shahri: ${fuqaro.city}"
        binding.uy.text = "Uy manzili: ${fuqaro.uyManzili}"
        binding.txtPasportOlganVaqti.text = "Pasport berilgan vaqti: ${fuqaro.passportOlganVaqti}"
        binding.txtPassportMuddati.text = "Amal qilish muddati: ${fuqaro.passportDedline}"

        when(fuqaro.viloyat){
            1 -> binding.viloyat.text = "Viloyati:  Toshkent sh."
            2 -> binding.viloyat.text = "Viloyati:  Andijon"
            3 -> binding.viloyat.text = "Viloyati:  Buxoro"
            4 -> binding.viloyat.text = "Viloyati:  Farg'ona"
            5 -> binding.viloyat.text = "Viloyati:  Jizzax"
            6 -> binding.viloyat.text = "Viloyati:  Xorazm"
            7 -> binding.viloyat.text = "Viloyati:  Namangan"
            8 -> binding.viloyat.text = "Viloyati:  Navoiy"
            9 -> binding.viloyat.text = "Viloyati:  Qashqadaryo"
            10 -> binding.viloyat.text = "Viloyati:  Qoraqalpog'iston Respublikasi"
            11 -> binding.viloyat.text = "Viloyati:  Samarqand"
            12-> binding.viloyat.text = "Viloyati:  Sirdaryo"
            13 -> binding.viloyat.text = "Viloyati:  Surxondaryo"
            14 -> binding.viloyat.text = "Viloyati:  Toshkent vil."
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}