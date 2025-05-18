package com.project.sakeshop.data.local.model

import com.project.sakeshop.domain.model.SakeShop
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SakeShopDTO(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "picture") val picture: String?,
    @Json(name = "rating") val rating: Double,
    @Json(name = "address") val address: String,
    @Json(name = "coordinates") val coordinates: List<Double>,
    @Json(name = "google_maps_link") val googleMapsLink: String,
    @Json(name = "website") val website: String
) {
    fun toDomain() = SakeShop(
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        coordinates = coordinates,
        googleMapsLink = googleMapsLink,
        website = website
    )

    companion object {
        fun fromDomain(sakeShop: SakeShop) = SakeShopDTO(
            name = sakeShop.name,
            description = sakeShop.description,
            picture = sakeShop.picture,
            rating = sakeShop.rating,
            address = sakeShop.address,
            coordinates = sakeShop.coordinates,
            googleMapsLink = sakeShop.googleMapsLink,
            website = sakeShop.website
        )
    }
}