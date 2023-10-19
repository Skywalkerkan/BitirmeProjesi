package com.example.bitirmeprojesi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "yemekler")
data class YemeklerRoom(@PrimaryKey(autoGenerate = true)
                         @ColumnInfo(name = "yemek_id_asil") @NotNull var yemek_id_asil :Int,
                         @ColumnInfo(name = "yemek_id") @NotNull var yemek_id:Int,
                         @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi:String,
                         @ColumnInfo(name = "yemek_resim_adi") @NotNull var yemek_resim_adi:String,
                         @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat:Int) : Serializable {
}


//data class Yemekler(var yemek_id: Int,
//                    var yemek_adi: String,
//                    var yemek_resim_adi: String,
//                    var yemek_fiyat: Int): Serializable {
//}