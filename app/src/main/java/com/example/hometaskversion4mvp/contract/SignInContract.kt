package com.example.hometaskversion4mvp.contract

interface SignInContract {


    interface SignInView {

        fun showProgressBar();
        fun hideProgressBar();
        fun setErrorEmail();
        fun setErrorPassword();
        fun navigateToProfile();
        fun onFailure(message: String);
    }


    interface SignInPresenter {

        fun signIn(
            email: String,
            password: String
        )

        fun onDestroy();

    }

    interface onLoginFinishedListener {
        fun onEmailError();
        fun onPasswordError();
        fun onSuccess();
        fun onFailure(message: String);
    }


}