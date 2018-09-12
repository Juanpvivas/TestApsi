package com.juanvivas.testapsi.utils

object Constants {
    object Url {

        const val URL_BASE: String = "https://www.liverpool.com.mx"
        const val TIENDA = "/tienda"
    }


    object OAuth {
        object Keys {
            const val CRITERIO: String = "s"
            const val KEY = "d3106047a194921c01969dfdec083925"
            const val VALUE_KEY: String = "json"
        }
    }

    object HTTPMethod {
        const val POST: String = "post"
        const val GET: String = "Get"
        const val TIMEOUT: Long = 20
    }

    object HttpCodes {
        const val OK: Int = 200
    }

    object ServiceTags {
        const val SEARCH: Int = 0
    }

    object NameAttrib {
        const val PRICE = "sortPrice"
        const val NAME = "product.displayName"
        const val IMAGE = "sku.thumbnailImage"
        const val PATH = "recordState"
    }
}