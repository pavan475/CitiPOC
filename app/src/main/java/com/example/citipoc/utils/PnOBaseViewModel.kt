package com.example.citipoc.utils

import android.os.Parcel
import androidx.annotation.IntDef

class PnOBaseViewModel : BaseViewModel {
    constructor(itemType: Int) : super(itemType) {}
    constructor(itemType: Int, uniqueId: Int) : super(itemType, uniqueId.toLong()) {}

    object Types {
        const val IMAGE_DESC_MODEL = 2001
        const val HEAD_TEXT_MODEL = 2002
        const val IMAGE_VIEW_MODEL = 2003
        const val CAROUSEL_VIEW_MODEL = 2004
        const val PAGE_TITLE_MODEL = 2005
        const val VERTICAL_DECK_PLAN_MODEL = 2006
        const val NOTIFICATION_MODEL = 2007
        const val TEXT_DRAWABLE_MODEL = 2008
        const val SWITCH_VIEW_MODEL = 2009
        const val DECK_PLAN_MODEL_ITEM = 2010

        @IntDef(*[IMAGE_DESC_MODEL, HEAD_TEXT_MODEL, NOTIFICATION_MODEL, IMAGE_VIEW_MODEL, CAROUSEL_VIEW_MODEL, PAGE_TITLE_MODEL, VERTICAL_DECK_PLAN_MODEL, DECK_PLAN_MODEL_ITEM])
        annotation class Type
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
    }

    protected constructor(`in`: Parcel?) : super(`in`!!) {}
}
