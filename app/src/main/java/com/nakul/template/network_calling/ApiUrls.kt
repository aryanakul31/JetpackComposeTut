package com.nakul.template.network_calling

object ApiUrls {
    const val LOGIN = "user/login"

    /**Settings*/
    const val CMS = "user/cms"
    const val NOTIFICATION = "user/notification"
    const val FEEDBACK = "user/feedback"
    const val CONTACT = "user/contact"
    const val DELETE = "user/deleteAccount"
    const val LOG_OUT = "user/logout"

    /**Forgot Password Flow*/
    const val FORGOT_PASS = "user/forget/password"
    const val VERIFY_OTP = "user/forget/verify"
    const val RESET_PASSWORD = "user/reset/password"

    /**Profile flow*/
    const val CHANGE_PASSWORD = "user/change/password"
    const val PROFILE = "user/profile"
    const val OPTIONS = "user/options"

    /**POLLS*/
    const val POLLS = "user/polls"
    const val POLL =  "user/poll"
    const val POLL_SUGGEST =  "user/poll/suggest"

    /**FORUMS*/
    const val FORUMS = "user/forums"
    const val FORUM = "user/forum"
}