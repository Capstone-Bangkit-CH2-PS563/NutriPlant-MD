package com.app.nutri_plant.view.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.app.nutri_plant.databinding.ActivityRegisterBinding
import com.app.nutri_plant.helper.GlobalVar
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.DataUser
import com.app.nutri_plant.model.Status
import com.app.nutri_plant.view.base.BaseActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterActivity : BaseActivity() {
    private val binding by viewBinding(ActivityRegisterBinding::inflate)

    private var reference: DatabaseReference? = null

    private var userId = ""

    private var listUser = mutableListOf<DataUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadUser()
        setView()
        setListener()
    }

    private fun loadUser() {
        listUser = mutableListOf()

        reference = FirebaseDatabase.getInstance().getReference(GlobalVar.USER)
        reference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    for (dataSnapshot in snapshot.children) {
                        val data: DataUser = dataSnapshot.getValue(DataUser::class.java)!!
                        listUser.add(data)
                    }
                } else {

                }
            }

            override fun onCancelled(@NonNull error: DatabaseError) {
                Log.d("firebaseError", error.toString())
            }
        })
    }


    private fun setView(){

    }

    private fun setListener(){
        binding.btnRegister.setOnClickListener {
            val fullname = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (fullname.isEmpty()){
                toast("Nama harus diisi!")
                return@setOnClickListener
            }
            if (email.isEmpty()){
                toast("Email harus diisi!")
                return@setOnClickListener
            }
            if (pass.isEmpty()){
                toast("Password harus diisi!")
                return@setOnClickListener
            }

            authViewModel.register(
                fullname, email, pass, pass
            ).observe(this){
                it?.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            val data = resource.data
                            toast(data?.message.toString())
                            pLoading.dismissDialog()

                            onBackPressed()
                        }
                        Status.ERROR -> {
                            val data = resource.data
                            toast(data?.message.toString())
                            pLoading.dismissDialog()
                        }
                        Status.LOADING -> {
                            pLoading.showLoading()
                        }
                    }
                }
            }

//            userId = GlobalVar.generateId()
//
//            val dataUser = DataUser(
//                userId.toInt(),
//                fullname,
//                email,
//                pass
//            )
//
//            val checkedEmail = listUser.any { it.email == email }
//
////            toast(checkedEmail.toString())
//
//            if (checkedEmail){
//                toast("Registrasi gagal. Email sudah digunakan!")
//                return@setOnClickListener
//            } else {
//                if (listUser.isEmpty()){
//                    reference = FirebaseDatabase.getInstance().getReference(GlobalVar.USER).child(
//                        userId
//                    )
//                    reference!!.setValue(dataUser).addOnCompleteListener(OnCompleteListener {
//                        toast("Registrasi berhasil")
//
//                        val i = Intent(this, LoginActivity::class.java)
//                        startActivity(i)
//                    })
//                } else {
//                    reference = FirebaseDatabase.getInstance().getReference(GlobalVar.USER).child(
//                        userId
//                    )
//                    reference!!.setValue(dataUser).addOnCompleteListener(OnCompleteListener {
//                        toast("Registrasi berhasil")
//
//                        val i = Intent(this, LoginActivity::class.java)
//                        startActivity(i)
//                    })
//                }
//
//            }

        }

        binding.tvLogin.setOnClickListener {
            onBackPressed()
        }
    }
}