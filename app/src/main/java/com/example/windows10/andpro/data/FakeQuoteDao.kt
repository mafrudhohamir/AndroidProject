package com.example.windows10.andpro.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDao {
    // tabel fakedatabase
    private val quoteList = mutableListOf<Quote>()
    //MutableLiveData berasal dari Library Components Architecture
    // LiveData dapat diamati perubahannya
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        // Segera menghubungkan quoteList yang sekarang kosong
        // ke MutableLiveData yang bisa observasi
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        // Setelah menambahkan kutipan ke "database",
        // perbarui nilai MutableLiveData
        // yang akan memberi tahu observasi aktif
        quotes.value = quoteList
    }
    // Casting MutableLiveData ke LiveData karena nilainya
    // seharusnya tidak diubah dari kelas lain

    fun getQuotes() = quotes as LiveData<List<Quote>>
}