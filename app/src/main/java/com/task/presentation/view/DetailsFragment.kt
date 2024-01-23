package com.task.presentation.view

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.task.R
import com.task.databinding.FragmentDetailsBinding
import com.task.core.uicomponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/** This Fragment is used for
 * showing the details of selected item
 */
@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun getFragmentView() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            loadDetails(it)
        }
    }

    /** This is to load the data which
     * we got from the recycler view
     */
    private fun loadDetails(it: Bundle) {
        val name = it.getString("name")
        val code = it.getString("code")
        val country = it.getString("country")
        binding.textTitle.text = name
        binding.textDescription.text = "Country code:" + code+" " +"Country Name "+ country

    }
}