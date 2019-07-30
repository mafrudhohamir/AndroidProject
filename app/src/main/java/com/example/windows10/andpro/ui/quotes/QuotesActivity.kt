package com.example.windows10.andpro.ui.quotes

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.windows10.andpro.R
import com.example.windows10.andpro.data.Quote
import com.example.windows10.andpro.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity() : AppCompatActivity(), Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }

    private fun initializeUi() {
        //Mendapatkan QuotesViewModelFactory dengan semua struktur dependenci
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // menggunakan kelas ViewModelProviders untuk membuat / mendapatkan QuotesViewModel yang sudah dibuat
        // untuk tampilan(activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observasi LiveData dari QuotesViewModel yang pada gilirannya nanti diobservasi
        // LiveData dari repository, yang mengobservasi LiveData dari DAO (Data access object)
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        //Ketika tombol diklik, instantiate sebuah kutipan / Quote dan tambahkan ke DB melalui ViewModel
        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuotesActivity> {
        override fun createFromParcel(parcel: Parcel): QuotesActivity {
            return QuotesActivity(parcel)
        }

        override fun newArray(size: Int): Array<QuotesActivity?> {
            return arrayOfNulls(size)
        }
    }

}