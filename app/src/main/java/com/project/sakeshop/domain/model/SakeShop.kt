package com.project.sakeshop.domain.model

data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    val googleMapsLink: String,
    val website: String
)