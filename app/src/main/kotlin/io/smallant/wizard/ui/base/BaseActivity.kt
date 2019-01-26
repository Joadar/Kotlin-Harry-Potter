package io.smallant.wizard.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.smallant.wizard.extensions.listenEvent

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    protected var viewDataBinding: T? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding?.executePendingBindings()

        getViewModel().toastMessage.listenEvent(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            getViewModel().onToastShown()
        }
    }
}