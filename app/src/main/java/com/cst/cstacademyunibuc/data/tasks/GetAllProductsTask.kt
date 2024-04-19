package com.cst.cstacademyunibuc.data.tasks

import android.os.AsyncTask
import com.cst.cstacademyunibuc.ApplicationController
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

class GetAllProductsTask(val onSuccess: (List<ProductAPIModel>) -> Unit) :
    AsyncTask<Unit, Unit, List<ProductAPIModel>>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Unit): List<ProductAPIModel> {
        return ApplicationController
            .instance?.appDatabase?.productsDao?.getAllProducts() ?: listOf()
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: List<ProductAPIModel>) {
        super.onPostExecute(result)

        onSuccess.invoke(result)
    }
}