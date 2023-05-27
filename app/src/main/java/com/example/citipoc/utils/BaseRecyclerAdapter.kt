package com.example.citipoc.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

abstract class BaseRecyclerAdapter<T : BaseViewHolder<*>>(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>?>() {
    var collection: ArrayList<Any?> = ArrayList<Any?>()
    var inflater: LayoutInflater
    private var recyclerView: RecyclerView? = null
    var fontPath = -1
    var listener: BaseViewHolder.BaseInteractionListener? = null
    var selectedPosition = -1
    private var configuration = 0

    private var spanSize = 0
    var isShimmerView = false
    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return createViewHolderInternal(parent, viewType)!!
    }

    private fun createViewHolderInternal(parent: ViewGroup, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            else -> null
        }
    }

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (!isShimmerView) {
            holder.itemView.setSelected(selectedPosition == position)
            holder.onBind(position, collection[position], listener)
        } else {
//            View childAt = ((ViewGroup)holder.itemView).getChildAt(0);
//            if(childAt instanceof ShimmerLayout) {
//                ((ShimmerLayout)childAt).startShimmer();
//            }
            Log.d("ShimmerView", "Its a ShimmerView, so not calling the BindView()")
        }
    }

    fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int, payloads: List<Any?>?) {
        payloads?.let { super.onBindViewHolder(holder, position, it) }
        if (!isShimmerView) {
            holder.itemView.setSelected(selectedPosition == position)
            holder.onBind(position, collection[position], listener)
        } else {
//            View childAt = ((ViewGroup)holder.itemView).getChildAt(0);
//            if(childAt instanceof ShimmerLayout) {
//                ((ShimmerLayout)childAt).startShimmer();
//            }
            Log.d("ShimmerView", "Its a ShimmerView, so not calling the BindView()")
        }
    }

    fun clearSelected() {
        if (selectedPosition != -1) {
            if (selectedPosition < collection!!.size) {
                this.notifyItemChanged(selectedPosition)
            }
            selectedPosition = -1
        }
    }

    fun setSelectedGroup(newGroupId: Int) {
        clearSelected()
        val oldSelectedGroup = selectedPosition
        selectedPosition = newGroupId
        for (i in collection!!.indices) {
            if (i == oldSelectedGroup || i == selectedPosition) {
                this.notifyItemChanged(i)
            }
        }
    }

    fun setSelectedGroupByUniqueId(uniqueId: Long) {
        clearSelected()
        val oldSelectedGroup = selectedPosition
        for (i in collection!!.indices) {
            val k = collection!![i]
            if (k is BaseViewModel) {
                val uniqueId1: Long = k.getUniqueTypeId()
                if (uniqueId1 == uniqueId) {
                    selectedPosition = i
                    this.notifyItemChanged(i)
                }
            }
        }
    }

    //    public void setListener(BaseViewHolder.BaseInteractionListener listener, ExpandableRecyclerHolderListener exListener) {
    //        listener.setExpandableListener(exListener);
    //        this.listener = listener;
    //    }
    fun setConfiguration(configuration: Int) {
        this.configuration = configuration
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        val layoutManager: RecyclerView.LayoutManager = recyclerView.getLayoutManager()!!
        /*if (layoutManager is GridLayoutManager || layoutManager is StaggeredGridLayoutManager) {
            setDefaultConfiguration()
            (layoutManager as GridLayoutManager).setSpanSizeLookup(lookup)
            lookup.setSpanIndexCacheEnabled(true)
            spanSize = (layoutManager as GridLayoutManager).getSpanCount()
        }*/
    }


    private fun setDefaultConfiguration() {
//        this.setConfiguration(!SystemUtils.isTablet(this.context)?1000:(this.context.getResources().getConfiguration().orientation == 2?1002:1001));
        setConfiguration(1000)
    }

    override fun getItemViewType(position: Int): Int {
        if (position < collection!!.size) {
            val item = getItem(position)
            if (item != null) {
                return getItem(position)?.getUniqueTypeId()!!.toInt()
            }
        }
        return -1
    }

    val itemCounts: Int
        get() = collection.size

    fun getItem(position: Int): BaseViewModel? {
        return collection!![position] as BaseViewModel?
    }

    val items: ArrayList<Any?>
        get() = collection

    @Synchronized
    fun clear() {
        this.clear(false)
    }

    @Synchronized
    fun remove(position: Int): Any? {
        return if (position >= 0 && position < collection!!.size) {
            val item = collection!!.removeAt(position)
            if (item != null) {
                this.notifyItemRemoved(position)
            }
            item
        } else {
            null
        }
    }

    @Synchronized
    fun getItemIndex(o: BaseViewModel?): Int {
        return collection!!.indexOf(o)
    }

    @Synchronized
    fun getItemIndexByUniqueId(o: BaseViewModel): Int {
        for (i in collection!!.indices) {
            val k = collection!![i]
            if (k is BaseViewModel) {
                val uniqueId1: Long = k.getUniqueTypeId()
                if (uniqueId1 == o.getUniqueTypeId()) {
                    return i
                }
            }
        }
        return -1
    }

    fun setFontPaths(fontPath: Int) {
        this.fontPath = fontPath
    }

    fun getModel(uniqueId: Long): ModelData? {
        for (i in items!!.indices) {
            val o: Any? = items!![i]
            if (uniqueId == (o as BaseViewModel?)?.getUniqueTypeId()) {
                val modelData = ModelData()
                modelData.viewModel = o
                modelData.position = i
                return modelData
            }
        }
        return null
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<*>) {
        holder.onViewAttachedToWindow(recyclerView)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*>) {
        holder.onViewDetachedFromWindow()
    }

    @Synchronized
    fun remove(data: BaseViewModel?): Boolean {
        val indexOf = collection!!.indexOf(data)
        return if (indexOf > -1) {
            val remove = collection!!.remove(data)
            if (remove) {
                this.notifyItemRemoved(indexOf)
            }
            remove
        } else {
            false
        }
    }

    @Synchronized
    fun remove(position: Int, data: List<*>?): Boolean {
        return if (position > collection!!.size) {
            Log.e("Remove", "Unable to remove the item at position: $position")
            false
        } else if (data != null && data.size > 0) {
            for (i in data.size downTo 1) {
                Log.d("remove", "Removed Item:${collection!!.removeAt(position + i)}")
            }
            Log.d("itemIndex", "Removing itemIndex:" + (position + 1) + ":data.size():" + data.size)
            this.notifyItemRangeRemoved(position + 1, data.size)
            true
        } else {
            true
        }
    }

    @get:Synchronized
    val isEmpty: Boolean
        get() = collection!!.isEmpty()

    @Synchronized
    fun clear(animate: Boolean) {
        if (collection != null && !collection!!.isEmpty()) {
            collection!!.clear()
            if (animate) {
                val size = collection!!.size
                this.notifyItemRangeRemoved(0, size)
            } else {
                this.notifyDataSetChanged()
            }
        }
    }

    @Synchronized
    fun clearFromIndex(index: Int, animate: Boolean) {
        collection!!.subList(index, collection!!.size).clear()
        if (animate) {
            val size = collection!!.size
            this.notifyItemRangeRemoved(0, size)
        } else {
            this.notifyDataSetChanged()
        }
    }

    @Synchronized
    fun refreshList(animate: Boolean, list: List<BaseViewModel?>?): Boolean {
        this.clear(animate)
        return this.addAll(list)
    }

    @Synchronized
    fun refreshList(list: List<BaseViewModel?>?): Boolean {
        this.clear(false)
        return this.addAll(list)
    }

    @Synchronized
    fun addShimmerAll(list: List<BaseViewModel?>?): Boolean {
        val size = collection!!.size
        isShimmerView = true
        val isAdded = collection!!.addAll(list!!)
        if (isAdded) {
            this.notifyItemRangeInserted(size, collection!!.size)
        }
        return isAdded
    }

    @Synchronized
    fun addAll(list: List<BaseViewModel?>?): Boolean {
        isShimmerView = false
        val size = collection!!.size
        val isAdded = collection!!.addAll(list!!)
        if (isAdded) {
            this.notifyItemRangeInserted(size, collection!!.size)
        }
        return isAdded
    }

    @Synchronized
    fun addAll(positionToAdd: Int, list: List<BaseViewModel?>): Boolean {
        isShimmerView = false
        val isAdded = collection!!.addAll(positionToAdd, list)
        if (isAdded) {
            this.notifyItemRangeInserted(positionToAdd, list.size)
        }
        return isAdded
    }

    @Synchronized
    fun addOrUpdate(toUpdate: BaseViewModel?): Int {
        isShimmerView = false
        var index = collection!!.indexOf(toUpdate)
        if (index == -1) {
            ++index
            add(toUpdate)
            val size = collection!!.size
            this.notifyItemChanged(size)
        } else {
            updateInternal(index, toUpdate, null as Any?)
        }
        return index
    }

    @Synchronized
    private fun updateInternal(index: Int, toUpdate: BaseViewModel?, payload: Any?) {
        if (index != -1) {
            collection!!.removeAt(index)
        }
        collection!!.add(index, toUpdate)
        this.notifyItemChanged(index)
    }

    @Synchronized
    fun add(toAdd: BaseViewModel?) {
        isShimmerView = false
        collection!!.add(toAdd)
    }

    @Synchronized
    fun update(toUpdate: BaseViewModel?): Int {
        return this.update(toUpdate, null as Any?)
    }

    @Synchronized
    fun update(toUpdate: BaseViewModel?, payload: Any?): Int {
        isShimmerView = false
        val index = collection!!.indexOf(toUpdate)
        if (index != -1) {
            updateInternal(index, toUpdate, payload)
        }
        return index
    }

    fun getItemByUniqueId(uniqueId: Int): BaseViewModel? {
        for (i in collection!!.indices) {
            val k = collection!![i]
            if (k is BaseViewModel) {
                val uniqueId1: Long = k.getUniqueTypeId()
                if (uniqueId1 == uniqueId.toLong()) {
                    return k
                }
            }
        }
        return null
    }

    class ModelData {
        var viewModel: BaseViewModel? = null
        var position = 0
    }

    object Configuration {
        const val PHONE = 1000
        const val TABLET_PORTRAIT = 1001
        const val TABLET_LANDSCAPE = 1002

        @Retention(RetentionPolicy.SOURCE)
        annotation class Type
    }

    companion object {
        private const val INVALID_POSITION = -1
        protected const val NO_TYPE = -1
        protected fun attachToContainer(
            inflater: LayoutInflater,
            container: View,
            viewId: Int,
            containerId: Int
        ) {
            inflater.inflate(viewId, container.findViewById<View>(containerId) as ViewGroup, true)
        }
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}
