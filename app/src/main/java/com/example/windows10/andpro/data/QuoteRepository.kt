package com.example.windows10.andpro.data

class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao) {


    // Ini seperti perulangan.
    // Bayangkan / gambaran kode yang juga memperbarui dan memeriksa backend.
    fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(quoteDao).also { instance = it }
            }
    }
}