package com.example.partfolio2.Pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.partfolio2.Adapter.RecyclerAdapter
import com.example.partfolio2.App.App
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem
import com.example.partfolio2.R
import com.example.partfolio2.ViewModel.MyViewModel
import com.example.partfolio2.databinding.FragmentSavePhotoPageBinding
import com.example.partfolio2.listners.ItemClickLIstner
import com.google.gson.Gson
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavePhotoPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavePhotoPage : Fragment(),ItemClickLIstner{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponents.inject(this)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding :FragmentSavePhotoPageBinding
    @Inject
    lateinit var viewModel: MyViewModel
    lateinit var listData:ArrayList<ListDataItem>
    lateinit var adapter :RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentSavePhotoPageBinding.inflate(inflater,container,false)

        loadRecyclerView()
        listData  = ArrayList()

        return binding.root
    }

    private fun loadRecyclerView() {
        binding.rvSavepage.layoutManager   = LinearLayoutManager(requireActivity())
        adapter   = RecyclerAdapter(this)
        binding.rvSavepage.adapter  =adapter

    }

    private fun loadData() {

            viewModel.getAllData().observe(viewLifecycleOwner,{
                listData.clear()
                it.forEach(){
                    listData.add(Gson().fromJson(it.data,ListDataItem::class.java))
                }
                Log.d("@@@@", "loadData:${listData.size}")
                adapter.submitList(listData)
            })
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavePhotoPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavePhotoPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun itemClickListner(listDataItem: ListDataItem) {
        var bundle =  Bundle()
        var str :String  = Gson().toJson(listDataItem)
        bundle.putString("data",str)
        findNavController().navigate(R.id.action_savePhotoPage_to_itemPage,bundle)
    }

    override fun logclickItem(listDataItem: ListDataItem) {

    }
}