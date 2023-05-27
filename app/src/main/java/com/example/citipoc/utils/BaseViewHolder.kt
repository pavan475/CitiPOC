package com.example.citipoc.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.Transformation

open class BaseViewHolder<T : BaseViewModel?> : RecyclerView.ViewHolder {
    protected var context: Context
    protected var adapter: BaseRecyclerAdapter<*>? = null
    protected var recyclerView: RecyclerView? = null
    var groupName: TextView? = null
    private val icon: ImageView? = null
    private val arrow: ImageView? = null
    private val rotationAngle = 0

    @Deprecated("")
    constructor(itemView: View?, context: Context) : super(itemView!!) {
        this.context = context
        try {
            if (recyclerView != null && recyclerView!!.getAdapter() != null && recyclerView!!.getAdapter() is BaseRecyclerAdapter<*>) {
                adapter = recyclerView!!.getAdapter() as BaseRecyclerAdapter<*>
                //                ThemeUtils.initTheme(context, itemView, this.adapter.fontPath);
            }
        } catch (var4: Exception) {
            var4.printStackTrace()
        }
    }


    constructor(
        itemView: View?,
        context: Context,
        adapter: BaseRecyclerAdapter<*>?
    ) : super(itemView!!) {
        this.context = context
        this.adapter = adapter
        //        ThemeUtils.initTheme(context, itemView, adapter.fontPath);
//        this.arrow = (ImageView)itemView.findViewById(id.expandable_arrow);
//        this.groupName = (TextView)itemView.findViewById(id.group_name);
//        this.icon = (ImageView)itemView.findViewById(id.group_image);
    }

    fun getAdapters(): BaseRecyclerAdapter<*> {
        return adapter!!
    }

    //    /** @deprecated */
    //    @Deprecated
    //    public void setFont(View... views) {
    //        if(this.adapter != null && this.adapter.fontPath != -1) {
    //            for(int i = 0; i < views.length; ++i) {
    //                ThemeUtils.initTheme(this.context, (TextView)views[i], this.adapter.fontPath);
    //            }
    //        }
    //
    //    }
    fun onBind(position: Int, model: Any?, listener: BaseInteractionListener?) {
//        if(this.arrow != null) {
//            this.rotationAngle = model.isExpanded()?180:0;
//            this.arrow.animate().rotation((float)this.rotationAngle).setDuration(500L).start();
//            this.arrow.setVisibility(model.getChilds() != null && !model.getChilds().isEmpty()?0:8);
//        }
//
//        if(this.icon != null) {
//            ;
//        }
        this.itemView.setOnClickListener(View.OnClickListener {
            //                BaseViewHolder.this.handleExpandableClick(model, listener, position);
        })
    }

    //    public boolean handleExpandableClick(T model, BaseViewHolder.BaseInteractionListener listener, int position) {
    //        if(listener != null && listener.getWrapperListener() != null) {
    //            if(model.getChilds() != null && model.getChilds().size() > 0 && listener != null) {
    //                listener.getWrapperListener().onGroupExpandOrCollapse(position, model);
    //                return true;
    //            } else {
    //                listener.getWrapperListener().onGroupClicked(position, model);
    //                return true;
    //            }
    //        } else {
    //            return false;
    //        }
    //    }
    //    /** @deprecated */
    //    @Deprecated
    //    public void onBind(int position, T model, BaseViewHolder.BaseInteractionListener listener, BaseRecyclerAdapter adapter) {
    //    }
    fun onBind(
        position: Int,
        model: T,
        listener: BaseInteractionListener?,
        payloads: List<Any?>?
    ) {
    }

    @CallSuper
    fun onViewAttachedToWindow(recyclerView: RecyclerView?) {
        this.recyclerView = recyclerView
    }

    @CallSuper
    fun onViewDetachedFromWindow() {
        recyclerView = null
    }

    abstract class BaseInteractionListener  //        private ExpandableRecyclerHolderListener expandableListener;
    {
        //        public void setExpandableListener(ExpandableRecyclerHolderListener expandableListener) {
        //            this.expandableListener = expandableListener;
        //        }
        //        public ExpandableRecyclerHolderListener getWrapperListener() {
        //            return this.expandableListener;
        //        }
        //        public void onTextIconClick(TextIconViewModel model) {
        //        }
        //        public void onTextViewClicked(TextViewModel viewModel) {
        //        }
        //        public void onCheckboxChecked(CompoundButton buttonView, boolean isChecked, CheckBoxViewModel model) {
        //        }
        fun getCarouselImageTransformation(context: Context?): Transformation<*>? {
            return null
        }
    }
}
