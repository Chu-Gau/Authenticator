package com.aurora.authenticator

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.aurora.authenticator.Locker.LOCK_AUTH
import java.lang.IllegalArgumentException

class AuthenticatorContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.aurora.authenticator.provider"

        private const val AUTH = 1

        val matcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "auth", AUTH)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        when (matcher.match(uri)) {
            AUTH -> {
                val intent = Intent(context, CautionActivity::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                context?.startActivity(intent)
                Locker.wait(LOCK_AUTH)

                val cursor = MatrixCursor(arrayOf("email", "auth_token", "AAS_token"))
                cursor.addRow(arrayOf(LOCK_AUTH.email, LOCK_AUTH.authToken, LOCK_AUTH.AASToken))
                return cursor
            }
            else -> {
                throw IllegalArgumentException("Unknown URI")
            }
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}