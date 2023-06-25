package com.test.Neversitup.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.Neversitup.common.Logger
import com.test.Neversitup.MainViewModel
import com.test.Neversitup.connectApi.ModelResponce.currentprice.*
import com.test.Neversitup.connectApi.NetworkClientViewModel
import com.test.Neversitup.database.DbModel
import com.test.Neversitup.databinding.FragmentHomeBinding
import com.test.Neversitup.ui.listView.CustomListAdapter
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import or.th.mobile.librarymobile.common.GobalFun
import or.th.mobile.librarymobile.common.GobalValue
import java.util.*
import com.test.Neversitup.R
import java.math.BigDecimal
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModelNetworkClient: NetworkClientViewModel
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelMain: MainViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelNetworkClient = ViewModelProvider(this).get(NetworkClientViewModel::class.java)
        viewModelMain = ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textViewUsd: TextView = binding.textUSD
        val textViewGBP: TextView = binding.textGBP
        val textViewEUR: TextView = binding.textEUR
        val textViewBTC: TextView = binding.textBTC
        viewModel.textUsd.observe(viewLifecycleOwner) { textViewUsd.text = it }
        viewModel.textGBP.observe(viewLifecycleOwner) { textViewGBP.text = it }
        viewModel.textEUR.observe(viewLifecycleOwner) { textViewEUR.text = it }
        viewModel.textBTC.observe(viewLifecycleOwner) { textViewBTC.text = it }

        binding.edittextBTC.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.isNotEmpty()){
                    var tempBtc:String="0"
                    when(tempValue){
                        "USD"->{
                            tempBtc=usdToBtc(s.toString())
                            Logger.debugAppName("USD--->"+tempBtc)
                        }
                        "GBP"->{
                            tempBtc=gbpToBtc(s.toString())
                            Logger.debugAppName("GBP--->"+tempBtc)
                        }
                        "EUR"->{
                            tempBtc=eurToBtc(s.toString())
                            Logger.debugAppName("EUR----->"+tempBtc)
                        }
                    }
                    viewModel.setTextBTC(tempBtc.toString())
                }

            }
        })
        binding.btnGetData.setOnClickListener {
            getCurrentprice()
        }
        loopCurrentprice()
        setSpinner()
    }
    private fun loopCurrentprice(){
        val timer = Timer()

        val task = object : TimerTask() {
            override fun run() {
                getCurrentprice()
            }
        }
        timer.schedule(task, 0L, 60 * 1000L)
        //timer.schedule(task, 0L,  3000L)

    }

    private fun getCurrentprice() {
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            viewModelNetworkClient.sendDataToApi(methodsApi= GobalValue.methodsApiCurrentprice).also {
                if (it.code == "C000") {
                    try{
                        val obj: ResponceCurrentprice = it.obj as ResponceCurrentprice
                        val objBpi: Bpi =obj.bpi as Bpi
                        val objUSD: USD = objBpi.USD as USD
                        val objGBP: GBP = objBpi.GBP as GBP
                        val objEUR: EUR = objBpi.EUR as EUR
                        Logger.debugAppName("id----------------->"+objUSD.rate)
                        viewModel.setTextUsd(objUSD.rate.toString())
                        viewModel.setTextGBP(objGBP.rate.toString())
                        viewModel.setTextEUR(objEUR.rate.toString())
                        DbModel.CurrentpriceDetail(
                            USD = objUSD.rate.toString(),
                            GBP = objGBP.rate.toString(),
                            EUR = objEUR.rate.toString()
                        ).also {
                            if (viewModel.insertCurrentprice(it).value == true) {
                                selectDataShow()
                            }
                        }

                    }catch (e:Exception){
                        Logger.debugAppName("Exception----------------->"+e.message)
                    }

                }

            }

        }
    }
    fun usdToBtc(value:String): String {
        val usdValue = value.toDouble()
        val exchangeRate =0.000032553
        val btcvalure=  (usdValue * exchangeRate)
        val btcvalureB= BigDecimal(btcvalure)
        return GobalFun.supString(btcvalureB.toString(),0,11)
    }


    fun gbpToBtc(value:String): String {

        val gbpAmount = value.toDouble()
        val exchangeRate =0.000041368
        val btcvalure=  (gbpAmount * exchangeRate)
        val btcvalureB= BigDecimal(btcvalure)
        return GobalFun.supString(btcvalureB.toString(),0,11)
    }
    fun eurToBtc(value:String): String {

        val eurAmount = value.toDouble()
        val exchangeRate =0.000035574
        val btcvalure=  (eurAmount * exchangeRate)
        val btcvalureB= BigDecimal(btcvalure)
        return GobalFun.supString(btcvalureB.toString(),0,11)
    }

     fun selectDataShow(){
         viewModel.selectCurrentprice().value!!.let {
            setListView(it)
        }
    }
    fun setListView(items: List<DbModel.CurrentpriceDetail>) {
        val listView = binding.listviewCurrentprice
        listView.removeAllViewsInLayout()
        val adapter = context?.let { CustomListAdapter(it, items) }
        listView.adapter = adapter
    }
    var tempValue="USD"
    fun setSpinner(){
        val adapter = context.let {
            it?.let { it1 -> ArrayAdapter.createFromResource(it1, R.array.arrayCurrentprice, R.layout.layout_spinner_black) }
        }
        adapter!!.setDropDownViewResource(R.layout.layout_spinner_item)
        binding!!.spinnerEcgM.adapter = adapter
        binding!!.spinnerEcgM.setSelection(0)
        binding!!.spinnerEcgM.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.edittextBTC.setText("")
                viewModel.setTextBTC("0")
                tempValue=parent!!.getItemAtPosition(position).toString()
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}