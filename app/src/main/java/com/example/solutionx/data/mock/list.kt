package com.example.solutionx.data.mock

import com.example.solutionx.data.model.Country
import com.example.solutionx.data.model.Currency
import com.example.solutionx.data.model.Filter

val countriesList = listOf(
    Country(
        id = 1,
        name = "Saudi Arabia",
        currency = "SAR",
        code = "sa",
        phone_code = "00966",
        flag = "\ud83c\uddf8\ud83c\udde6",
        isSelected = false
    ),
    Country(
        id = 2,
        name = "Egypt",
        currency = "EGP",
        code = "eg",
        phone_code = "0020",
        flag = "\ud83c\uddea\ud83c\uddec",
        isSelected = false
    ),
    Country(
        id = 3,
        name = "Afghanistan",
        currency = "AFN",
        code = "af",
        phone_code = "0093",
        flag = "\ud83c\udde6\ud83c\uddeb",
        isSelected = false
    ),
    Country(
        id = 4,
        name = "Albania",
        currency = "ALL",
        code = "al",
        phone_code = "00355",
        flag = "\ud83c\udde6\ud83c\uddf1",
        isSelected = false
    ),
    Country(
        id = 5,
        name = "Algeria",
        currency = "DZD",
        code = "dz",
        phone_code = "00213",
        flag = "\ud83c\udde9\ud83c\uddff",
        isSelected = false
    ),
    Country(
        id = 6,
        name = "Andorra",
        currency = "EUR",
        code = "ad",
        phone_code = "00376",
        flag = "\ud83c\udde6\ud83c\udde9",
        isSelected = false
    ),
    Country(
        id = 7,
        name = "Angola",
        currency = "AOA",
        code = "ao",
        phone_code = "00244",
        flag = "\ud83c\udde6\ud83c\uddf4",
        isSelected = false
    ),
    Country(
        id = 8,
        name = "Argentina",
        currency = "ARS",
        code = "ar",
        phone_code = "0054",
        flag = "\ud83c\udde6\ud83c\uddf7",
        isSelected = false
    ),
    Country(
        id = 9,
        name = "Armenia",
        currency = "AMD",
        code = "am",
        phone_code = "00374",
        flag = "\ud83c\udde6\ud83c\uddf2",
        isSelected = false
    )
)


val filterList = listOf(
    Filter(
        id = 1,
        name = "test title 2",
        isSelected = false
    ),
    Filter(
        id = 3,
        name = "\u0639\u0631\u0628\u06492222",
        isSelected = false
    )
)

val currencyList = listOf(
    Currency(
        id = 1,
        name = "American Dollar",
        sign = "$",
        code = "USD",
        isSelected = false
    ),
    Currency(
        id = 2,
        name = "Euro",
        sign = "\u20ac",
        code = "EUR",
        isSelected = false
    ),
    Currency(
        id = 3,
        name = "Egyptian Pound",
        sign = "LE",
        code = "EGP",
        isSelected = false
    ),
    Currency(
        id = 4,
        name = "Saudi Riyal",
        sign = "SAR",
        code = "SAR",
        isSelected = false
    ),
    Currency(
        id = 5,
        name = "Emirati Dirham",
        sign = "AED",
        code = "AED",
        isSelected = false
    ),
    Currency(
        id = 6,
        name = "Kuwaiti Dinar",
        sign = "KWD",
        code = "KWD",
        isSelected = false
    ),
    Currency(
        id = 7,
        name = "Qatari Riyal",
        sign = "QAR",
        code = "QAR",
        isSelected = false
    ),
    Currency(
        id = 8,
        name = "Bahraini Dinar",
        sign = "BHD",
        code = "BHD",
        isSelected = false
    ),
    Currency(
        id = 9,
        name = "Omani Riyal",
        sign = "OMR",
        code = "OMR",
        isSelected = false
    )
)
