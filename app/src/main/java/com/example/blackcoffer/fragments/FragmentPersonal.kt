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
import com.example.blackcoffer.adapter.AdapterRVPersonal
import com.example.blackcoffer.databinding.FragmentPersonalBinding
import com.example.blackcoffer.model.modelPersonal.ModelPersonal
import com.example.blackcoffer.model.modelPersonal.ResponsePersonal
import com.example.blackcoffer.modelBusiness.ModelBusiness
import com.example.blackcoffer.objects.RetrofitInstance
import retrofit2.Call

class FragmentPersonal : Fragment() {

    private lateinit var binding: FragmentPersonalBinding
    private var itemAll = ArrayList<ModelPersonal>()
    private var adapterRVPersonal: AdapterRVPersonal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireActivity()
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        val view = binding.root

        RetrofitInstance.apiInterface.getBlackCofferPersonalUser().
        enqueue(object : retrofit2.Callback<ResponsePersonal?> {
            override fun onResponse(
                call: Call<ResponsePersonal?>,
                response: retrofit2.Response<ResponsePersonal?>
            ) {
                Log.d("createUser", "request response : " + response.body().toString());
                if(response.body()?.status == true){
                    binding.progressCircular.visibility = View.GONE
                    val userDataList = response.body()?.users
                    itemAll.clear()
                    if (userDataList != null) {
                        itemAll.addAll(userDataList)
                    }
                    adapterRVPersonal?.updateData(itemAll)
                    binding.personalRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    adapterRVPersonal = AdapterRVPersonal(context, itemAll)
                    binding.personalRV.adapter = adapterRVPersonal
                }else{
                    Toast.makeText(context, "Error : " + response?.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePersonal?>, t: Throwable) {
                Log.d("createUser", "request t : " + t.message);
                Toast.makeText(context, "Error : "+  t.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.edtPersonal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    val query = it.toString().toLowerCase()
                    val filteredItems = filteredItems(itemAll, query)
                    adapterRVPersonal?.updateData(filteredItems)
                }
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        return view
    }
    fun filteredItems(items : ArrayList<ModelPersonal>,query : String):ArrayList<ModelPersonal>{
        val filteredItems = ArrayList<ModelPersonal>()
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