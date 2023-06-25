package com.test.Neversitup.ui.fibonacci

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.Neversitup.databinding.FragmentFibonacciBinding
import or.th.mobile.librarymobile.common.GobalFun

class FibonacciFragment : Fragment() {

    companion object {
        fun newInstance() = FibonacciFragment()
    }
    private var _binding: FragmentFibonacciBinding? = null
    private lateinit var viewModel: FibonacciViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FibonacciViewModel::class.java)
        _binding = FragmentFibonacciBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView: TextView = binding.textFibonacci
        viewModel.text.observe(viewLifecycleOwner) { textView.text = it }

        binding.btnGetData.setOnClickListener {
            showFibonacci()
        }

    }

    val checkloop :Int=10
    var numloop :Int=0
    val fibonacciList: ArrayList<Int> =  ArrayList<Int>()
    fun showFibonacci() {
        numloop=0
        if(fibonacciList.size==0){
            fibonacciList.add(0)
            fibonacciList.add(1)
            while (numloop != checkloop) {
                fibonacciList.add(fibonacciList[fibonacciList.size-2]+fibonacciList[fibonacciList.size-1])
                numloop++
            }

        }else{
            while (numloop != checkloop) {
                fibonacciList.add(fibonacciList[fibonacciList.size-2]+fibonacciList[fibonacciList.size-1])
                numloop++
            }
        }
        var tempData=""
        for (item in fibonacciList){
            tempData += "$item,"
        }
        viewModel.setText(tempData)
    }


}