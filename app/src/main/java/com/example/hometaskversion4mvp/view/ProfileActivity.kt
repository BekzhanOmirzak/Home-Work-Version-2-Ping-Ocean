package com.example.hometaskversion4mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.hometaskversion4mvp.contract.ProfileContract
import com.example.hometaskversion4mvp.databinding.ActivityProfileBinding
import com.example.hometaskversion4mvp.entity.PersonalProfileInf
import com.example.hometaskversion4mvp.presenter.ProfilePresenter

class ProfileActivity : AppCompatActivity(), ProfileContract.ProfileView {

    private lateinit var profilePresenter: ProfilePresenter;
    private lateinit var binding: ActivityProfileBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root);
        profilePresenter = ProfilePresenter(this);
        profilePresenter.requestStarted();

        btnSignOut();

    }


    private fun btnSignOut() {
        binding.btnSignOut.setOnClickListener {
            profilePresenter.signOut();
        }
    }


    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE;
        binding.parentProfile.visibility = View.VISIBLE;
    }

    override fun failure(message: String) {
        binding.progressBar.visibility = View.GONE;
        binding.parentProfile.visibility = View.VISIBLE;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun success(personalProfileInf: PersonalProfileInf) {

        binding.inputEmail.editText?.setText(personalProfileInf.email);
        binding.inputCompany.editText?.setText(personalProfileInf.company_name);
        binding.inputName.editText?.setText(personalProfileInf.name);
        binding.inputTimZone.editText?.setText(personalProfileInf.timezone);
        binding.inputPhone.editText?.setText(personalProfileInf.phone);

    }

    override fun signOut() {
        Intent(this, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.onDestroy()
    }


}