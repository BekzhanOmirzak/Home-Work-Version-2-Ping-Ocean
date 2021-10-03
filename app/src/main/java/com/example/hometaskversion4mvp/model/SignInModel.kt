package com.example.hometaskversion4mvp.model

import android.util.Log
import com.example.hometaskversion4mvp.contract.SignInContract
import com.example.hometaskversion4mvp.entity.User
import com.example.hometaskversion4mvp.remote.RingAppServiceProvider
import com.example.hometaskversion4mvp.util.TempStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignInModel {

    private val ringAppService = RingAppServiceProvider.provideRingAppService();

    private val TAG = "SignInModel"

    fun signInEmailAndPassword(
        email: String,
        password: String,
        loginFinishedListener: SignInContract.onLoginFinishedListener
    ) {

        if (email.isEmpty()) {
            loginFinishedListener.onEmailError()
            return;
        } else if (password.isEmpty()) {
            loginFinishedListener.onPasswordError();
            return;
        }

        CoroutineScope(Dispatchers.IO).launch {

            val user = User(email, password);
            val response = ringAppService.authenticate(user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body()!!.error.isNotEmpty()) {
                        Log.e(TAG, "signInEmailAndPassword: error: }")
                        loginFinishedListener.onFailure("Логин или пароль неправильно")
                    } else if (response.body()!!.token.isNotEmpty()) {
                        TempStorage.token = response.body()!!.token;
                        Log.e(TAG, "signInEmailAndPassword: ${TempStorage.token}")
                        loginFinishedListener.onSuccess();
                    }
                } else {
                    loginFinishedListener.onFailure("Не удалось войти в систему ${response.code()}");
                }
            }
        }


    }


}