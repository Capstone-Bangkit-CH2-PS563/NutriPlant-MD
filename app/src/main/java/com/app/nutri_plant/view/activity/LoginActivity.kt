package com.app.nutri_plant.view.activity

import android.content.Intent
import android.os.Bundle
import com.app.nutri_plant.databinding.ActivityLoginBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.DataUser
import com.app.nutri_plant.model.Status
import com.app.nutri_plant.view.base.BaseActivity
import com.google.firebase.database.DatabaseReference

class LoginActivity : BaseActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)

    private var reference: DatabaseReference? = null
    private var listUser = mutableListOf<DataUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setListener()
    }

    private fun setView(){

    }

    private fun setListener(){
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (email.isEmpty()){
                toast("Email harus diisi!")
                return@setOnClickListener
            }
            if (pass.isEmpty()){
                toast("Password harus diisi!")
                return@setOnClickListener
            }

            authViewModel.login(
                email, pass
            ).observe(this){
                it?.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            val data = resource.data

//                            session.dataUser = data
                            session.isLogin(true)

                            toast(data?.message.toString())
                            pLoading.dismissDialog()

                            val i = Intent(this@LoginActivity, HomeActivity::class.java)
                            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(i)
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

//            reference = FirebaseDatabase.getInstance().getReference(GlobalVar.USER)
//            reference!!.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(@NonNull snapshot: DataSnapshot) {
//                    if (snapshot.hasChildren()) {
//                        for (dataSnapshot in snapshot.children) {
//                            var dataUser: DataUser? = dataSnapshot.getValue(DataUser::class.java)
//                            if (dataUser != null) {
//                                listUser.add(dataUser)
//                            }
//
//                        }
//
//                        var correctUser = listUser.any { it.email == email && it.password == pass }
//
//                        if (correctUser){
//                            var dataUser = listUser.filter { it.email == email && it.password == pass }[0]
//                            intentLogin(dataUser)
//                        } else {
//                            toast("Email atau password salah")
//                        }
//                    }
//                }
//
//                override fun onCancelled(@NonNull error: DatabaseError) {}
//            })
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun intentLogin(dataUser: DataUser){
        session.dataUser = dataUser
        session.isLogin(true)

        toast("Login berhasil")

        val i = Intent(this@LoginActivity, HomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

}