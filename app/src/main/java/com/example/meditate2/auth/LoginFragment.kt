package com.example.meditate2.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.meditate2.PhotosActivity
import com.example.meditate2.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.et_email
import kotlinx.android.synthetic.main.fragment_login.et_password

class LoginFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        val btn: Button = view.findViewById(R.id.btn_login)

        btn.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    d("MainRe", "Successfully signed in: ${it.result?.user?.uid}")

                }
                .addOnFailureListener {
                    //Toast.makeText(activity, "{$it.message}", Toast.LENGTH_SHORT).show()
                    d("MainRe", "Unsuccessful")
                }

            val intent = Intent(activity, PhotosActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return view
    }
}
