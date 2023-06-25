package com.test.Neversitup.ui.primeNumber

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

class PrimeNumberFragment : Fragment() {

    companion object {
        fun newInstance() = PrimeNumberFragment()
    }


    private var _binding: FragmentFibonacciBinding? = null
    private lateinit var viewModel: PrimeNumberViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PrimeNumberViewModel::class.java)
        _binding = FragmentFibonacciBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView: TextView = binding.textFibonacci
        viewModel.text.observe(viewLifecycleOwner) { textView.text = it }

        binding.btnGetData.setOnClickListener {
            showPrimeNumber()

            //ShowFilterArray()
        }

    }



    val checkloop :Int=100
    var numloop :Int=1
    // val primeNumberList: ArrayList<Int> =  ArrayList<Int>()
    fun showPrimeNumber(){
        var  primeNumberList: ArrayList<Int> =  ArrayList<Int>()
        numloop=1
        while (numloop <= checkloop) {
            if(checkPrimeNumber(numloop)){
                primeNumberList.add(numloop)
            }
            numloop++

        }


        var tempData=""
        for (item in primeNumberList){
            tempData += "$item,"
        }
        viewModel.setText(tempData+"=(จำนวนเฉพาะมี ${primeNumberList.size } ตัว)")
    }

    fun checkPrimeNumber(value:Int):Boolean{
        var tempnum=0
        var tempValue=value
        while (tempValue > 1 ) {
            tempValue--
            val  tempD=( value.toDouble()/tempValue.toDouble() )
            var tempS:String =tempD.toString()
            val tempIndex = tempS.indexOf(".")
            tempS=GobalFun.supString(tempS,tempIndex,tempS.length)
            if(tempS==".0"){
                tempnum++
            }
        }
        if(tempnum==1)
            return true

        return false
    }

    fun ShowFilterArray(){
        var  arr: ArrayList<Int> =  ArrayList<Int>()
        var arr1 = arrayOf(1,4,44,2,15,8,45)
        var arr2 = arrayOf(1,3,5,15,13,7,9,2)
        var temparr1=""
        for (item in arr1){
            temparr1 += "$item,"
        }
        var temparr2=""
        for (item in arr2){
            temparr2 += "$item,"
        }
        for (item1 in arr1){
            for (item2 in arr2){
                if(item1==item2){
                    arr.add(item1)
                }
            }
        }

        var temparr=""
        for (item in arr){
            temparr += "$item,"
        }
        viewModel.setText("สมาชิก arr1=$temparr1\n"  +"สมาชิก arr2=$temparr2\n"+"สมาชิกคงเหลือของ arr1=$temparr\n")

    }
}