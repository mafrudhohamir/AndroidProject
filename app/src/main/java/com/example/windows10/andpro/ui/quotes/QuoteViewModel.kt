package com.example.windows10.andpro.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.windows10.andpro.data.Quote
import com.example.windows10.andpro.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}