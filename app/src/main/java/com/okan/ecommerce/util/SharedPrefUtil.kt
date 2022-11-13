package com.okan.ecommerce.util

import android.content.Context
import com.okan.ecommerce.data.BaseApplication

class SharedPrefUtil {
    companion object {
        private val SHARED_PREFS_FILE = "com.okan.ecommerce.pazarama"
        private val PASSKEY = "PASSKEY"
        private val FAVORITE = "FAVORITE"

        fun setPassKey(context: Context, passKey: String) {
            context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE).edit()
                .putString(PASSKEY, passKey).apply()
        }

        fun getPassKey(context: Context): String {
            return context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
                .getString(PASSKEY, "DEFAULT")!!
        }


        private var favoriteSet = HashSet<String>()
        fun addFavorite(context: Context,productName : String){
            favoriteSet.add(productName)
            setFavorite(context,favoriteSet)
        }

        fun remoteFavorite(context: Context,productName : String){
            if(favoriteSet.contains(productName)){
                favoriteSet.remove(productName)
                setFavorite(context,favoriteSet)
            }
        }

        fun getFavorite(context: Context): MutableSet<String>? {
            favoriteSet.clear()
            favoriteSet.addAll(context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
                .getStringSet(FAVORITE,favoriteSet)!!)
            return favoriteSet
        }

        private fun setFavorite(context: Context, set : MutableSet<String>) {
            context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE).edit()
                .putStringSet(FAVORITE, set).apply()
        }


    }
}