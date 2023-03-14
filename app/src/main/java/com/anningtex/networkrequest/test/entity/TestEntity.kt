package com.anningtex.networkrequest.test.entity

/**
 * @Author Song
 * @Date：2023-03-09
 */
data class TestEntity(
    val code: Int,
    val data: List<Data>,
    val msg: String
) {
    override fun toString(): String {
        return "TestDataBean(code=$code, data=$data, msg='$msg')"
    }
}

data class Data(
    val BLNo: String,
    val ContainerList: List<Container>,
    val ContainerNo: String,
    val InDate: String
) {
    override fun toString(): String {
        return "Data(BLNo='$BLNo', ContainerList=$ContainerList, ContainerNo='$ContainerNo', InDate='$InDate')"
    }
}

data class Container(
    val BGLID: String,
    val BLNo: String,
    val ContainerNo: String,
    val ID: String,
    val InDate: String,
    val MetersPerBale: String,
    val MetersPerBaleUnitID: String,
    val OrderNo: String,
    val QBales: Int,
    val ToCountryName: String,
    val UnitName: String,
    val UnitNameEN: String,
    val VolumeUnit: String,
    val WeightUnit: String
) {
    override fun toString(): String {
        return "Container(BGLID='$BGLID', BLNo='$BLNo', ContainerNo='$ContainerNo', ID='$ID', InDate='$InDate', MetersPerBale='$MetersPerBale', MetersPerBaleUnitID='$MetersPerBaleUnitID', OrderNo='$OrderNo', QBales=$QBales, ToCountryName='$ToCountryName', UnitName='$UnitName', UnitNameEN='$UnitNameEN', VolumeUnit='$VolumeUnit', WeightUnit='$WeightUnit')"
    }
}