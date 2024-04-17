package com.example.blackcoffer.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blackcoffer.R
import com.example.blackcoffer.adapter.AdapterRVBusiness
import com.example.blackcoffer.adapter.AdapterRVPersonal
import com.example.blackcoffer.adapter.AdapterRVServices
import com.example.blackcoffer.databinding.FragmentPersonalBinding
import com.example.blackcoffer.databinding.FragmentServicesBinding
import com.example.blackcoffer.model.modelPersonal.ModelPersonal
import com.example.blackcoffer.model.modelPersonal.ResponsePersonal
import com.example.blackcoffer.modelBusiness.ModelBusiness
import com.example.blackcoffer.modelServices.ModelServices
import com.example.blackcoffer.modelServices.ResponseServices
import com.example.blackcoffer.objects.RetrofitInstance
import retrofit2.Call

class FragmentServices : Fragment() {

    private lateinit var binding: FragmentServicesBinding
    private var itemAll = ArrayList<ModelServices>()
    private var adapterRVServices: AdapterRVServices? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireActivity()
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        val view = binding.root

        RetrofitInstance.apiInterface.getBlackCofferServicesUser().
        enqueue(object : retrofit2.Callback<ResponseServices?> {
            override fun onResponse(
                call: Call<ResponseServices?>,
                response: retrofit2.Response<ResponseServices?>
            ) {
                Log.d("createUser", "request response : " + response.body().toString());
                if(response.body()?.status == true){
                    binding.progressCircular.visibility = View.GONE
                    val userDataList = response.body()?.users
                    itemAll.clear()
                    if (userDataList != null) {
                        itemAll.addAll(userDataList)
                    }
                    adapterRVServices?.updateData(itemAll)
                    binding.servicesRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    adapterRVServices = AdapterRVServices(context, itemAll)
                    binding.servicesRV.adapter = adapterRVServices
                }else{
                    Toast.makeText(context, "Error : " + response?.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseServices?>, t: Throwable) {
                Log.d("createUser", "request t : " + t.message);
                Toast.makeText(context, "Error : "+  t.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.edtServices.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    val query = it.toString().toLowerCase()
                    val filteredItems = filteredItems(itemAll, query)
                    adapterRVServices?.updateData(filteredItems)
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        return view
    }
    fun filteredItems(items : ArrayList<ModelServices>, query : String):ArrayList<ModelServices>{
        val filteredItems = ArrayList<ModelServices>()
        if (query == null || query.isEmpty()){
            return items
        }
        var query = query.toLowerCase()
        for (item in items){
            val itemName = item.name.toLowerCase()
            if (itemName.contains(query)){
                filteredItems.add(item)
            }
        }
        return filteredItems
    }
}