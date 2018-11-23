package com.tlgbltcn.app.weather.utils.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.annotation.NonNull
import androidx.annotation.Nullable

import java.lang.reflect.InvocationTargetException

/**
 * <pre>
 * author: \__\/         \__\/         \__\/
 * blog  : http://blankj.com
 * time  : 16/12/08
 * desc  : utils about initialization
</pre> *
 */
class Utils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }


    class ContentProvider4SubUtil : ContentProvider() {

        override fun onCreate(): Boolean {
            Utils.init(context)
            return true
        }

        @Nullable
        override fun query(@NonNull uri: Uri, @Nullable projection: Array<String>?, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?, @Nullable sortOrder: String?): Cursor? {
            return null
        }

        @Nullable
        override fun getType(@NonNull uri: Uri): String? {
            return null
        }

        @Nullable
        override fun insert(@NonNull uri: Uri, @Nullable values: ContentValues?): Uri? {
            return null
        }

        override fun delete(@NonNull uri: Uri, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?): Int {
            return 0
        }

        override fun update(@NonNull uri: Uri, @Nullable values: ContentValues?, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?): Int {
            return 0
        }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var sApplication: Application? = null

        /**
         * Init utils.
         *
         * Init it in the class of Application.
         *
         * @param context context
         */
        fun init(context: Context?) {
            if (context == null) {
                init(applicationByReflect)
                return
            }
            init(context.applicationContext as Application)
        }

        /**
         * Init utils.
         *
         * Init it in the class of Application.
         *
         * @param app application
         */
        fun init(app: Application?) {
            if (sApplication == null) {
                if (app == null) {
                    Utils.sApplication = applicationByReflect
                } else {
                    Utils.sApplication = app
                }
            }
        }

        /**
         * Return the context of Application object.
         *
         * @return the context of Application object
         */
        val app: Application?
            get() = if (sApplication != null) sApplication else applicationByReflect

        private val applicationByReflect: Application?
            get() {
                try {
                    @SuppressLint("PrivateApi")
                    val activityThread = Class.forName("android.app.ActivityThread")
                    val at = activityThread.getMethod("currentActivityThread").invoke(null)
                    val app = activityThread.getMethod("getApplication").invoke(at)
                            ?: throw NullPointerException("u should init first")
                    init(app as Application)
                    return sApplication
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }

                throw NullPointerException("u should init first")
            }
    }
}