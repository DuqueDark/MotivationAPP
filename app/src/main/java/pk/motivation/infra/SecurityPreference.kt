package pk.motivation.infra

import android.content.Context

class SecurityPreference(context: Context) {

    private val mShared = context.getSharedPreferences("motivation",Context.MODE_PRIVATE)

    fun storeString(key:String,value:String){
        mShared.edit().putString(key, value).apply()
    }

    fun getString(key: String):String{
        return mShared.getString(key,"PERSON NAME") ?: ""
    }

}