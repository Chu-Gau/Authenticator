package com.aurora.authenticator

import java.util.Objects


object Locker {
    val LOCK_AUTH = AuthLock()

    fun wait(lock: Object) {
        synchronized(lock) {
            lock.wait(300000)
        }
    }

    fun notifyAll(lock: Object) {
        synchronized(lock) {
            lock.notifyAll()
        }
    }
}

class AuthLock (
    var email: String = "",
    var authToken: String = "",
    var AASToken: String = "",
        ): Object()