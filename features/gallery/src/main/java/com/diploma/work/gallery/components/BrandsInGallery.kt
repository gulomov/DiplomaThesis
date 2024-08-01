package com.diploma.work.gallery.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.diploma.work.common.componants.BrandImage
import com.diploma.work.design.theme.BrandsInGalleryImageHeightSize
import com.diploma.work.design.theme.BrandsInGalleryImageWidthSize
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.repository.data.BrandsItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BrandsInGallery(
    brandsList: List<BrandsItem>,
    brandClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { brandsList.size })

    HorizontalPager(
        modifier = modifier.padding(top = normal100, start = normal100, end = normal100),
        state = pagerState,
        pageSpacing = small100,
        pageSize = PageSize.Fixed(BrandsInGalleryImageWidthSize),
    ) { page ->
        Card(
            modifier = Modifier
                .width(BrandsInGalleryImageWidthSize),
            shape = RoundedCornerShape(small100),
            onClick = { brandClick.invoke(brandsList[page].brand.toString()) }
        ) {
            BrandImage(
                imageUrl = brandsList[page].imageUrl.toString(),
                modifier = Modifier
                    .width(BrandsInGalleryImageWidthSize)
                    .height(BrandsInGalleryImageHeightSize),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BrandsInGalleryPreview() {
    val brands = listOf(
        BrandsItem(1, "imageUrl", "brand"),
        BrandsItem(1, "imageUrl", "brand"),
        BrandsItem(1, "imageUrl", "brand"),
    )

    DiplomaThesisTheme {
        BrandsInGallery(brandsList = brands, brandClick = {})
    }
}