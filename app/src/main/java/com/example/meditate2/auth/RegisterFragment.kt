package com.example.meditate2.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.meditate2.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.et_email
import kotlinx.android.synthetic.main.fragment_register.et_password
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val btn: Button = view.findViewById(R.id.btn_register)

        // Registration
        btn.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val rePassword = et_rePassword.text.toString()


            if (email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(activity, "You need to fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    // else if
                    d("RegisterActivity", "Successfully created user with uid: ${it.result?.user?.uid}")
                    val intent = Intent(activity, ProfileActivity::class.java)
                    intent.putExtra("username", et_name.text.toString())
                    startActivity(intent)
                    activity?.finish()
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }

        }
        return view
    }

    /*companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_register -> {
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                val rePassword = et_rePassword.text.toString()

                if(email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(activity, "You need to fill all fields", Toast.LENGTH_SHORT).show()
                    return@onClick
                }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener
                        // else if
                        d("MainRe", "Successfully created user with uid: ${it.result?.user?.uid}")
                    }
            }
        }
    }*/


}
