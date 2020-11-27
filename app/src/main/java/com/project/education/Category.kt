package com.project.education

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class Category(
    val title: String,
    val description: String,
    val imageUrl: String,
    val instructionUrl: String,
    val label: String) {

  companion object {

    fun getCategoriesFromFile(context: Context): ArrayList<Category> {
      val categoryList = ArrayList<Category>()

      try {
        // Load data
        val jsonString = loadJsonFromAsset("categories.json", context)
        val json = JSONObject(jsonString)
        val categories = json.getJSONArray("categories")

        // Get Category objects from data
        (0 until categories.length()).mapTo(categoryList) {
          Category(categories.getJSONObject(it).getString("categoryTitle"),
              categories.getJSONObject(it).getString("categoryDescription"),
              categories.getJSONObject(it).getString("categoryImage"),
              categories.getJSONObject(it).getString("categoryUrl"),
              categories.getJSONObject(it).getString("categoryLabel"))
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }

      return categoryList
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
      var json: String? = null

      try {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
      } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
      }

      return json
    }
  }
}