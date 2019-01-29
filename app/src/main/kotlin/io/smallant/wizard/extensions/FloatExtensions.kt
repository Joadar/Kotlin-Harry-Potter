package io.smallant.wizard.extensions

import android.content.Context


fun Float.spToPx(ctx: Context): Float {
    return this * ctx.resources.displayMetrics.scaledDensity
}

fun Float.pxToDp(context: Context): Float {
    return this / context.resources.displayMetrics.density
}

fun Float.dpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
}