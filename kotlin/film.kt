import java.util.Scanner

data class Film(val id: Int, var judul: String, var status: String)

class Catatan {
    var ket: String = ""
        get() {
            return if (field.isEmpty()) "Tidak ada catatan" else "Catatan: " + field
        }
        set(value) {
            field = if (value.isEmpty()) "Kosong" else value
        }
}

val data = ArrayList<Film>()
val input = Scanner(System.`in`)
var id = 1

fun main() {
    val menu = "\nMenu"
    var jalan = true

    while (jalan) {
        println(menu)
        println("1. Tambah")
        println("2. List")
        println("3. Edit")
        println("4. Hapus")
        println("5. Show")
        println("0. Keluar")
        print("Pilih Nomor: ")

        val p = input.nextInt()
        input.nextLine()

        if (p == 1) tambah()
        else if (p == 2) list()
        else if (p == 3) edit()
        else if (p == 4) hapus()
        else if (p == 5) show()
        else if (p == 0) {
            jalan = false
            println("Program Selesai")
        } else {
            println("Pilihan Nomor Tidak Ada!")
        }
    }
}

fun tambah() {
    print("Judul Film: ")
    var j = input.nextLine()
    if (j.isEmpty()) j = "Tidak ada"

    print("Status: ")
    var s = input.nextLine()
    if (s.isEmpty()) s = "Belum Nonton"

    data.add(Film(id, j, s))
    id++
    println("Judul Film Berhasil Ditambahkan!")
}

fun list() {
    if (data.isEmpty()) {
        println("Data Kosong")
    } else {
        for (f in data) {
            println("${f.judul} = ${f.status}")
        }
    }
}

fun edit() {
    print("Masukkan Judul Film yang Ingin Diedit: ")
    val cari = input.nextLine()

    var ada = false
    
    for (i in data.indices) {
        val f = data[i]
        if (f.judul == cari) {
            print("Judul Baru: ")
            var j = input.nextLine()
            if (j.isEmpty()) j = f.judul
            f.judul = j

            println("Judul Film Berhasil Diedit!")
            ada = true
            break
        }
    }

    if (!ada) {
        println("Judul Film Tidak Ditemukan!")
    }
}

fun hapus() {
    print("Masukkan Judul Film yang Ingin Dihapus: ")
    val cari = input.nextLine()

    var ada = false
    val iterator = data.iterator()
    while (iterator.hasNext()) {
        val f = iterator.next()
        if (f.judul == cari) {
            iterator.remove()
            println("Judul Film Berhasil Dihapus!")
            ada = true
        }
    }

    if (!ada) {
        println("Judul Film Tidak Ditemukan!")
    }
}

fun show() {
    print("Masukkan Judul Film: ")
    val cari = input.nextLine()

    var ada = false

    for (f in data) {
        if (f.judul == cari) {
            val c = Catatan()
            c.ket = "Data Detail Film"

            println("ID: ${f.id}")
            println("Judul: ${f.judul}")
            println("Status: ${f.status}")
            println(c.ket)

            ada = true
            break
        }
    }

    if (!ada) {
        println("Judul Film Tidak Ditemukan!")
    }
}