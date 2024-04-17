package com.example.blackcoffer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blackcoffer.ModelPurpose
import com.example.blackcoffer.R
import com.example.blackcoffer.adapter.AdapterRVRefine
import com.example.blackcoffer.databinding.ActivityRefineBinding
import com.example.blackcoffer.modelRefine.RequestRefine
import com.example.blackcoffer.modelRefine.ResponseRefine
import com.example.blackcoffer.objects.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRefine : AppCompatActivity() {

    lateinit var binding: ActivityRefineBinding

    private val itemList = mutableListOf(
        ModelPurpose("Coffee", false),
        ModelPurpose("Business", false),
        ModelPurpose("Hobbies", false),
        ModelPurpose("Friendship", false),
        ModelPurpose("Movies", false),
        ModelPurpose("Dinning", false),
        ModelPurpose("Dating", false),
        ModelPurpose("Matrimony", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRefineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner()

        binding.backBtn.setOnClickListener{
            finish()
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this,4)
        binding.recyclerView.adapter = AdapterRVRefine(itemList)

        binding.btnSave.setOnClickListener {
            binding.btnSave.text = "Please wait..."

            val availability = binding.spinner.toString()
            val status = binding.edtStatus.text.toString()
            val seekBar = binding.seekBar.toString()
            val purpose = itemList

            val createUserRequest = RequestRefine(
                availability, status, seekBar, purpose
            )

            RetrofitInstance.apiInterface.saveRefineData(createUserRequest).
            enqueue(object : Callback<ResponseRefine?> {
                override fun onResponse(
                    call: Call<ResponseRefine?>,
                    response: Response<ResponseRefine?>
                ) {
                    Log.d("saveData", "request response : " + response?.body()?.message.toString());
                    if(response.body()?.status == true){
                        Toast.makeText(this@ActivityRefine, response?.body()?.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@ActivityRefine, "Error : " + response?.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseRefine?>, t: Throwable) {
                    Log.d("createUser", "request t : " + t.message);
                    Toast.makeText(this@ActivityRefine, "Error : "+  t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun spinner(){
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (parent?.getChildAt(0) as? TextView)?.setTextColor(getColor(R.color.black))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinner.setOnItemSelectedListener(listener)
    }
}