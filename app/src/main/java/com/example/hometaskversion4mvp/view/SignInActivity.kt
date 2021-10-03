package com.example.hometaskversion4mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hometaskversion4mvp.contract.SignInContract
import com.example.hometaskversion4mvp.databinding.ActivitySignInBinding
import com.example.hometaskversion4mvp.presenter.SignInPresenter

class SignInActivity : AppCompatActivity(), SignInContract.SignInView {

    private lateinit var binding: ActivitySignInBinding;
    private lateinit var signInPresenter: SignInPresenter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding.root);
        signInPresenter = SignInPresenter(this);

        clickSignInButton();

    }

    private fun clickSignInButton() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.inputEmail.editText?.text.toString().trim();
            val password = binding.inputPassword.editText?.text.toString().trim();
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            imm.hideSoftInputFromWindow(it.windowToken, 0);
            signInPresenter.signIn(email, password);
        }
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE;
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE;
    }

    override fun setErrorEmail() {
        binding.inputEmail.editText?.error = "Почта не может быть пустой"
    }

    override fun setErrorPassword() {
        binding.inputPassword.editText?.error = "Пароль не может быть пустой"
    }

    override fun navigateToProfile() {
        Intent(this, ProfileActivity::class.java).also {
            startActivity(it);
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        signInPresenter.onDestroy();
    }


}