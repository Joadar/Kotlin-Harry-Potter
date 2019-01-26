package io.smallant.wizard.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import io.smallant.wizard.extensions.listenEvent

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    protected var viewDataBinding: T? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    protected open fun getRootLayout(): View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding?.executePendingBindings()

        getViewModel().toastMessage.listenEvent(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            getViewModel().onToastShown()
        }

        getViewModel().snackbarMessage.listenEvent(this) {
            getRootLayout()?.let { rootLayout ->
                Snackbar.make(rootLayout, it, Snackbar.LENGTH_SHORT).show()
                getViewModel().onSnackbarShown()
            }
        }
    }
}