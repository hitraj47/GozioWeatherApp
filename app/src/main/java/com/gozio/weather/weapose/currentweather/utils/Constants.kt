package com.gozio.weather.weapose.currentweather.utils

import com.google.android.gms.maps.model.LatLng

object Constants {
    object DateFormat {
        const val HH_mm = "HH:mm"
        const val EE_MM_dd = "EEEE, MMM dd"
    }

    object Key {
        const val LAT_LNG = "lat_lng"
        const val LAT = "lat"
        const val LNG = "lng"
        const val FROM_ROUTE = "from_route"
        const val ADDRESS_NAME = "address_name"
    }

    object Default {
        val LAT_LNG_DEFAULT = LatLng(33.78591032377107, -84.40964058633683)
    }
}
