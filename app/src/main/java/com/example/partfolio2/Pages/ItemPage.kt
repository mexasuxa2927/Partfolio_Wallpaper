package com.example.partfolio2.Pages

import android.app.WallpaperColors
import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.partfolio2.App.App
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem
import com.example.partfolio2.Database.DataEntity
import com.example.partfolio2.R
import com.example.partfolio2.ViewModel.MyViewModel
import com.example.partfolio2.databinding.FragmentItemPageBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var viewModel: MyViewModel
    private lateinit var listDataItem: ListDataItem
    private var liked  = false
    lateinit var wallpaperManager:WallpaperManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponents.inject(this)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding :FragmentItemPageBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding  = FragmentItemPageBinding.inflate(inflater,container,false)


        loadData()
        setImage()
        buttonsClicks()
        wallpaperManager  = WallpaperManager.getInstance(context)




        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun buttonsClicks() {
        binding.homesreen.setOnClickListener{

            val url = URL(listDataItem.urls.regular)
            lifecycleScope.launch(Dispatchers.IO) {
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                wallpaperManager.setBitmap(image)


            }.let {
                Toast.makeText(context, "Homescreen set wallpaper", Toast.LENGTH_SHORT).show()
            }


        }
        binding.locksreen.setOnClickListener{
            Toast.makeText(context, "Lockscreen set wallpaper", Toast.LENGTH_SHORT).show()
            val url = URL(listDataItem.urls.regular)
            lifecycleScope.launch(Dispatchers.IO) {
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                wallpaperManager.setBitmap(image,null,false,WallpaperManager.FLAG_LOCK)

            }

        }
        // Inflate the layout for this fragment
        binding.saveBtn.setOnClickListener {
            if(requireArguments().getString("data")!!.isNotEmpty()){
               if(liked){
                   var dataItem  =  DataEntity(requireArguments().getString("data")!!)
                   viewModel.deleteData(dataItem).let {
                       Toast.makeText( requireContext(), "unsave", Toast.LENGTH_SHORT).show()
                       liked = false
                       binding.saveBtn.setImageResource(R.drawable.save_icon_unliked)
                   }
               }
                else{
                   var dataItem  =  DataEntity(requireArguments().getString("data")!!)
                   viewModel.savePhoto(dataItem).let {
                       Toast.makeText( requireContext(), "save", Toast.LENGTH_SHORT).show()
                       liked = true
                       binding.saveBtn.setImageResource(R.drawable.save_icon_liked)
                   }

               }
            }
        }
    }

    private fun setImage() {
        if(!requireArguments().isEmpty){
            listDataItem  = Gson().fromJson(requireArguments().getString("data"),ListDataItem::class.java)

            Glide.with(this).load(listDataItem.urls.regular).placeholder(R.drawable.asasas).into(binding.imageview)
        }
        else
        {

        }

    }

    private fun loadData() {
        viewModel.getAllData().observe(viewLifecycleOwner, Observer {
            if(requireArguments().getString("data")!!.isNotEmpty()){
                var str   =  requireArguments().getString("data").toString()
                if(it.contains(DataEntity(str))){
                    liked   = true
                    binding.saveBtn.setImageResource(R.drawable.save_icon_liked)
                }else{
                    liked  =  false
                    binding.saveBtn.setImageResource(R.drawable.save_icon_unliked)
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}