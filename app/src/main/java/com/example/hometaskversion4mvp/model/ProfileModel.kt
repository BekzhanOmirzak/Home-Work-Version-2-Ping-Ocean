package com.example.hometaskversion4mvp.model

import com.example.hometaskversion4mvp.contract.ProfileContract
import com.example.hometaskversion4mvp.remote.RingAppServiceProvider
import com.example.hometaskversion4mvp.util.TempStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileModel {

    private val ringAppService = RingAppServiceProvider.provideRingAppService();
    private val TAG = "ProfileModel"

    fun requestStarted(requestFinished: ProfileContract.ProfileRequestFinished) {
        val bearer_token = "Bearer ${TempStorage.token}"
        CoroutineScope(Dispatchers.IO).launch {

            val response = ringAppService.getPersonalProfileInformation(bearer_token);
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body()!!.error.isNotEmpty()) {
                        requestFinished.onFailure("Недействительный токен")
                    } else {
                        requestFinished.onSuccess(response.body()!!);
                    }
                } else {
                    requestFinished.onFailure("Не удалось войти в систему ${response.code()}")
                }
            }
        }
    }


}