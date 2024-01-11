package com.pineapplepractice.infernohookah.view.rvadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Dishes
import com.pineapplepractice.infernohookah.data.Tea
import com.pineapplepractice.infernohookah.data.listOfDishes
import com.pineapplepractice.infernohookah.databinding.DishesItemBinding

class DishesRecyclerAdapter(private var items: List<Dishes>) :
    RecyclerView.Adapter<DishesRecyclerAdapter.InnerDishesViewHolder>() {

    /*    private var _binding: DishesItemBinding? = null
        private val binding get() = _binding!!*/

    //    private var items = listOfTea
//    private var returnItems = listOfTea
    private var returnItems = listOfDishes

    /*    private var items = listOfDishes
        private var returnItems = listOfDishes*/

    //    inner class InnerDishesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class InnerDishesViewHolder(binding: DishesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameOfDish = binding.nameOfDish
        val descriptionOfDish = binding.descriptionOfDish
        val countOfDish = binding.countOfDish
        val priceOfDish = binding.priceOfDish
        val dishesCardView = binding.dishesCardView
        val imageOfDish = binding.imageOfDish
    }

    override fun getItemCount() = items.size

    private fun setItems(newItems: List<Dishes>) {
        items = newItems
        notifyDataSetChanged()
    }

    /*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerDishesViewHolder {
            *//*        _binding = DishesItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )*//*
        val binding = DishesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerDishesViewHolder(binding.root)
//        return InnerDishesViewHolder(binding.root)
    } */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerDishesViewHolder {
        return InnerDishesViewHolder(DishesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InnerDishesViewHolder, position: Int) {
        val tea = items[position]

        holder.nameOfDish.text = tea.name
        holder.descriptionOfDish.text = tea.description
        holder.countOfDish.text = tea.count.toString() + "мл"
        holder.priceOfDish.text = tea.price.toString() + "р."

        Glide.with(holder.dishesCardView)
            .load(tea.image)
            .error(R.drawable.ic_menu)
            .centerCrop()
            .into(holder.imageOfDish)

/*        holder.itemView.apply {
            binding.nameOfDish.text = tea.name
            binding.descriptionOfDish.text = tea.description
            binding.countOfDish.text = tea.count.toString() + "мл"
            binding.priceOfDish.text = tea.price.toString() + "р."

            Glide.with(binding.dishesCardView)
                .load(tea.image)
                .error(R.drawable.ic_menu)
                .centerCrop()
                .into(binding.imageOfDish)
        }*/
    }

    /*    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is DishesViewHolder -> {
                    ViewCompat.setTransitionName(
                        holder.itemView.findViewById(R.id.dishesCardView),
                        items[position].id.toString()
                    )
                    holder.bind(items[position])
                }
            }
        }*/

    /*private fun setItems(newItems: List<Dishes>) {

    //    private fun setItems(newItems: List<Tea>) {
            items = newItems
            notifyDataSetChanged()
        }

        // Метод для фильтрации элементов по категории
        fun filterItemsByCategory(category: String) {
            returnItems()
            if (category == "Все") {
                val filteredItems = returnItems
                setItems(filteredItems)
            } else {
                val filteredItems = items.filter { it.description == category }
                setItems(filteredItems)
            }

        }*/

    /*    private fun returnItems(){
            items = returnItems
            notifyDataSetChanged()
        }*/

    fun updateData(newData: List<Dishes>) {
        items = newData
        notifyDataSetChanged()
        println("CategoryRecyclerAdapterЖ Data updated. New data size: ${newData.size}")
    }

}