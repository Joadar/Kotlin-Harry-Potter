package io.smallant.wizard.ui.features.student

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import io.smallant.wizard.R
import io.smallant.wizard.databinding.ActivityStudentBinding
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.utils.STUDENT_FULLNAME
import io.smallant.wizard.utils.STUDENT_ID
import io.smallant.wizard.utils.Theme

class StudentActivity : BaseActivity<ActivityStudentBinding, StudentViewModel>() {
    override fun getLayoutId(): Int = R.layout.activity_student
    override fun getViewModel(): StudentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Theme.houseTheme)
        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@StudentActivity.getViewModel()
            setLifecycleOwner(this@StudentActivity)
        }

        var studentFullname: String? = "John Doe"
        var studentId = 0

        intent?.extras?.let { bundle ->
            studentFullname = bundle.getString(STUDENT_FULLNAME)
            studentId = bundle.getInt(STUDENT_ID, 0)
        }

        supportActionBar?.run {
            this.title = studentFullname
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }

        getViewModel().fetchData(studentId)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}