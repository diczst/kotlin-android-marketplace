package com.neonusa.marketplace.util

fun String?.defaultError(): String {
    return this ?: Constants.DEFALUT_ERROR
}