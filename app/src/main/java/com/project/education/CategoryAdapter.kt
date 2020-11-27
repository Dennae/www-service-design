package com.project.education

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso


class CategoryAdapter(private val context: Context,
                      private val dataSource: ArrayList<Category>) : BaseAdapter() {

  private val inflater: LayoutInflater
      = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

  companion object {
    private val LABEL_COLORS = hashMapOf(
        "Easy" to R.color.colorEasy,
        "Intermediate" to R.color.colorIntermediate,
        "Advanced" to R.color.colorAdvanced,
    )
  }

  override fun getCount(): Int {
    return dataSource.size
  }

  override fun getItem(position: Int): Any {
    return dataSource[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val view: View
    val holder: ViewHolder


    if (convertView == null) {

      view = inflater.inflate(R.layout.list_item_category, parent, false)

      holder = ViewHolder()
      holder.thumbnailImageView = view.findViewById(R.id.category_list_thumbnail) as ImageView
      holder.titleTextView = view.findViewById(R.id.category_list_title) as TextView
      holder.subtitleTextView = view.findViewById(R.id.category_list_subtitle) as TextView
      holder.detailTextView = view.findViewById(R.id.category_list_detail) as TextView

      view.tag = holder
    } else {

      view = convertView
      holder = convertView.tag as ViewHolder
    }

    val titleTextView = holder.titleTextView
    val subtitleTextView = holder.subtitleTextView
    val detailTextView = holder.detailTextView
    val thumbnailImageView = holder.thumbnailImageView

    val category = getItem(position) as Category

    titleTextView.text = category.title
    subtitleTextView.text = category.description
    detailTextView.text = category.label

    Picasso.get().load(category.imageUrl).placeholder(R.drawable.dog_profile_pic).into(thumbnailImageView)

    val titleTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
    titleTextView.typeface = titleTypeFace

    val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
    subtitleTextView.typeface = subtitleTypeFace

    val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
    detailTextView.typeface = detailTypeFace

    detailTextView.setTextColor(
        ContextCompat.getColor(context, LABEL_COLORS[category.label] ?: R.color.colorPrimary))

    return view
  }

  private class ViewHolder {
    lateinit var titleTextView: TextView
    lateinit var subtitleTextView: TextView
    lateinit var detailTextView: TextView
    lateinit var thumbnailImageView: ImageView
  }
}
