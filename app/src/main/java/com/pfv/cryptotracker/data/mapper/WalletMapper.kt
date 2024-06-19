package com.pfv.cryptotracker.data.mapper

import com.pfv.cryptotracker.data.dto.CurrentBitcoinStateDto
import com.pfv.cryptotracker.data.dvo.CryptoCurrenciesDvo
import com.pfv.cryptotracker.data.dvo.CryptoCurrencyDvo
import com.pfv.cryptotracker.data.dvo.CurrentBitcoinStateDvo
import com.pfv.cryptotracker.data.local.entity.BitcoinStateEntity
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
            )
        )
    }

    fun bitcoinStateDboToDvo(data: BitcoinStateEntity): CurrentBitcoinStateDvo {

        return CurrentBitcoinStateDvo(
            cryptoCurrenciesDvo = CryptoCurrenciesDvo(
                usd = CryptoCurrencyDvo(
                    code = data.code,
                    rate = data.rate,
                    rateFloat = data.rateFloat,
                )
            )
        )
    }

    fun bitcoinStateDvoToDbo(data: CurrentBitcoinStateDvo): BitcoinStateEntity {

        return BitcoinStateEntity(
            code = data.cryptoCurrenciesDvo.usd.code,
            rate = data.cryptoCurrenciesDvo.usd.rate,
            rateFloat = data.cryptoCurrenciesDvo.usd.rateFloat,
        )
    }


}