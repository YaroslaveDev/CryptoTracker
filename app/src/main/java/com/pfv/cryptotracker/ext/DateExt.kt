package com.pfv.cryptotracker.ext

import java.util.Calendar
import java.util.Date

fun Date.isDiffMoreThatHour() =
    (Calendar.getInstance().time.time - this.time).div(1000) > 1