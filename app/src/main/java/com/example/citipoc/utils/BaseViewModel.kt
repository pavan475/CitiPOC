package com.example.citipoc.utils

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.util.Log
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.*

open class BaseViewModel : Parcelable {
    val itemType: Int
    protected var uniquetypeId: Long = 0
    var isFixed = false
        private set
    var isSticky = false
        private set
    var childs: ArrayList<BaseViewModel>? = null
        private set
    var isExpanded = false
    var expandedPosition = 0
    var isExpandableView = false

    constructor(itemType: Int) {
        this.itemType = itemType
        uniquetypeId = -1L
    }

    constructor(itemType: Int, uniqueId: Long) {
        this.itemType = itemType
        this.uniquetypeId = uniqueId
    }

    constructor(
        itemType: Int,
        uniqueId: Long,
        isFixed: Boolean,
        isSticky: Boolean,
        childs: ArrayList<BaseViewModel>?
    ) {
        this.itemType = itemType
        this.uniquetypeId = uniqueId
        this.isFixed = isFixed
        this.isSticky = isSticky
        this.childs = childs
        isExpandableView = true
    }

    val randomNumber: Int
        get() {
            val random = Random()
            val id = String.format("%04d", *arrayOf<Any>(Integer.valueOf(random.nextInt(10000))))
            Log.d("BaseViewModel", "getRandomNumber: $id")
            return Integer.valueOf(id).toInt()
        }

    fun getUniqueTypeId(): Long {
        return if (uniquetypeId != -1L && isExpandableView) {
            if (uniquetypeId.toString().length > itemType.toString().length) {
                val i = uniquetypeId.toString().substring(Integer.valueOf(itemType).toString().length)
                    .toLong()
                Log.d("BaseExpandViewModel:", "getUniqueId: $i")
                i
            } else {
                uniquetypeId
            }
        } else {
            uniquetypeId
        }
    }

    val phoneSpan: Float
        get() = 1.0f
    val tabletLandscapeSpan: Float
        get() = 1.0f
    val tabletPortraitSpan: Float
        get() = 1.0f

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(itemType)
        dest.writeLong(uniquetypeId)
    }

    protected constructor(`in`: Parcel) {
        itemType = `in`.readInt()
        uniquetypeId = `in`.readLong()
    }

    object Types {
        const val TEXT_ICON_MODEL = 1003
        const val DIVIDER = 1004
        const val CAROUSEL_ITEM = 1005
        const val SPACE_CARD = 1007
        const val TEXT_MODEL = 1008
        const val CHECKBOX_MODEL = 1009
        const val IMAGE_MODEL = 1010

        @Retention(RetentionPolicy.SOURCE)
        annotation class Type
    }

    companion object {
        protected const val FULL = 1.0f
        protected const val HALF = 0.5f
        protected const val THIRD = 0.33f
        const val ID_NOT_SET = -1
        @JvmField
        val CREATOR: Creator<BaseViewModel?> = object : Creator<BaseViewModel?> {
            override fun createFromParcel(source: Parcel): BaseViewModel? {
                return BaseViewModel(source)
            }

            override fun newArray(size: Int): Array<BaseViewModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
