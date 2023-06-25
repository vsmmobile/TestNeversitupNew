package com.test.Neversitup.ui.filterArray

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.Neversitup.R
import com.test.Neversitup.databinding.FragmentFibonacciBinding
import com.test.Neversitup.ui.fibonacci.FibonacciViewModel
import or.th.mobile.librarymobile.common.GobalFun

class FilterArrayFragment : Fragment() {

    companion object {
        fun newInstance() = FilterArrayFragment()
    }

    private var _binding: FragmentFibonacciBinding? = null
    private lateinit var viewModel: FilterArrayViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FilterArrayViewModel::class.java)
        _binding = FragmentFibonacciBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView: TextView = binding.textFibonacci
        viewModel.text.observe(viewLifecycleOwner) { textView.text = it }
        binding.btnGetData.setOnClickListener {
            ShowFilterArray()
        }

    }


    fun ShowFilterArray() {
        var arr: ArrayList<Int> = ArrayList<Int>()
        var arr1 = arrayOf(1, 4, 44, 2, 15, 8, 45)
        var arr2 = arrayOf(1, 3, 5, 15, 13, 7, 9, 2)
        var temparr1 = ""
        for (item in arr1) {
            temparr1 += "$item,"
        }
        var temparr2 = ""
        for (item in arr2) {
            temparr2 += "$item,"
        }
        for (item1 in arr1) {
            for (item2 in arr2) {
                if (item1 == item2) {
                    arr.add(item1)
                }
            }
        }

        var temparr = ""
        for (item in arr) {
            temparr += "$item,"
        }
        viewModel.setText("สมาชิก arr1=$temparr1\n" + "สมาชิก arr2=$temparr2\n" + "สมาชิกคงเหลือของ arr1=$temparr\n")

    }


}