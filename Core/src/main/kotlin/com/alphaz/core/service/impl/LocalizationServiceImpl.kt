package com.alphaz.core.service.impl

import com.alphaz.core.service.LocalizationService
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.Resource
import javax.transaction.Transactional

/**
 *@Author: c0der
 *@Date: 下午3:46 2018/1/8
 *@Description:
 */
@Transactional
@Service
open class LocalizationServiceImpl : LocalizationService {


    val logger = LoggerFactory.getLogger(LocalizationServiceImpl::class.java!!)
    @Resource
    lateinit var messageSource: MessageSource

    override fun getMessage(code: String): String {
        return getMessage(code, null, LocaleContextHolder.getLocale())
    }

    override fun getMessage(code: String, locale: Locale): String {
        return getMessage(code, null, locale)
    }

    override fun getMessage(code: String, args: Array<String>): String {
        return getMessage(code, args, LocaleContextHolder.getLocale())
    }

    override fun getMessage(code: String, args: Array<String>?, locale: Locale): String {
        try {
            var message = messageSource.getMessage(code, args, locale);
            return message;
        } catch (ex: Exception) {
            logger.info("未在${LocaleContextHolder.getLocale()}找到${code}")
        }
        return "[$code]";
    }
}