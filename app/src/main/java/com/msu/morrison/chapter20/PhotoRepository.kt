package com.msu.morrison.chapter20

import com.msu.morrison.chapter20.api.FlickrApi
import com.msu.morrison.chapter20.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository {

    private val flickrApi:FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        flickrApi = retrofit.create<FlickrApi>()
    }

    suspend fun fetchPhotos():List<GalleryItem> = flickrApi.fetchPhotos().photos.galleryItems
}