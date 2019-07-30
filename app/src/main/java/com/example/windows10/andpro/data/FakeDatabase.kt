package com.example.windows10.andpro.data

//Konstruktor utama private tidak dapat diakses dari kelas lain
class FakeDatabase private constructor() {

    // Semua DAO (Data Access Object) ada di sini.
    var quoteDao = FakeQuoteDao()
        private set

    companion object {
        // @Volatile - Menulis ke properti ini langusng terlihat oleh thread yang lain
        @Volatile private var instance: FakeDatabase? = null

        //Satu-satunya cara untuk mendapatkan objek FakeDatabase
        fun getInstance() =
        // Sudah instantiated? - return instance
        // Jika tidak instantiate dengan cara yang aman
            instance ?: synchronized(this) {
                // Jika itu masih tidak dipakai, akhirnya membuat sebuah objek
                // juga atur properti "instance" menjadi yang saat ini dibuat
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}