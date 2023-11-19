package com.pineapplepractice.infernohookah.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.databinding.FragmentPromotionDetailsBinding
import com.pineapplepractice.infernohookah.view.activity.MainActivity

import com.pineapplepractice.infernohookah.viewmodel.PromotionDetailsViewModel
import com.pineapplepractice.infernohookah.viewmodel.PromotionsViewModel

class PromotionDetailsFragment : Fragment() {

    private var _binding: FragmentPromotionDetailsBinding? = null
    private val binding get() = _binding!!
    private val promotionDetailsViewModel: PromotionsViewModel by viewModels()

    private lateinit var promotion: Promotions


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromotionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetails()
        closeDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDetails() {
        promotion =
            arguments?.getParcelable<Promotions>(MainActivity.KEY_PROMOTIONS_DETAILS_FRAGMENT) as Promotions
        binding.descriptionOfPromotion.text = promotion.description
        binding.nameOfPromotion.text = promotion.name

        Glide.with(this)
            .load(promotion.image)
            .error(R.drawable.ic_logo_inferno)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(binding.imageOfPromotion)
    }

    private fun closeDetails() {
        binding.closeDetails.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_promotionDetailsFragment_to_promotionsFragment, null)
        }
    }


}