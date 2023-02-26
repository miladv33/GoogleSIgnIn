package com.example.googlesignin.newSDK

import GoogleSignInClient
import android.app.Activity
import android.content.Intent
import com.example.googlesignin.listener.ISignInResult

/**
 *  this class will handle the initialization of the SignInRequirements,
 *  starting the intentSender for the result and handling the exception that may occur throughout the process.
 *
 * @constructor
 *
 * @param activity
 * @param googleClientId
 * @param iSignInResult
 */
class GoogleSignIn constructor(
    activity: Activity,
    googleClientId: String,
    iSignInResult: ISignInResult
) {
    private val REQ_ONE_TAP: Int = 100
    private var googleSignInRequest: GoogleSignInRequest = GoogleSignInRequest(googleClientId)
    private var googleSignInClient: GoogleSignInClient =
        GoogleSignInClient(activity, googleSignInRequest, iSignInResult, REQ_ONE_TAP)
    private var googleSignInResult: GoogleSignInResult =
        GoogleSignInResult(iSignInResult, activity, REQ_ONE_TAP)
    private var tokenManager = TokenManager()

    fun signIn() {
        googleSignInClient.signIn()
    }


    fun onActivityResult(data: Intent?) {
        googleSignInResult.parseActivityResult(REQ_ONE_TAP, data)
    }
    fun isTokenExpired(token: String?): Boolean {
        return tokenManager.isTokenExpired(token)
    }

}
