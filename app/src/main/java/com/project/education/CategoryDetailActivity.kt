package com.project.education

import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class CategoryDetailActivity : AppCompatActivity() {

  private lateinit var webView: WebView

  companion object {
    const val EXTRA_TITLE = "title"
    const val EXTRA_URL = "url"

    fun newIntent(context: Context, category: Category): Intent {
      val detailIntent = Intent(context, CategoryDetailActivity::class.java)

      detailIntent.putExtra(EXTRA_TITLE, category.title)
      detailIntent.putExtra(EXTRA_URL, category.instructionUrl)

      return detailIntent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_category_detail)

    // retrieve category data
    val title = intent.extras?.getString(EXTRA_TITLE)
    val url = intent.extras?.getString(EXTRA_URL)

    setTitle(title)

    webView = findViewById(R.id.detail_web_view)

    if (url != null) {
      webView.loadUrl(url)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }
}