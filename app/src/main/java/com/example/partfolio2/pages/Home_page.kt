package com.example.partfolio2.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.partfolio2.adapter.RecyclerAdapter
import com.example.partfolio2.app.App
import com.example.partfolio2.data_model.RandomDataByTag.ListDataItem
import com.example.partfolio2.R
import com.example.partfolio2.utils.Resources
import com.example.partfolio2.viewmodel.MyViewModel
import com.example.partfolio2.databinding.FragmentHomePageBinding
import com.example.partfolio2.listners.ItemClickLIstner
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home_page.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home_page : Fragment() ,ItemClickLIstner{
    @Inject
    lateinit var myViewModel: MyViewModel
    lateinit var binding: FragmentHomePageBinding
    lateinit var adapter: RecyclerAdapter
    private var listTabs:List<String> = listOf("New","Sport","Wallpapers","Nature","Cyber","Neo","3d-render","Travel","Animal","food","Arts-culture")
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = RecyclerAdapter(this)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponents.inject(this)
        binding = FragmentHomePageBinding.inflate(inflater,container,false)
        initViews()



        buttonClicks()

        return binding.root

    }

    private fun buttonClicks() {
        binding.saveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_page_to_savePhotoPage)
        }
    }


    private fun initViews() {
        if(adapter.itemCount==0){
            loadData("New")
        }
        binding.rvHomepage.adapter  = adapter
        installTablayout()
        onTabSelec()

    }


    private fun onTabSelec() {
        binding.tablayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                val view  = tab!!.view
                val textView:TextView = view.findViewById(R.id.textViewtab)
                loadData(textView.text.toString())
            }

            override fun onTabUnselected(tab: Tab?) {

            }

            override fun onTabReselected(tab: Tab?) {
            }
        })
    }




    @SuppressLint("MissingInflatedId")
    private fun installTablayout() {
        for(i in listTabs){
            val view  =  LayoutInflater.from(context).inflate(R.layout.custom_tab,binding.root,false)
            val textview:TextView  = view.findViewById(R.id.textViewtab)
            textview.text = i

            if(i.equals("New")){
                binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(view),true)
            }
             else{
                binding.tablayout.addTab(binding.tablayout.newTab().setCustomView(view))

            }

        }
    }

    private fun loadData(title:String) {
        lifecycleScope.launch {
            myViewModel.getPhoto(title).collect{
                when(it){
                    is Resources.LOADING->{}
                    is Resources.SUCCES->{
                        adapter.submitList(it.list)
                    }
                    is Resources.ERROR->{
                        Log.d("@@@@", "loadData:${it.msg}")}
                    else -> {}
                }
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home_page.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home_page().apply {
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

        findNavController().navigate(R.id.action_home_page_to_itemPage,bundle)
    }

    override fun logclickItem(listDataItem: ListDataItem) {

    }


}