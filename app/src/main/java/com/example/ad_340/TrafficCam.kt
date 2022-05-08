package com.example.ad_340

class TrafficCam (val description: String, private val image: String, var type: String, var coords: DoubleArray ) {
    val des = this.description;

    val ima = this.image

    val typ = this.type

    val coo = this.coords

    private val baseUrl: Map<String, String> = mapOf(
        "sdot" to "http://www.seattle.gov/trafficcams/images/",
        "wsdot" to "http://images.wsdot.wa.gov/nw/"
    )

    fun imageUrl() : String {
        return baseUrl[this.type] + this.image
    }
}