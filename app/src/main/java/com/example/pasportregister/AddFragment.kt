package com.example.pasportregister

import DB.AppDatabase
import Models.Fuqaro
import Models.MyObject
import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.pasportregister.databinding.DialogTasdiqlashBinding
import com.example.pasportregister.databinding.FragmentAddBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    lateinit var appDatabase: AppDatabase
    private var param1: String? = null
    private var param2: String? = null
    override fun onStart() {
        super.onStart()
        MyObject.isHome = false
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
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
        binding = FragmentAddBinding.inflate(LayoutInflater.from(context))

        binding.btnSave.setOnClickListener {
            val fuqaro = Fuqaro()

            fuqaro.name = binding.editName.text.toString().trim()
            fuqaro.lastName = binding.editSurname.text.toString().trim()
            fuqaro.otasiningIsmi = binding.editOtaIsmi.text.toString().trim()
            fuqaro.viloyat = binding.spinnerViloyatlar.selectedItemPosition
            fuqaro.city = binding.editShahar.text.toString().trim()
            fuqaro.uyManzili = binding.editUyManzili.text.toString().trim()
            fuqaro.passportOlganVaqti = binding.editPasportOlganVaqti.text.toString().trim()
            fuqaro.passportDedline = binding.editPassportMuddati.text.toString().trim()
            fuqaro.jinsi = binding.spinnerJinsi.selectedItemPosition
            fuqaro.imagePath = absolutePath

            if (fuqaro.name != "" && fuqaro.lastName != "" && absolutePath != "" && binding.spinnerViloyatlar.selectedItemPosition != 0 && binding.spinnerJinsi.selectedItemPosition != 0) {
                val alertDialog = AlertDialog.Builder(binding.root.context, R.style.NewDialog)
                val itemDialog = DialogTasdiqlashBinding.inflate(LayoutInflater.from(context))
                val dialog = alertDialog.create()
                dialog.setView(itemDialog.root)
                itemDialog.btnNo.setOnClickListener { dialog.cancel() }

                itemDialog.btnYes.setOnClickListener {
                    appDatabase.fuqaroDao().addFuqaro(fuqaro)
                    // var id = appDatabase.fuqaroDao().getFuqaroById(fuqaro.passportSeriya!!)
                    Toast.makeText(context, "${fuqaro.name} qo'shildi", Toast.LENGTH_SHORT)
                        .show()
                    dialog.cancel()
                    findNavController().popBackStack()

                }
                dialog.show()


            } else {
                Toast.makeText(
                    context,
                    "Ma'lumotlar yetarli emas, avval ma'lumotlarni to'ldiring...",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            appDatabase = AppDatabase.getInstance(context)
            val listFuqaro = appDatabase.fuqaroDao().getAllFuqaro()
            val listSeriya = ArrayList<String>()

//        for (fuqaro in listFuqaro) {
//            listSeriya.add(fuqaro.passportSeriya!!)
//
//        }
        }

        binding.image.setOnClickListener {
                askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
                    getImageContent.launch("image/*")
                }.onDeclined { e ->
                    if (e.hasDenied()) {
                        AlertDialog.Builder(binding.root.context)
                            .setMessage("Please accept our permissions")
                            .setPositiveButton("yes") { dialog, which ->
                                e.askAgain();
                            } //ask again
                            .setNegativeButton("no") { dialog, which ->
                                dialog.dismiss();
                            }
                            .show()
                    }

                    if (e.hasForeverDenied()) {
                        //the list of forever denied permissions, user has check 'never ask again'

                        // you need to open setting manually if you really need it
                        e.goToSettings();
                    }
                }
            }

        binding.btnBack.setOnClickListener {
                if (binding.editName.text.toString() != "" && binding.editSurname.text.toString() != "") {
                    val dialog = android.app.AlertDialog.Builder(context)
                    dialog.setTitle("ATTENTION !")
                    dialog.setIcon(R.drawable.ic_baseline_follow_the_signs_24)
                    dialog.setMessage("Ma'lumotlar saqlanmadi, ortga qaytilsinmi?")
                    dialog.setPositiveButton(
                        "Ha "
                    ) { dialog, which ->
                        findNavController().popBackStack()
                    }

                    dialog.setNegativeButton(
                        "Yo'q "
                    ) { dialog, which ->
                        dialog.cancel()
                    }
                    dialog.show()
                } else {
                    findNavController().popBackStack()
                }
            }


        return binding.root
    }

    var absolutePath = ""
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
            uri ?: return@registerForActivityResult
            binding.image.setImageURI(uri)
            val inputStream = activity?.contentResolver?.openInputStream(uri)
            val format = SimpleDateFormat("yyMMdd_hhss").format(Date())
            val file = File(activity?.filesDir, "${format}image.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            absolutePath = file.absolutePath

            Toast.makeText(context, "$absolutePath", Toast.LENGTH_SHORT).show()
        }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}