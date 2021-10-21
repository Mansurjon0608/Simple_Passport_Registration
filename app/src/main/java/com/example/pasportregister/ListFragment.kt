package com.example.pasportregister

import Adapter.RvAdapter
import Adapter.RvOnClick
import DB.AppDatabase
import Models.Fuqaro
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.pasportregister.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.dialog_search.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment() {

    lateinit var binding:FragmentListBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var listData:ArrayList<Fuqaro>

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

        binding = FragmentListBinding.inflate(LayoutInflater.from(context))

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }



        binding.btnSearch.setOnClickListener {
            val customDialog = AlertDialog.Builder(context)
            val dialog = customDialog.create()

            dialog.setTitle("Qidirish")
            val dialogView = layoutInflater.inflate(R.layout.dialog_search, null, false)

            dialog.setView(dialogView)

            dialogView.btn_save.setOnClickListener {
                Toast.makeText(context, "Qidirilmoqda...", Toast.LENGTH_LONG).show()
                dialog.cancel()
            }
            dialog.show()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(binding.root.context)
        listData = ArrayList<Fuqaro>()
        listData.addAll(appDatabase.fuqaroDao().getAllFuqaro())

        rvAdapter = RvAdapter(context, listData, object: RvOnClick{
            override fun itemOnClick(fuqaro: Fuqaro, position: Int) {
                findNavController().navigate(R.id.infoFragment,bundleOf("keyFuqaro" to fuqaro) )
            }

            override fun moreOnClick(fuqaro: Fuqaro, position: Int, v: ImageView) {
                val popupMenu = PopupMenu(context, v)
                popupMenu.inflate(R.menu.popup_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete_menu -> {
                            val alertDialog = AlertDialog.Builder(context)
                            alertDialog.setIcon(R.drawable.ic_baseline_delete_sweep_24)
                            alertDialog.setTitle("ATTENTION !")
                            alertDialog.setMessage("${fuqaro.name} o'chirilsinmi?")
                            alertDialog.setPositiveButton(
                                "Ha",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                                        var id = appDatabase.fuqaroDao()
//                                            .getFuqaroById(fuqaro.passportSeriya!!)
//                                        fuqaro.id = id

                                        appDatabase.fuqaroDao().deleteCitizen(fuqaro)
                                        Toast.makeText(
                                            context,
                                            "${fuqaro.name} o'chirildi",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        onResume()
                                    }
                                })

                            alertDialog.setNegativeButton(
                                "Yo'q",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {

                                    }
                                })

                            alertDialog.show()
                        }

                        R.id.edit_menu -> {
                            findNavController().navigate(
                                R.id.editFragment,
                                bundleOf("fuqaroKey" to fuqaro)
                            )
                            Toast.makeText(context, "Tahrirlash", Toast.LENGTH_SHORT).show()

                        }
                    }
                    true
                }

                popupMenu.show()
            }

        })

        binding.rv.adapter = rvAdapter

    }

    fun search(list: List<Fuqaro>) {
        rvAdapter = RvAdapter(context, list, object : RvOnClick {
            override fun itemOnClick(fuqaro: Fuqaro, position: Int) {
                findNavController().navigate(
                    R.id.infoFragment,
                    bundleOf("keyCitizen" to fuqaro)
                )
            }

            override fun moreOnClick(fuqaro: Fuqaro, position: Int, v: ImageView) {
                val popupMenu = PopupMenu(context, v)
                popupMenu.inflate(R.menu.popup_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete_menu -> {
                            val alertDialog = AlertDialog.Builder(context)
                            alertDialog.setIcon(R.drawable.ic_baseline_delete_sweep_24)
                            alertDialog.setTitle("ATTENTION !")
                            alertDialog.setMessage("${fuqaro.name} o'chirilsinmi?")
                            alertDialog.setPositiveButton(
                                "Ha",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        var id = appDatabase.fuqaroDao()
                                            .getFuqaroById(fuqaro.passportSeriya!!)
                                        fuqaro.id = id

                                        appDatabase.fuqaroDao().deleteCitizen(fuqaro)
                                        Toast.makeText(
                                            context,
                                            "${fuqaro.name} o'chirildi",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        onResume()
                                    }
                                })

                            alertDialog.setNegativeButton(
                                "Yo'q",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {

                                    }
                                })

                            alertDialog.show()
                        }

                        R.id.edit_menu -> {
                            findNavController().navigate(
                                R.id.editFragment,
                                bundleOf("fuqaroKey" to fuqaro)
                            )
                            Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()

                        }
                    }
                    true
                }

                popupMenu.show()
            }

        })
        binding.rv.adapter = rvAdapter
    }


            companion object {

                @JvmStatic
                fun newInstance(param1: String, param2: String) =
                    ListFragment().apply {
                        arguments = Bundle().apply {
                            putString(ARG_PARAM1, param1)
                            putString(ARG_PARAM2, param2)
                        }
                    }
            }
        }
