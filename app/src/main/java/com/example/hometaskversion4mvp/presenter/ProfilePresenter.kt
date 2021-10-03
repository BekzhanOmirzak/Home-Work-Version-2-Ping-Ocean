package com.example.hometaskversion4mvp.presenter

import com.example.hometaskversion4mvp.contract.ProfileContract
import com.example.hometaskversion4mvp.entity.PersonalProfileInf
import com.example.hometaskversion4mvp.model.ProfileModel
import com.example.hometaskversion4mvp.util.TempStorage


class ProfilePresenter(var profileView: ProfileContract.ProfileView?) :
    ProfileContract.ProfileRequestFinished, ProfileContract.ProfilePresenter {

    private val profileModel = ProfileModel();


    override fun requestStarted() {
        profileModel.requestStarted(this);
    }

    override fun onFailure(message: String) {
        profileView?.apply {
            hideProgressBar();
            failure(message);
        }
    }

    override fun onSuccess(personalProfileInf: PersonalProfileInf) {
        profileView?.apply {
            hideProgressBar()
            success(personalProfileInf);
        }
    }


    override fun onDestroy() {
        profileView?.let {
            profileView = null;
        }
    }

    fun signOut() {
        TempStorage.token = null;
        profileView?.let {
            it.signOut();
        }
    }
    


}