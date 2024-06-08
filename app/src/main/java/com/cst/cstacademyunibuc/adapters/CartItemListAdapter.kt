package com.cst.cstacademyunibuc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademyunibuc.R
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.models.CartItemModel
import com.cst.cstacademyunibuc.models.CartItemType
import com.cst.cstacademyunibuc.models.CategoryModel
import com.cst.cstacademyunibuc.models.ProductModel

class CartItemListAdapter(
    private var list: List<CartItemModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(newList: List<CartItemModel>) {
        val diffUtil = DiffUtil.calculateDiff(CartItemsDiffCallback(list, newList))
        list = newList
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int = list[position].type.key

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        "onCreateViewHolder; viewType=$viewType".logErrorMessage("ProductAdapter")

        return when(viewType) {
            CartItemType.PRODUCT.key -> ProductViewHolder(
                layoutInflater.inflate(R.layout.item_product_cell, parent, false)
            )

            CartItemType.CATEGORY.key -> CategoryViewHolder(
                layoutInflater.inflate(R.layout.item_category_cell, parent, false)
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list.getOrNull(position) ?: return

        when(holder) {
            is ProductViewHolder -> holder.onBind((item as? ProductModel) ?: return)
            is CategoryViewHolder -> holder.onBind((item as? CategoryModel) ?: return)
        }

        "onBindViewHolder; position=$position".logErrorMessage("ProductAdapter")
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleTextView: TextView
        private val descriptionTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.tv_title)
            descriptionTextView = view.findViewById(R.id.tv_description)
        }

        fun onBind(product: ProductModel) {
            titleTextView.text = product.title
            descriptionTextView.text = product.description
        }
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleTextView: TextView
        private val descriptionTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.tv_title)
            descriptionTextView = view.findViewById(R.id.tv_description)
        }

        fun onBind(product: CategoryModel) {
            titleTextView.text = product.title
            descriptionTextView.text = product.description
        }
    }

    inner class CartItemsDiffCallback(
        private val oldList: List<CartItemModel>,
        private val newList: List<CartItemModel>
    ): DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}