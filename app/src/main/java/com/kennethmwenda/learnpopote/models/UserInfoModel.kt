package com.kennethmwenda.learnpopote.models

class UserInfoModel(val key:String, val userName:String, val userPhone:String, val userEmail:String) {
    constructor():this("","","","")
}