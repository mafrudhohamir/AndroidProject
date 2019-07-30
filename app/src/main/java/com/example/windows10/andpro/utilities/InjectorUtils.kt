package com.example.windows10.andpro.utilities

import com.example.windows10.andpro.data.FakeDatabase
import com.example.windows10.andpro.data.QuoteRepository
import com.example.windows10.andpro.ui.quotes.QuotesViewModelFactory


object InjectorUtils {

   // ini akan dipanggil dari QuotesActivity
    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        // ViewModelFactory membutuhkan sebuah repository, yang pada gilirannya membutuhkan DAO dari database
       //semua pohon dependency dibangun disni, dalam satu tempat
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}