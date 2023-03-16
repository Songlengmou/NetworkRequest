package com.anningtex.networkrequestlivedata.api

import com.anningtex.networkrequestlivedata.MainApplication
import com.anningtex.networkrequestlivedata.fragment.two.entity.CountryEntity
import com.anningtex.networkrequestlivedata.fragment.two.entity.LoginUrlEntity
import com.anningtex.networkrequestlivedata.utils.SPUtils
import java.util.ArrayList

/**
 * @Author Song
 * @Date：2023-03-14
 */
internal object AppUrlManger {

    internal object indexChoose {
        private const val REQUEST_URL = "request_url"
        private var PARAM_INDEX = "index"

        fun setIndex(position: Int) {
            MainApplication.context?.let {
                SPUtils.put(
                    it, PARAM_INDEX, position
                )
            }
        }

        fun getIndex(): Any? {
            return SPUtils[MainApplication.context, PARAM_INDEX, 0]
        }

        fun setUrl(url: String?) {
            MainApplication.context?.let {
                if (url != null) {
                    SPUtils.put(it, REQUEST_URL, url)
                }
            }
        }
    }

    var urlEntities: MutableList<LoginUrlEntity> = ArrayList()

    init {
        urlEntities.add(LoginUrlEntity("test1", "http://192.168.0.155:8081/"))
        urlEntities.add(LoginUrlEntity("test2", "http://192.168.0.155:8082/"))
        urlEntities.add(LoginUrlEntity("test3", "http://192.168.0.155:8083/"))
        urlEntities.add(LoginUrlEntity("test4", "http://192.168.0.155:8084/"))
        urlEntities.add(LoginUrlEntity("test5", "http://192.168.0.155:8085/"))
        urlEntities.add(LoginUrlEntity("test6", "http://192.168.0.155:8086/"))
    }

    val countryEntity: MutableList<CountryEntity> = ArrayList()

    init {
        countryEntity.add(CountryEntity("Kenya", ApiConstants.KENYA_COUNTRY_ID))
        countryEntity.add(CountryEntity("Uganda", "2"))
        countryEntity.add(CountryEntity("Tanzania", "3"))
        countryEntity.add(CountryEntity("Congo", ApiConstants.CONGO_COUNTRY_ID))
        countryEntity.add(CountryEntity("Rwanda", "7"))
        countryEntity.add(CountryEntity("Mozambique", "8"))
        countryEntity.add(CountryEntity("Zambia", "9"))
        countryEntity.add(CountryEntity("Ivory Coast", "10"))
        countryEntity.add(CountryEntity("Ghana", "13"))
        countryEntity.add(CountryEntity("Malawi", "14"))
    }
}