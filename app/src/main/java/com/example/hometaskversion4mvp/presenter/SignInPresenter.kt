package com.example.hometaskversion4mvp.presenter

import com.example.hometaskversion4mvp.contract.SignInContract
import com.example.hometaskversion4mvp.model.SignInModel


class SignInPresenter(
    var signInView: SignInContract.SignInView?,
) : SignInContract.SignInPresenter, SignInContract.onLoginFinishedListener {

    private val signInModel = SignInModel();

    override fun signIn(
        email: String,
        password: String
    ) {
        signInView?.let {
            it.showProgressBar();
            signInModel.signInEmailAndPassword(email, password, this);
        }
    }


    override fun onEmailError() {
        signInView?.apply {
            hideProgressBar();
            setErrorEmail();
        }
    }

    override fun onPasswordError() {
        signInView?.apply {
            hideProgressBar();
            setErrorPassword();
        }
    }

    override fun onSuccess() {
        signInView?.apply {
            hideProgressBar();
            navigateToProfile();
        }
    }

    override fun onFailure(message: String) {
        signInView?.apply {
            hideProgressBar()
            onFailure(message);
        }
    }

    override fun onDestroy() {
        signInView?.let {
            signInView = null;
        }
    }


}