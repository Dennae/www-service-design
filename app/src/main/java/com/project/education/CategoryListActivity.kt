package com.project.education

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CategoryListActivity : AppCompatActivity() {

  private lateinit var listView: ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_category_list)

    listView = findViewById<ListView>(R.id.category_list_view)

    // Action Bar back button
    val actionbarCategoryList = supportActionBar
    if (actionbarCategoryList != null) {
      actionbarCategoryList.setDisplayHomeAsUpEnabled(true)
      actionbarCategoryList.title = "Back to Profile Page"
    }

    val categoryList = Category.getCategoriesFromFile(this)

    val adapter = CategoryAdapter(this, categoryList)
    listView.adapter = adapter

    val context = this
    // onItemClick arguments: parent, view (row), position, id
    listView.setOnItemClickListener { _, _, position, _ ->
      val selectedCategory = categoryList[position]

      val detailIntent = CategoryDetailActivity.newIntent(context, selectedCategory)

      startActivity(detailIntent)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }
}
