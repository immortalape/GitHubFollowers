package com.example.githubfollowers.ui.home

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.githubfollowers.R
import com.example.githubfollowers.ui.SharedViewModel
import kotlinx.android.synthetic.main.get_followers_fragment.*
import kotlinx.android.synthetic.main.get_followers_fragment.view.*


class GetFollowersFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.get_followers_fragment, container, false)
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        view.search_user_edit_text.setOnEditorActionListener(TextView.OnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getFollowersClicked()
                hideKeyboardFrom(requireContext(), requireView())
            }
            return@OnEditorActionListener false
        })

        view.get_followers_button.setOnClickListener {
            getFollowersClicked()
        }

        return view
    }

    private fun getFollowersClicked(){
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        when {
            !isConnected -> {
                Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show()
            }
            search_user_edit_text.text.isNullOrBlank() -> {
                Toast.makeText(context, "Please enter a valid username!", Toast.LENGTH_SHORT).show()
                search_user_edit_text.text.clear()
            }
            else -> {
                val userName = search_user_edit_text.text.toString()
                val action = GetFollowersFragmentDirections.navigateToSearchResults(userName)
                findNavController().navigate(action)
                search_user_edit_text.text.clear()
            }
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}