package com.behzad.sugarLogook.features.bloodGlucose.data.units

import androidx.annotation.StringRes
import androidx.room.Ignore
import com.behzad.sugarLogook.R

enum class GlucoseUnit(
    @Ignore @StringRes val unitTextRes: Int,
    @Ignore @StringRes val unitValueTextFormatRes: Int
) {
    MgperdL(R.string.unit_mg_per_dl, R.string.unit_value_mg_per_dl),
    MmolPerL(R.string.unit_mmol_per_L, R.string.unit_value_mmol_per_L);
    companion object {
        val DEFAULT  = MgperdL
    }
}