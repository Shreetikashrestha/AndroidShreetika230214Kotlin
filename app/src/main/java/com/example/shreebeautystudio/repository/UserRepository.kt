package com.example.shreebeautystudio.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.credentials.GetPasswordOption
import com.example.shreebeautystudio.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
//    fun login(email: String,password:String, callback:(Boolean,String)-> Unit )
//    {
//        "success":true
//        "message":"login succesful"
//    }
//

    //response ma k  dini tesko datatype boolean  ma halne
    fun login(email:String,password:String,
              callback:(Boolean,String)->Unit)


//    {"success" : true
//    "message" : "register successfull"
////    "userId" : "1234"}




    fun signup(email:String,password:String,
               callback: (Boolean, String,String) -> Unit)


    fun addUserToDatabase(userId: String, userModel: UserModel,
                          callback: (Boolean, String) -> Unit)
    fun forgetPassword(email:String,
                       callback: (Boolean, String) -> Unit)



    fun getCurrentUser() : FirebaseUser?



}