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
import com.example.blackcoffer.adapter.AdapterRVBusiness
import com.example.blackcoffer.databinding.FragmentBusinessesBinding
import com.example.blackcoffer.modelBusiness.ModelBusiness
import com.example.blackcoffer.modelBusiness.ResponseBusiness
import com.example.blackcoffer.objects.RetrofitInstance
import retrofit2.Call

class FragmentBusinesses : Fragment() {

    private lateinit var binding: FragmentBusinessesBinding
    private var itemAll = ArrayList<ModelBusiness>()
    private var adapterRVBusiness: AdapterRVBusiness? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireActivity()
        binding = FragmentBusinessesBinding.inflate(inflater, container, false)
        val view = binding.root

        RetrofitInstance.apiInterface.getBlackCofferBusinessUser().
        enqueue(object : retrofit2.Callback<ResponseBusiness?> {
            override fun onResponse(
                call: Call<ResponseBusiness?>,
                response: retrofit2.Response<ResponseBusiness?>
            ) {
                Log.d("createUser", "request response : " + response.toString());
                if(response.body()?.status == true){
                    binding.progressCircular.visibility = View.GONE
                    val userDataList = response.body()?.users
                    itemAll.clear()
                    if (userDataList != null) {
                        itemAll.addAll(userDataList)
                    }
                    adapterRVBusiness?.updateData(itemAll)
                    binding.businessRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    adapterRVBusiness = AdapterRVBusiness(context, itemAll)
                    binding.businessRV.adapter = adapterRVBusiness
                }else{
                    Toast.makeText(context, "Error : " + response?.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseBusiness?>, t: Throwable) {
                Log.d("createUser", "request t : " + t.message);
                Toast.makeText(context, "Error : "+  t.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.edtBusiness.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    val query = it.toString().toLowerCase()
                    val filteredItems = filteredItems(itemAll, query)
                    adapterRVBusiness?.updateData(filteredItems)
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        return view
    }
    fun filteredItems(items : ArrayList<ModelBusiness>,query : String):ArrayList<ModelBusiness>{
        val filteredItems = ArrayList<ModelBusiness>()
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