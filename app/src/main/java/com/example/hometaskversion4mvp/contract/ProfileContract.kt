package com.example.hometaskversion4mvp.contract

import com.example.hometaskversion4mvp.entity.PersonalProfileInf

interface ProfileContract {


    interface ProfileView {

        fun hideProgressBar();
        fun failure(message: String)
        fun success(personalProfileInf: PersonalProfileInf)
        fun signOut();

    }

    interface ProfilePresenter {

        fun onDestroy();
        fun requestStarted();

    }

    interface ProfileRequestFinished {

        fun onFailure(message:String);

        fun onSuccess(personalProfileInf: PersonalProfileInf);

    }


}