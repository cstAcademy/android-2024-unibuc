package com.cst.cstacademyunibuc.data.tasks

import android.os.AsyncTask
import com.cst.cstacademyunibuc.ApplicationController
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

class InsertProductTask(val onSuccess: () -> Unit) : AsyncTask<ProductAPIModel, Unit, Unit>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: ProductAPIModel) {
        params.getOrNull(0)?.let { product ->
            ApplicationController
                .instance?.appDatabase?.productsDao?.insertProduct(product)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Unit) {
        super.onPostExecute(result)

        onSuccess.invoke()
    }
}