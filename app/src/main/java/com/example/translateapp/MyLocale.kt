package com.example.translateapp

import android.content.Context
import android.util.DisplayMetrics
import java.util.Locale

class MyLocale (
    private var context: Context,  private var language :String) {


    fun setNewLocale(){
        val  newLocale = Locale(language)
        Locale.setDefault(newLocale)

        val  newResources=context.resources

        val  newConfig = newResources.configuration
        newConfig.setLocale(newLocale)

        newResources.updateConfiguration(newConfig, DisplayMetrics())

    }




}