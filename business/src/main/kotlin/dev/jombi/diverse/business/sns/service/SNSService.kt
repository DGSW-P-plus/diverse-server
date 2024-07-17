package dev.jombi.diverse.business.sns.service

import dev.jombi.diverse.business.sns.dto.SNSDto

interface SNSService {
    fun getMySNSList(): List<SNSDto>

    fun addSNS(sns: SNSDto)

    fun removeSNS(sns: SNSDto)
}
