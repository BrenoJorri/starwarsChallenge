package com.trivago.domain.search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.math.RoundingMode

data class SearchDomain(
    val results: List<CharacterDomain>
) {
    fun isValid(): Boolean = results.isEmpty().not()
}

@Parcelize
data class CharacterDomain(
    val name: String,
    val height: String,
    val birthYear: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>
) : Parcelable {
    val heightInches: String?
        get() = try {
            BigDecimal(height.toDouble() * 0.393701)
                .setScale(1, RoundingMode.HALF_EVEN)
                .toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

}
