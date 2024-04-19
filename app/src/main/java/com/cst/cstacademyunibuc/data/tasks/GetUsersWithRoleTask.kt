package com.cst.cstacademyunibuc.data.tasks

import android.os.AsyncTask
import com.cst.cstacademyunibuc.ApplicationController
import com.cst.cstacademyunibuc.data.models.user.UsersWithRole
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.user.RoleType

class GetUsersWithRoleTask(val onSuccess: (UsersWithRole?) -> Unit) :
    AsyncTask<Unit, Unit, UsersWithRole?>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Unit): UsersWithRole? {
        return ApplicationController
            .instance?.appDatabase?.userDao?.getUsersWithRole(RoleType.USER_PLEB)
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: UsersWithRole?) {
        super.onPostExecute(result)

        onSuccess.invoke(result)
    }
}