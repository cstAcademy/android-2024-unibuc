package com.cst.cstacademyunibuc.data.tasks

import android.os.AsyncTask
import com.cst.cstacademyunibuc.ApplicationController
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.UserModel

class InsertUserTask(val onSuccess: () -> Unit) : AsyncTask<UserModel, Unit, Unit>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: UserModel) {
        params.getOrNull(0)?.let { user ->
            ApplicationController
                .instance?.appDatabase?.userDao?.insertRole(RoleModel(user.role))

            ApplicationController
                .instance?.appDatabase?.userDao?.insertUser(user)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Unit) {
        super.onPostExecute(result)

        onSuccess.invoke()
    }
}