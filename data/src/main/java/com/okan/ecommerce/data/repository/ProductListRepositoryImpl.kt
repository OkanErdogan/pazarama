package com.okan.ecommerce.data.repository

import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.data.R
import com.okan.ecommerce.domain.models.ProductItem
import com.okan.ecommerce.domain.models.ProductListRequest
import com.okan.ecommerce.domain.models.ProductListResponse
import com.okan.ecommerce.domain.repository.ProductListRepository

class ProductListRepositoryImpl : ProductListRepository {
    override suspend fun getProductList(productListRequest: ProductListRequest): ProductListResponse {
        val passKey =
            BaseApplication.getAppInstance().applicationContext.getString(R.string.passkey)
        val isSuccess = passKey.equals(productListRequest.passKey)
        var message = if (isSuccess) "Başarılı" else "Başarısız"
        return ProductListResponse(isSuccess, message, generateProductListDemo(isSuccess))
    }

    private fun generateProductListDemo(isSuccess: Boolean): ArrayList<ProductItem> {
        var productItemList = ArrayList<ProductItem>()

        if (isSuccess) {


            productItemList.add(
                ProductItem(
                    "Milk Shake Whipped Cream Saç Bakım Köpüğü",
                    1,
                    168.0,
                    "TL",
                    R.drawable.img1
                )
            )
            productItemList.add(
                ProductItem(
                    "Atoderm Intensive Eye",
                    2,
                    262.0,
                    "TL",
                    R.drawable.img2
                )
            )
            productItemList.add(
                ProductItem(
                    "Easyvit Multi Omega 3",
                    3,
                    234.9,
                    "TL",
                    R.drawable.img3
                )
            )
            productItemList.add(
                ProductItem(
                    "Bioxin Forte Şampuan 300 ml",
                    4,
                    139.0,
                    "TL",
                    R.drawable.img4
                )
            )
            productItemList.add(
                ProductItem(
                    "Gillette Blue3 Comfort Kullan At Traş Bıçağı",
                    5,
                    124.9,
                    "TL",
                    R.drawable.img5
                )
            )
            productItemList.add(ProductItem("Nivea STICK BY FRESH", 6, 64.9, "TL", R.drawable.img6))
            productItemList.add(
                ProductItem(
                    "Nivea Invisible Deodorant",
                    7,
                    52.43,
                    "TL",
                    R.drawable.img7
                )
            )
            productItemList.add(
                ProductItem(
                    "Biotherm Creme  Nacree Oligo Thermale",
                    8,
                    254.15,
                    "TL",
                    R.drawable.img8
                )
            )
            productItemList.add(
                ProductItem(
                    "Walkway Belz-F Pudra İçi Kürklü Kız Çocuk Kar Botu",
                    9,
                    339.0,
                    "TL",
                    R.drawable.img9
                )
            )
            productItemList.add(ProductItem("Siyah Kadın Çizme", 10, 629.9, "TL", R.drawable.img10))
            productItemList.add(
                ProductItem(
                    "Tommy Erkek Sneaker Ayakkabı Siyah",
                    11,
                    699.99,
                    "TL",
                    R.drawable.img11
                )
            )
            productItemList.add(
                ProductItem(
                    "Nivea Luminous 630 Leke Karşıtı Serum",
                    12,
                    247.9,
                    "TL",
                    R.drawable.img12
                )
            )
            productItemList.add(
                ProductItem(
                    "Oral B Cross Action Şarjlı Diş Fırçası",
                    3,
                    399.5,
                    "TL",
                    R.drawable.img13
                )
            )
            productItemList.add(
                ProductItem(
                    "Gül Mayası Suyu",
                    3,
                    99.99,
                    "TL",
                    R.drawable.img14))
        }
        return productItemList
    }
}