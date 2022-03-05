package com.softaai.upstoxholding.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "avg_price")
    val avgPrice: String,
    @Json(name = "close")
    val close: Int,
    @Json(name = "cnc_used_quantity")
    val cncUsedQuantity: Int,
    @Json(name = "collateral_qty")
    val collateralQty: Int,
    @Json(name = "collateral_type")
    val collateralType: String,
    @Json(name = "collateral_update_qty")
    val collateralUpdateQty: Int,
    @Json(name = "company_name")
    val companyName: String,
    @Json(name = "haircut")
    val haircut: Double,
    @Json(name = "holdings_update_qty")
    val holdingsUpdateQty: Int,
    @Json(name = "isin")
    val isin: String,
    @Json(name = "ltp")
    val ltp: Double,
    @Json(name = "product")
    val product: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "t1_holding_qty")
    val t1HoldingQty: Int,
    @Json(name = "token_bse")
    val tokenBse: String,
    @Json(name = "token_nse")
    val tokenNse: String,
    @Json(name = "withheld_collateral_qty")
    val withheldCollateralQty: Int,
    @Json(name = "withheld_holding_qty")
    val withheldHoldingQty: Int
)