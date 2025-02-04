package com.kamel.rafiq_al_iman.data.source.remote.dto

data class PrayerYearlyTimesResponseDto(
    val code: Int? = null,
    val data: YearlyData? = null,
    val status: String? = null
) {
    data class YearlyData(
        val `1`: List<MonthTimes?>? = null,
        val `10`: List<MonthTimes?>? = null,
        val `11`: List<MonthTimes?>? = null,
        val `12`: List<MonthTimes?>? = null,
        val `2`: List<MonthTimes?>? = null,
        val `3`: List<MonthTimes?>? = null,
        val `4`: List<MonthTimes?>? = null,
        val `5`: List<MonthTimes?>? = null,
        val `6`: List<MonthTimes?>? = null,
        val `7`: List<MonthTimes?>? = null,
        val `8`: List<MonthTimes?>? = null,
        val `9`: List<MonthTimes?>? = null
    ) {
        data class MonthTimes(
            val date: Date? = null,
            val meta: Meta? = null,
            val timings: Timings? = null
        ) {
            data class Date(
                val gregorian: Gregorian? = null,
                val hijri: Hijri? = null,
                val readable: String? = null,
                val timestamp: String? = null
            ) {
                data class Gregorian(
                    val date: String? = null,
                    val day: String? = null,
                    val designation: Designation? = null,
                    val format: String? = null,
                    val lunarSighting: Boolean? = null,
                    val month: Month? = null,
                    val weekday: Weekday? = null,
                    val year: String? = null
                ) {
                    data class Designation(
                        val abbreviated: String? = null,
                        val expanded: String? = null
                    )

                    data class Month(
                        val en: String? = null,
                        val number: Int? = null
                    )

                    data class Weekday(
                        val en: String? = null
                    )
                }

                data class Hijri(
                    val date: String? = null,
                    val day: String? = null,
                    val designation: Designation? = null,
                    val format: String? = null,
                    val holidays: List<String?>? = null,
                    val method: String? = null,
                    val month: Month? = null,
                    val weekday: Weekday? = null,
                    val year: String? = null
                ) {
                    data class Designation(
                        val abbreviated: String? = null,
                        val expanded: String? = null
                    )

                    data class Month(
                        val ar: String? = null,
                        val days: Int? = null,
                        val en: String? = null,
                        val number: Int? = null
                    )

                    data class Weekday(
                        val ar: String? = null,
                        val en: String? = null
                    )
                }
            }


            data class Meta(
                val latitude: Double? = null,
                val latitudeAdjustmentMethod: String? = null,
                val longitude: Double? = null,
                val method: Method? = null,
                val midnightMode: String? = null,
                val offset: Offset? = null,
                val school: String? = null,
                val timezone: String? = null
            ) {

                data class Method(
                    val id: Int? = null,
                    val location: Location? = null,
                    val name: String? = null,
                    val params: Params? = null
                ) {

                    data class Location(
                        val latitude: Double? = null,
                        val longitude: Double? = null
                    )


                    data class Params(
                        val Fajr: Double? = null,
                        val Isha: Double? = null
                    )
                }


                data class Offset(
                    val Asr: Double? = null,
                    val Dhuhr: Double? = null,
                    val Fajr: Double? = null,
                    val Imsak: Double? = null,
                    val Isha: Double? = null,
                    val Maghrib: Double? = null,
                    val Midnight: Double? = null,
                    val Sunrise: Double? = null,
                    val Sunset: Double? = null
                )
            }


            data class Timings(
                val Asr: String? = null,
                val Dhuhr: String? = null,
                val Fajr: String? = null,
                val Firstthird: String? = null,
                val Imsak: String? = null,
                val Isha: String? = null,
                val Lastthird: String? = null,
                val Maghrib: String? = null,
                val Midnight: String? = null,
                val Sunrise: String? = null,
                val Sunset: String? = null
            )
        }
    }
}