package com.anningtex.networkrequestlivedata.fragment.one

import com.google.gson.annotations.SerializedName

data class TestOneEntity(
    @SerializedName("BLNo") val bLNo: String,
    @SerializedName("ContainerList") val containerList: List<Container>,
    @SerializedName("ContainerNo") val containerNo: String,
    @SerializedName("InDate") val inDate: String
) {
    override fun toString(): String {
        return "TestOneEntity(bLNo='$bLNo', containerList=$containerList, containerNo='$containerNo', inDate='$inDate')"
    }
}

data class Container(
    @SerializedName("BGLID") val bGLID: String,
    @SerializedName("BLNo") val bLNo: String,
    @SerializedName("ContainerNo") val containerNo: String,
    @SerializedName("ID") val iD: String,
    @SerializedName("InDate") val inDate: String,
    @SerializedName("MetersPerBale") val metersPerBale: String,
    @SerializedName("MetersPerBaleUnitID") val metersPerBaleUnitID: String,
    @SerializedName("OrderNo") val orderNo: String,
    @SerializedName("QBales") val qBales: Double,
    @SerializedName("ToCountryName") val toCountryName: String,
    @SerializedName("UnitName") val unitName: String,
    @SerializedName("UnitNameEN") val unitNameEN: String,
    @SerializedName("VolumeUnit") val volumeUnit: String,
    @SerializedName("WeightUnit") val weightUnit: String
) {
    override fun toString(): String {
        return "Container(bGLID='$bGLID', bLNo='$bLNo', containerNo='$containerNo', iD='$iD', inDate='$inDate', metersPerBale='$metersPerBale', metersPerBaleUnitID='$metersPerBaleUnitID', orderNo='$orderNo', qBales=$qBales, toCountryName='$toCountryName', unitName='$unitName', unitNameEN='$unitNameEN', volumeUnit='$volumeUnit', weightUnit='$weightUnit')"
    }
}
