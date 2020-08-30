package com.example.githubfollowers.utils

import androidx.fragment.app.Fragment
import com.example.githubfollowers.R
import com.example.githubfollowers.ui.searchResults.SearchResultsFragment

class FragmentTransaction : Fragment() {

    fun transaction(layoutId : Int, fragment: Fragment){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(layoutId, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}