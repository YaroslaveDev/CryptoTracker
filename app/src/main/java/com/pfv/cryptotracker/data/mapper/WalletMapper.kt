package com.pfv.cryptotracker.data.mapper

import com.pfv.cryptotracker.data.dto.CurrentBitcoinStateDto
import com.pfv.cryptotracker.data.dvo.CryptoCurrenciesDvo
import com.pfv.cryptotracker.data.dvo.CryptoCurrencyDvo
import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
import com.pfv.cryptotracker.ui.ext.isNotNull
import com.pfv.cryptotracker.ui.ext.isNull
import java.util.Calendar
import javax.inject.Inject

class WalletMapper @Inject constructor(){

    fun bitcoinStateDtoToDvo(data: CurrentBitcoinStateDto?): CurrentBitcoinStateDvo {

        return CurrentBitcoinStateDvo(
            cryptoCurrenciesDvo = CryptoCurrenciesDvo(
                usd = CryptoCurrencyDvo(
                    code = data?.bitcoinCurrencies?.usd?.code.orEmpty(),
                    rate = data?.bitcoinCurrencies?.usd?.rate.orEmpty(),
                    rateFloat = data?.bitcoinCurrencies?.usd?.rateFloat ?: 0f,
                )
            ),
            updatedAt = Calendar.getInstance().time
        )
    }

    fun bitcoinStateDboToDvo(data: BitcoinStateEntity?): CurrentBitcoinStateDvo? {

        return if (data.isNotNull())
            CurrentBitcoinStateDvo(
                cryptoCurrenciesDvo = CryptoCurrenciesDvo(
                    usd = CryptoCurrencyDvo(
                        code = data!!.code,
                        rate = data.rate,
                        rateFloat = data.rateFloat,
                    )
                ),
                updatedAt = Calendar.getInstance().time
            )
        else null
    }

    fun bitcoinStateDvoToDbo(data: CurrentBitcoinStateDvo): BitcoinStateEntity {

        return BitcoinStateEntity(
            code = data.cryptoCurrenciesDvo.usd.code,
            rate = data.cryptoCurrenciesDvo.usd.rate,
            rateFloat = data.cryptoCurrenciesDvo.usd.rateFloat,
            updatedAt = Calendar.getInstance().time
        )
    }


}