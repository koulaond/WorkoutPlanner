package com.example.workoutplanner.db.dao.async

import android.os.AsyncTask
import com.example.workoutplanner.db.dao.AbstractDao

class InsertAsyncTask<T, D: AbstractDao<T>>(var dao: D) : AsyncTask<T, Void, Void>() {

    override fun doInBackground(vararg params: T): Void? {
        dao.insert(*params)
        return null
    }
}
