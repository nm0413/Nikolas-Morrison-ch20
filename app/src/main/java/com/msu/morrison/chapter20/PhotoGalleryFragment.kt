package com.msu.morrison.chapter20

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.msu.morrison.chapter20.api.FlickrApi
import com.msu.morrison.chapter20.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create


private const val TAG = "PhotoGalleryFragment"


class PhotoGalleryFragment : Fragment() {

    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    private val photoGalleryViewModel:PhotoGalleryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val flickrApi:FlickrApi = retrofit.create<FlickrApi>()*/

        viewLifecycleOwner.lifecycleScope.launch {

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){

                photoGalleryViewModel.galleryItems.collect(){ items ->

                    binding.photoGrid.adapter = PhotoListAdapter(items)

                }
               // val response = PhotoRepository().fetchContents()
               // val response = PhotoRepository().fetchPhotos()
               // Log.d(TAG, "Response received:: $response")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}