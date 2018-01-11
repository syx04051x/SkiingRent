package com.alphaz.core.service

import java.util.*

/**
 *@Author: c0der
 *@Date: 下午3:46 2018/1/8
 *@Description:
 */
interface LocalizationService {
    fun getMessage(code: String): String
    fun getMessage(code: String, locale: Locale): String
    fun getMessage(code: String, args: Array<String>): String
    fun getMessage(code: String, args: Array<String>?, locale: Locale): String
}